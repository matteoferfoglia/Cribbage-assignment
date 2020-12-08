package dssc.assignment.cribbage;

import java.util.Arrays;

public enum Rank {

    ACE ('A'), TWO ('2'), THREE('3'), FOUR('4'), FIVE('5'),
    SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'), TEN('0'),
    JACK('J'), QUEEN('Q'), KING('K');

    private final char rankAsChar;

    Rank(char rankAsChar){
        this.rankAsChar = rankAsChar;
    }

    public static boolean isValid(char rankAsChar) {

        return Arrays.stream(Rank.values())
                .anyMatch( rankValue -> Character.toLowerCase(rankValue.rankAsChar) == Character.toLowerCase(rankAsChar));
    }

    public char convertRankToChar() {
        // TODO this method may be enitrely replaced by the static one, to avoid message chain code smell
        return rankAsChar;
    }

    public static char convertRankToChar(Rank rank) {
        return rank.convertRankToChar();
    }

    public static Rank convertCharToRank(char characterToBeConvertedToRank){
        for(Rank rank : Rank.values()) {
            if(Character.toLowerCase(rank.rankAsChar) == Character.toLowerCase(characterToBeConvertedToRank)) {
                return rank;
            }
        }
        throw new RuntimeException("The given character does not correspond to any Rank!");
    }

    public static int getDefaultNumericValue(Rank rank) {
        return Character.getNumericValue(rank.rankAsChar);
    }

    @Override
    public String toString() {
        return "" + rankAsChar;
    }
}
