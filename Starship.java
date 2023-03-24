package cwk4;

public class Starship extends Force{

    private int amountLaserCannons;
    private int amountPhotons;
    /**
     * Constructor For the Force Class. Takes 8 Parameters::
     * @param fleetRef       = String variable of a valid fleet reference
     * @param force          = String variable representing the name of the Force
     * @param activationCost = Integer representing the activation Fee of the Force Object
     * @param battleValue    =  Integer representing the battle Strength of the Force Object
     */

    public Starship(String fleetRef, String force, int activationCost, int battleValue, int lasers, int photons) {
        super(fleetRef, force, activationCost, battleValue);
        amountLaserCannons = lasers;
        amountPhotons = photons;
    }
}
