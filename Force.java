package cwk4;


public class Force {

private String fleetReference;
private String forceName;
private int activationFee;
private ForceState forceStatus;
private int battleStrength;
public boolean hasCloak;

//PERSONAL NOTE:: INSTEAD OF HAVING THESE FIELDS, HAVE METHODS WHICH RETURN A BOOLEAN WITH THESE FIELD NAMES.

    /** Constructor For the Force Class. Takes 8 Parameters::
     * @param fleetRef = String variable of a valid fleet reference
     * @param force = String variable representing the name of the Force
     * @param activationCost = Integer representing the activation Fee of the Force Object
     * @param battleValue = Integer representing the Battle Strength of the Object.
     * @param cloak = Boolean variable, if true: Has Cloak, if false: Does not have Cloak.
     */
public Force(String fleetRef, String force,  int activationCost, int battleValue, boolean cloak) {
    fleetReference = fleetRef;
    forceName = force;
    activationFee = activationCost;
    forceStatus = ForceState.DOCKED;
    battleStrength = battleValue;
    hasCloak = cloak;

}

//TODO:: Accessor Method returns Fleet Reference.
public String getFleetReference() {
    return fleetReference;
}
//TODO:: Accessor Method returns Name of Force.
public String getForceName() {
    return forceName;
}
//TODO:: Accessor Method returns Activation Fee.
public int getActivationFee() {
    return activationFee;
}
//TODO:: Accessor Method returns Battle Strength of Force Object
public int getBattleStrength() {
    return battleStrength;
}
//TODO:: Accessor Method returns ForceState enum for the forceStatus of the Force Object.
public ForceState getForceStatus() {
    return forceStatus;
}

// TODO:: Function determines whether Force can Skirmish or not
public boolean canSkirmish() {
    return true;
}
public boolean canAmbush() {
   return true;
}
public boolean canFight() {
   return true;
}

}