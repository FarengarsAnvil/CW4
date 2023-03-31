package cwk4; 
import java.util.*;
import java.io.*;

/**
 * This class implements the behaviour expected from a WIN
 system as required for 5COM2007 - March 2023
 * 
 * @author Team 37
 * @version March 2023
 */

public class SpaceWars implements WIN {

//**************** WIN **************************

    private ArrayList<Force> dockingList = new ArrayList<Force>();
    private ArrayList<Force> activeStarFleet = new ArrayList<Force>();
    private ArrayList<Force> destroyedForces = new ArrayList<Force>();
    private ArrayList<Battle> battleList = new ArrayList<Battle>();
    Admiral admiral1;

    /**
     * Constructor requires the name of the admiral
     *
     * @param admiral the name of the admiral
     */
    public SpaceWars(String admiral) {
        admiral1 = new Admiral(admiral);
        setupForces();
        setupBattles();
    }


    /**
     * Returns a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the
     * Active Star Fleet(ASF),(or, "No forces" if Star Fleet is empty)
     *
     * @return a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the
     * Star Fleet,(or, "No forces" if Active Star Fleet is empty)
     **/
    public String toString() {
        return admiral1.toString() + "\n" + " Active Fleet\n:" + getASFleet() + " Game Over? :" + (isDefeated());
    }

