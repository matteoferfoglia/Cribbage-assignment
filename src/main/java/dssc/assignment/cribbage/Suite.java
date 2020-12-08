package dssc.assignment.cribbage;

import java.util.Arrays;

public enum Suite {
    CLUBS('C'), DIAMONDS('D'), HEARTS('H'), SPADES('S');

    private final char suiteAsChar;

    Suite (char suiteAsChar){
        this.suiteAsChar = suiteAsChar;
    }

    public static Suite convertCharToSuite(char characterToBeConvertedToSuite) {
        for(Suite suite : Suite.values()) {
            if(Character.toLowerCase(suite.suiteAsChar) == Character.toLowerCase(characterToBeConvertedToSuite)) {
                return suite;
            }
        }
        throw new RuntimeException("The given character does not correspond to any Suite!");
    }

    public static boolean isValid(char suiteAsChar) {
        // TODO refactor: create an abstract class for Suite and Rank (same implementation for this method)
        return Arrays.stream(Suite.values())
                .anyMatch( suiteValue -> Character.toLowerCase(suiteValue.suiteAsChar) == Character.toLowerCase(suiteAsChar));
    }

    @Override
    public String toString() {
        return "" + suiteAsChar;
    }
}
