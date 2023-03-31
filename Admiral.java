package cwk4;
import java.io.Serializable;

public class Admiral implements Serializable {

private String admiralName;
private int warChestFunds;

// TODO:: Initialise the Admiral's name, and sets the warChestFunds to 1000 on Object's instantiation.
public Admiral(String admiral) {
    admiralName = admiral;
    warChestFunds = 1000;
}

// TODO:: Accessor methods that return Admiral name field.
public String getAdmiralName() { return admiralName; }

// TODO:: Accessor method returns Admiral field warChestFunds, an Integer.
public int getWarChest() {
    return warChestFunds;
}

public void modifyWarChest(int value) { warChestFunds+= value; }

// TODO:: Returns a String encapsulating all the Information about the Admiral.
public String toString() {
    return   "Admiral Name:" + admiralName + "Warchest Balance:" + warChestFunds;
}
}
