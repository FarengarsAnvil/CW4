package cwk4; 


/**
 * Details of your team
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "37";
        
        details[1] = "Ali";
        details[2] = "Sana";
        details[3] = "19041936";

        details[4] = "Khidr";
        details[5] = "Muhammed";
        details[6] = "19031414";

        details[7] = "Tasker";
        details[8] = "Harry";
        details[9] = "19063128";

        details[10] = "White";
        details[11] = "Charlie";
        details[12] = "20040033";

    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
