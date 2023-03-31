package cwk4;

import java.io.Serializable;

public class Battle implements Serializable {

    private int battleNumber;
    private BattleType battle;
    private String enemyName;
    private int enemyStrength;
    private int battleGains;
    private int battleLosses;
    /** Constructor For Battle Object.
     * @param battleNum = Unique integer Value representing a Specific battle
     * @param type = BattleType Enum representing the type of Battle object
     * @param enemy = String variable representing the Enemy
     * @param enemyStrengthVal = Integer variable representing the Battle Strength of the Enemy
     * @param battleWins = Integer variable representing the Bit-coin additions for winning the Battle.
     * @param battleLoss = Integer variable representing the Bit-coin deductions for losing the Battle.
     */
    public Battle(int battleNum, BattleType type, String enemy, int enemyStrengthVal,  int battleLoss, int battleWins) {
        battleNumber = battleNum;
        battle = type;
        enemyName = enemy;
        enemyStrength = enemyStrengthVal;
        battleLosses = battleLoss;
        battleGains = battleWins;
    }

    /**
     *Several accesor Methods which return an Field of the Battle object.
     */
    public int getBattleNumber() {
        return battleNumber;
    }
    public BattleType getBattle() {
        return battle;
    }
    public String getEnemyName() {
        return enemyName;
    }
    public int getEnemyStrength() {
        return enemyStrength;
    }
    public int getBattleGains() {
        return battleGains;
    }
    public int getBattleLosses() { return battleLosses; }

    /**
     * @return = String that encompasses all the Fields of the Battle object.
     */
    public String toString() {
        return "Battle Number:" + battleNumber + " Battle Type:" + battle + " Enemy Name:"
                + enemyName + " Enemy Strength:" + enemyStrength + " Battle Gains:" + battleGains + " Battle Losses:" + battleLosses;
    }

}
