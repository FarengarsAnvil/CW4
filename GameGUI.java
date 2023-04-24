package cwk4; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 * 
 * @author A.A.Marczyk
 * @version 20/10/23
 */
public class GameGUI 
{
    private WIN gp = new SpaceWars("Horatio");
    private JFrame myFrame = new JFrame("Game GUI");

    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton fightBtn = new JButton("Fight");
    private JPanel eastPanel = new JPanel();

    private JButton stateBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");

    
    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the Forces menu
        JMenu forcesMenu = new JMenu("Forces");
        menubar.add(forcesMenu);
        
        JMenuItem listForcesItem = new JMenuItem("List All Forces ");
        listForcesItem.addActionListener(new ListForcesHandler());
        forcesMenu.add(listForcesItem);

        //list ActiveStarFleet
        JMenuItem listASFleet = new JMenuItem("List Active Star Fleet");
        listASFleet.addActionListener(new ListASFHandler());
        forcesMenu.add(listASFleet);
        //activate a force
        JMenuItem activateForce = new JMenuItem("Activate a Force");
        activateForce.addActionListener(new activateForceHandler());
        forcesMenu.add(activateForce);
        //recall a force
        JMenuItem recallForce = new JMenuItem("Recall a Force");
        recallForce.addActionListener(new recallForceHandler());
        forcesMenu.add(recallForce);



        //make Battle menu
        JMenu battlesMenu = new JMenu("Battles");
        menubar.add(battlesMenu);
        //list all battles in listing
        JMenuItem listAllBattles = new JMenuItem("List all Battles");
        listAllBattles.addActionListener(new ListBattlesHandler());
        battlesMenu.add(listAllBattles);
    }


    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);
        listing.setVisible(false);
        myFrame.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));
        eastPanel.add(fightBtn);
        fightBtn.addActionListener(new FightBtnHandler());
        fightBtn.setVisible(true);

        //add ViewState button to east panel
        eastPanel.add(stateBtn);
        stateBtn.addActionListener(new StateBtnHandler());
        stateBtn.setVisible(true);

        //add Clear button to east panel
        eastPanel.add(clearBtn);
        clearBtn.addActionListener(new ClearBtnHandler());
        clearBtn.setVisible(true);
        
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    
    private String fighting(int code)
    {
        switch (code)
        {
            case 0:return "Fight won"; 
            case 1:return "Fight lost as no suitable force available"; 
            case 2:return "Fight lost on battle strength, force destroyed";
            case 3:return "fight is lost and admiral completely defeated ";
        }
        return " no such fight ";
    }


    /*
        switch statement for activating a force
     */
    private String activation(int code)
    {
        switch (code)
        {
            case 0:return "force is activated";
            case 1:return "force is not in the UFFDock";
            case 2:return "not enough money";
            case 3:return "no such force";
            default: return "Error";
        }
    }


    
    private class ListForcesHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = gp.getAllForces();
            listing.setText(xx);
            
        }
    }


    /*
     *     gets all forces in the ASF and displays in listing
     */
    private class ListASFHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = "Active Star Fleet:\n" + gp.getASFleet();
            listing.setText(xx);
        }
    }



    /*
        activates a force and moves it to teh Active Star Fleet
     */
    private class activateForceHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int result;
            String inputValue = JOptionPane.showInputDialog("Force reference ?: ");
            result = gp.activateForce(inputValue);
            JOptionPane.showMessageDialog(myFrame, activation(result));
        }

    }

    /*
        recalls a force from ASF and moves it to the dock
     */
    private class recallForceHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String inputValue = JOptionPane.showInputDialog("Force reference ?:");
            //checks if force can be recalled
            if (gp.isInASFleet(inputValue))
            {
                gp.recallForce(inputValue);
                JOptionPane.showMessageDialog(myFrame, "Force Recalled");
            }
            else if (gp.isInUFFDock(inputValue))
            {
                JOptionPane.showMessageDialog(myFrame, "Force Already in Dock");
            }
            else
            {
                JOptionPane.showMessageDialog(myFrame, "Error");
            }
        }
    }


    /*
     *  gets all battles and displays in listing
     */
    private class ListBattlesHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = gp.getAllBattles();
            listing.setText(xx);
        }
    }
    
    
    private class FightBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            int result = -1;
            String inputValue = JOptionPane.showInputDialog("Fight number ?: ");
            int num = Integer.parseInt(inputValue);
            result = gp.doBattle(num);
            JOptionPane.showMessageDialog(myFrame,fighting(result));    
        }
    }


    /*
        view state of game button
     */
    private class StateBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = gp.toString();
            listing.setText(xx);

        }
    }

    
    /*
        clears JTextArea listing
     */
    private class ClearBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = " ";
            listing.setText(xx);
        }
    }
 
    
    public static void main(String[] args)
    {
        new GameGUI();
    }
}
   
