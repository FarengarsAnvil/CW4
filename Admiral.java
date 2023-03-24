package cwk4;

import java.util.ArrayList;

public class Admiral  {

private String admiralName;
private int warChestFunds;
private ArrayList<Force> dockingList;

private ArrayList<Force> activeStarFleet;

public Admiral(String admiral ) {
    // TODO:: Initialise the Admiral's name, and sets the warChestFunds to 1000 on Object's instantiation.
    admiralName = admiral;
    warChestFunds = 1000;
}

public String toString() {
    // TODO:: Returns a String encapsulating all the Information about the Admiral.
    String s = "Admiral Name:" + admiralName + "Warchest Balance:" + warChestFunds ;
    return s;


}

}