    /**
     * returns true if war chest <=0 AND the active Star Fleet(ASF) has no
     * forces which can be recalled.
     * @return true if war chest <=0 and the active Star Fleet(ASF) has no
     * forces which can be recalled.
     */
    public boolean isDefeated() {
        if (admiral1.getWarChest() <= 0 && activeStarFleet.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * returns the number of bit-coins in the war chest
     *
     * @return the number of bit-coins in the war chest
     */
    public int getWarchest() {
        return admiral1.getWarChest();
    }

    /* Returns a list of all forces in the system by listing :
     * All forces in the Active Star Fleet(ASF), or "No forces in ASF")
     * All forces remaining in the UFF dock, or "No forces in UFF dock
     * All forces destroyed as a result of losing a battle, or "No destroyed forces"
     */
    public String getAllForces() {
        String f = "";
        String d = "";
        String s = "";
        for (int i = 0; i < activeStarFleet.size(); i++) {

            f += activeStarFleet.get(i).getForceName() + " , ";
        }
        for (int j = 0; j < dockingList.size(); j++) {

            d += dockingList.get(j).getForceName() + " , ";
        }
        for (int k = 0; k < destroyedForces.size(); k++) {

            s += destroyedForces.get(k).getForceName() + " , ";
        }
        if (s.length() == 0) {
            s = "No destroyed forces";
        }
        if (f.length() == 0) {
            f = "No forces in ASF";
        }
        if (d.length() == 0) {
            d = "No forces in UFF dock";
        }

        return f + "\n" + d + "\n" + s;
    }


    /**
     * Returns true if force is in the United Forces Fleet(UFF), else false
     *
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFDock(String ref) {
        for (int i = 0; i < dockingList.size(); i++) {
            if (dockingList.get(i).getFleetReference().equals(ref)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a String representation of all forces in the United Forces Fleet(UFF) dock.
     * Does not include destroyed forces
     *
     * @return a String representation of all forces in the United Forces Fleet(UFF) dock.
     **/
    public String getForcesInDock() {
        String s = "\n\n************ Forces available in UFFleet Dock********\n";
        String f = "";
        for (int i = 0; i < dockingList.size(); i++) {
            f += dockingList.get(i).getForceName() + " , ";
        }
        if (f.length() != 0) {
            return f;
        }
        return "No Docked Forces";
    }

    /**
     * Returns a list of all destroyed forces in the system
     *
     * @return all destroyed forces
     */
    public String getDestroyedForces() {
        String s = "\n***** Destroyed Forces ****\n";
        String f = "";
        for (int i = 0; i < destroyedForces.size(); i++) {
            f += destroyedForces.get(i).getForceName() + " , ";
        }
        if (f.length() != 0) {
            return f;
        }
        return "No destroyed Forces";
    }

    /**
     * Returns details of the force with the given reference code, or "No such force"
     *
     * @param ref the reference of the force
     * @return details of the force with the given reference code
     **/
    public String getForceDetails(String ref) {
        for (int i = 0; i < activeStarFleet.size(); i++) {
            if (activeStarFleet.get(i).getFleetReference().equals(ref)) {
                return activeStarFleet.get(i).toString();
            }
        }
        for (int j = 0; j < dockingList.size(); j++) {
            if (dockingList.get(j).getFleetReference().equals(ref)) {
                return dockingList.get(j).toString();
            }
        }
        for (int k = 0; k < destroyedForces.size(); k++) {
            if (destroyedForces.get(k).getFleetReference().equals(ref)) {
                return destroyedForces.get(k).toString();
            }
        }
        return "No such force";
    }


    // ***************** Active Star Fleet Forces ************************

    /**
     * Allows a force to be activated into the Active Star Fleet(ASF), but
     * only if there are enough resources for the activation fee.The force's
     * state is set to "active"
     *
     * @param ref represents the reference code of the force
     * @return 0 if force is activated, 1 if force is not in the UFF dock or is destroyed
     * 2 if not enough money, -1 if no such force
     **/
    public int activateForce(String ref) {
        if (isInUFFDock(ref)) {
            for (int i = 0; i < dockingList.size(); i++) {
                if (dockingList.get(i).getFleetReference().equals(ref)) {
                    Force temp = dockingList.get(i);
                    if (temp.getActivationFee() <= admiral1.getWarChest()) {
                        temp.setState(ForceState.ACTIVE);
                        activeStarFleet.add(temp);
                        dockingList.remove(i);
                        return 0;
                    } else {
                        return 2;
                    }
                }
            }
        } else if (!checkForceIsDestroyed(ref)) {
            return 1;
        }
        return -1;
    }


    /**
     * Returns true if the force with the reference code is in
     * the Active Star Fleet(ASF), false otherwise.
     *
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref) {
        for (int i = 0; i < activeStarFleet.size(); i++) {
            if (activeStarFleet.get(i).getFleetReference().equals(ref)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a String representation of the forces in the active
     * Star Fleet(ASF), or the message "No forces activated"
     *
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet() {
        String s = "\n****** Forces in the Active Star Fleet******\n";
        String f = "";
        if (activeStarFleet.size() == 0) {
            return "No forces activated";
        } else {
            for (int i = 0; i < activeStarFleet.size(); i++) {
                f += activeStarFleet.get(i).getForceName() + " , ";
            }
        }
        return f;
    }

    /**
     * Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only
     * if it is in the Active Star Fleet(ASF)
     *
     * @param ref is the reference code of the force
     **/
    public void recallForce(String ref) {
        for (int i = 0; i < activeStarFleet.size(); i++) {
            if (activeStarFleet.get(i).getFleetReference().equals(ref)) {
                dockingList.add(activeStarFleet.get(i));
                activeStarFleet.get(i).setState(ForceState.DOCKED);
                activeStarFleet.remove(i);
            }
        }
    }


//**********************Battles************************* 

    /**
     * returns true if the number represents a battle
     *
     * @param num is the number of the required battle
     * @returns true if the number represents a battle
     **/
    public boolean isBattle(int num) {
        if (getBattleObj(num) != null) {
            return true;
        }
        return false;
    }

    /**
     * Provides a String representation of a battle given by
     * the battle number
     *
     * @param num the number of the battle
     * @return returns a String representation of a battle given by
     * the battle number
     **/
    public String getBattle(int num) {
        if (getBattleObj(num) != null) {
            getBattleObj(num).toString();
        }
        return "No such battle";
    }

    /**
     * Provides a String representation of all battles
     *
     * @return returns a String representation of all battles
     **/
    public String getAllBattles() {
        String s = "\n************ All Battles ************\n";
        String f = "";
        for (int i = 0; i < battleList.size(); i++) {
            f += battleList.get(i).getBattle() + " , ";

        }
        return f;
    }

    /**
     * Retrieves the battle represented by the battle number.Finds
     * a force from the Active Star Fleet which can engage in the battle.The
     * results of battle will be one of the following:
     * 0 - Battle won, battle gains added to the war chest,
     * 1 - Battle lost as no suitable force available, battle losses
     * deducted from war chest
     * 2 - Battle lost on battle strength , battle losses
     * deducted from war chest and force destroyed
     * 3 - If a battle is lost and admiral completely defeated (no resources and
     * no forces to recall)
     * -1 - no such battle
     *
     * @param battleNo is the number of the battle
     * @return an int showing the result of the battle (see above)
     */
    public int doBattle(int battleNo) {
        //Check that the Battle No corresponds to an Actual battle Object.
        if (!checkBattleExists(battleNo)) {
            return -1;
        } else {
            //Given the Battle Object exists, we now check if we have an available force for the Battle.
            if(getForceForBattle(battleNo) != null) {
                //If our Forces strength is Greater than the Enemies strength of the Battle Object
                if(getForceForBattle(battleNo).getBattleStrength() > getBattleObj(battleNo).getEnemyStrength()) {
                    //We then Add the Battle Gains to our Warchest Funds.
                    admiral1.modifyWarChest(getBattleObj(battleNo).getBattleGains());
                    return 0;
                }
                //If our Forces Strength is not > than the Enemies strength.
                else if(getForceForBattle(battleNo).getBattleStrength() <= getBattleObj(battleNo).getEnemyStrength()){
                    //We deduct the Battle losses from the Warchest Funds.
                    admiral1.modifyWarChest(getBattleObj(battleNo).getBattleLosses() * -1);
                    //We add the Force to the destroyed forces Arraylist.
                    destroyedForces.add(getForceForBattle(battleNo));
                    //force status is destroyed.
                    getForceForBattle(battleNo).setState(ForceState.DESTROYED);
                    //We remove the force from the ASF.
                    activeStarFleet.remove(getForceForBattle(battleNo));
                    //If isDefeated returns True, Meaning that Warchest Funds is <= 0 or that ASF is empty we return 3.
                    if(isDefeated()) {
                        return 3;
                    }
                    return 2;
                }
            }
        }
        return 1;
    }


    //*******************************************************************************


    /**
     * Instantiates all the Force objects in Appendix 1, and adds them to the Docking List collection.
     */
    private void setupForces() {
        Wings wing1 = new Wings("IW1", "Twister", 200, 200, false, 10);
        Starship ship1 = new Starship("SS2", "Enterprise", 300, 200, false, 10, 20);
        Warbird bird1 = new Warbird("WB3", "Droop", 300, 100, false);
        Wings wing2 = new Wings("IW4", "Winger", 200, 400, false, 20);
        Warbird bird2 = new Warbird("WB5", "Hang", 400, 300, true);
        Starship ship2 = new Starship("SS6", "Voyager", 450, 200, false, 15, 10);
        Starship ship3 = new Starship("SS87", "Explorer", 120, 65, false, 4, 5);
        Warbird bird3 = new Warbird("WB9", "Hover", 300, 400, false);
        Wings wing3 = new Wings("IW10", "Flyer", 200, 100, false, 5);
        dockingList.add(wing1);
        dockingList.add(ship1);
        dockingList.add(bird1);
        dockingList.add(wing2);
        dockingList.add(bird2);
        dockingList.add(ship2);
        dockingList.add(ship3);
        dockingList.add(bird3);
        dockingList.add(wing3);
    }

    /**
     * Instantiates all the Battle objects in Appendix 1 and adds them to the battleList Collection.
     */

    private void setupBattles() {

        Battle battle1 = new Battle(1, BattleType.FIGHT, "Borg", 200, 300, 100);
        Battle battle2 = new Battle(2, BattleType.SKIRMISH, "Kardassians", 700, 200, 120);
        Battle battle3 = new Battle(3, BattleType.AMBUSH, "Ferengi", 100, 400, 150);
        Battle battle4 = new Battle(4, BattleType.FIGHT, "Ewoks", 600, 600, 200);
        Battle battle5 = new Battle(5, BattleType.AMBUSH, "Borg", 500, 400, 90);
        Battle battle6 = new Battle(6, BattleType.SKIRMISH, "Borg", 150, 100, 100);
        Battle battle7 = new Battle(7, BattleType.FIGHT, "Groaners", 150, 500, 300);
        Battle battle8 = new Battle(8, BattleType.AMBUSH, "Wailers", 300, 300, 300);
        battleList.add(battle1);
        battleList.add(battle2);
        battleList.add(battle3);
        battleList.add(battle4);
        battleList.add(battle5);
        battleList.add(battle6);
        battleList.add(battle7);
        battleList.add(battle8);
    }

    //**************************Add your own private methods here ***********************

    /**
     * @param battleNo = Integer representing the Battle Number of a Battle in the Battle List.
     * @return = If battleNo = Battle.battleNumber: Return Battle Object. Else return null.
     */
    private Battle getBattleObj(int battleNo) {
        for (int i = 0; i < battleList.size(); i++) {
            if (battleList.get(i).getBattleNumber() == battleNo) {
                return battleList.get(i);
            }
        }
        return null;
    }

    /**
     * @param battleNumber = Integer corresponding to a Battle Objects battleNumber.
     * @return = If the battleNumber matches a Force objects battle Number, return true. Elsewise: return false.
     */
    private boolean checkBattleExists(int battleNumber) {
        for (int i = 0; i < battleList.size(); i++) {
            if (battleList.get(i).getBattleNumber() == battleNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param ref = String variable representing a Force objects fleet reference.
     * @return = If force in Destroyed Forces List: Return true. Elsewise: Return false.
     */
    private boolean checkForceIsDestroyed(String ref) {
        for (int i = 0; i < destroyedForces.size(); i++) {
            if (destroyedForces.get(i).getFleetReference().equals(ref)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param battleNo = Integer variable that should be a Battle objects battle Number.
     * @return = Returns a force object to fight the Battle corresponding to the battle number, if no suitabel force exist for the battle; it returns null.
     */
    private Force getForceForBattle(int battleNo) {
        BattleType type1 = getBattleObj(battleNo).getBattle();
        for (int i = 0; i < activeStarFleet.size(); i++) {
            if (type1 == BattleType.FIGHT) {
                if (activeStarFleet.get(i).canFight()) {
                    return activeStarFleet.get(i);
                }
            } else if (type1 == BattleType.AMBUSH) {
                if (activeStarFleet.get(i).canAmbush()) {
                    return activeStarFleet.get(i);
                }
            } else if (type1 == BattleType.SKIRMISH) {
                if (activeStarFleet.get(i).canSkirmish()) {
                    return activeStarFleet.get(i);
                }
            }
        }
        return null;
    }


    //*******************************************************************************

    //These methods are not needed until Task 3.5. Uncomment thmemto complete task 3.5
    // ***************   file write/read  *********************

     /** Writes whole game to the specified file
      * @param fname name of file storing requests
      */
    public void saveGame(String fname) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fname);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            //this keyword pertains to the current Object, so we're saving the current object to a file.
            objectOut.writeObject(this);
            objectOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     /** reads all information about the game from the specified file
      * and returns a SpaceWars object
      * @param fname name of file storing the game
      * @return the game (as a SpaceWars object)
      */
     public SpaceWars restoreGame(String fname) {
            try {
                FileInputStream fileIn = new FileInputStream(fname);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                //We cast what we read from the file as a SpaceWars object and then return it.
                SpaceWars a =  (SpaceWars) objectIn.readObject();
                return a;

            } catch (Exception e) {
                e.printStackTrace();
        }
        return null;
     }

     /** Reads information about battles from the specified file into the appropriate collection
      * @param =  name of the file
      */

     private void readBattles(String fname) {
         try {
             // Create a FileinputStream and ObjInputStream Objects and pass fname as Param.
             FileInputStream fileIn = new FileInputStream(fname);
             ObjectInputStream objectIn =  new ObjectInputStream(fileIn);
             //We loop through the file.
             while(true) {
                 Object temp = objectIn.readObject();
                 // If the Line in the file is not Null:
                 if(temp != null) {
                     //We add it to the Battle List, casting temp as a Battle Object.
                      battleList.add((Battle) temp);
                 }
                 //If the file we're reading does not have any Object data, we break the Loop.
                 else if(temp == null) {
                     break;
                 }
             }
         }
         catch(Exception e) {
             e.printStackTrace();
         }
     }

}

