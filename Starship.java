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

    public Starship(String fleetRef, String force, int activationCost, int battleValue, boolean cloak, int lasers, int photons) {
        super(fleetRef, force, activationCost, battleValue, cloak);
        amountLaserCannons = lasers;
        amountPhotons = photons;
    }
    // TODO:: Accessor method returns the amount of Laser cannons Starship object has.
    public int getLaserCannons() { return amountLaserCannons;}
    // TODO:: Accessor method returns the amount of Photons Starship object has.
    public int getPhotons() { return amountPhotons; }

    @Override
    public boolean canAmbush() {
        return false;
    }

    @Override
    public String toString() {
        return "Force Name:" + getForceName() + " Reference:" + getFleetReference() + " Strength:" + getBattleStrength() + " Activation Fee:" + getActivationFee()
                + " Cloak?:" + hasCloak + " Number Laser Cannons:" + amountLaserCannons + " Number Photons:" + amountPhotons + "Status:" + forceStatus.toString();

    }

}
