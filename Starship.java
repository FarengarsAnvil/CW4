package cwk4;

public class Starship extends Force{
    /**
     * Constructor For the Starship Class. Takes 8 Parameters::
     * @param fleetRef       = String variable of a valid fleet reference
     * @param force          = String variable representing the name of the Force
     * @param activationCost = Integer representing the activation Fee of the Force Object
     * @param battleValue    =  Integer representing the battle Strength of the Force Object
     * @param cloakVal       = Boolean Variable. If true: The Force Object has a Cloak. False: No Cloak.
     * @param skirmish       = Boolean Variable. If true: The force can partake in Skirmishes. False: Cannot take part in Skirmish.
     * @param ambush         =   Boolean Variable. If true: The force can partake in Ambushes. False: Cannot take part in Ambushes.
     * @param fight          =     Boolean Variable. If true: The force can partake in Fights. False: Cannot take part in Fights.
     */
    public Starship(String fleetRef, String force, int activationCost, int battleValue, Boolean cloakVal, Boolean skirmish, Boolean ambush, Boolean fight) {
        super(fleetRef, force, activationCost, battleValue, cloakVal, skirmish, ambush, fight);
    }
}
