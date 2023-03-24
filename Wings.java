package cwk4;

public class Wings extends Force{

private int strikerAmount;
    /**
     * Constructor For the Force Class. Takes 8 Parameters::
     * @param fleetRef       = String variable of a valid fleet reference
     * @param force          = String variable representing the name of the Force
     * @param activationCost = Integer representing the activation Fee of the Force Object
     * @param battleValue    = Integer representing the Battle Strength of the Object.
     * @param cloak          = Boolean Variable. IF TRUE: HAS CLOAK. IF FALSE: NO CLOAK.
     */
    public Wings(String fleetRef, String force, int activationCost, int battleValue, boolean cloak, int strikers) {
        super(fleetRef, force, activationCost, battleValue, cloak);
        strikerAmount = strikers;
    }


    @Override
    public boolean canFight() {
        return false;
    }

}



