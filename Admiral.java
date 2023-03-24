package cwk4;

public class Admiral  {

private String admiralName;
private int warChestFunds;

// TODO:: Initialise the Admiral's name, and sets the warChestFunds to 1000 on Object's instantiation.
public Admiral(String admiral) {
    admiralName = admiral;
    warChestFunds = 1000;
}

// TODO:: Accessor methods that return Object Fields.
public String getAdmiralName() {
    return admiralName;
 }
public int getWarChest() {
    return warChestFunds;
}



// TODO:: Returns a String encapsulating all the Information about the Admiral.
public String toString() {
    return   "Admiral Name:" + admiralName + "Warchest Balance:" + warChestFunds;
}

}
