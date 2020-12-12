package dssc.assignment.cribbage;

public class Card implements Comparable<Card> {

    private final Rank rank;
    private final Suite suite;

    public Card(char rank, char suite){
        this(rank+""+suite);
    }

    public Card(String cardAsString) {
        if(isValidCardRepresentation(cardAsString)) {

            char rank = cardAsString.charAt(0);
            char suite = cardAsString.charAt(1);

            this.rank = Rank.convertCharToRank(rank);
            this.suite = Suite.convertCharToSuite(suite);

        } else {
            throw new IllegalArgumentException("Invalid representation for a card");
        }
    }

    public Rank getRank() {
        return rank;
    }

    public Suite getSuite() {
        return suite;
    }

    public static boolean isValidCardRepresentation(String cardAsString) {

        if(cardAsString == null || cardAsString.length()!=2) {
            throw new IllegalArgumentException("Invalid representation of a card as a string.");
        }

        char rank = cardAsString.charAt(0);
        char suite = cardAsString.charAt(1);

        return Rank.isValid(rank) && Suite.isValid(suite);
    }

    @Override
    public String toString() {
        return rank.toString() + suite.toString();
    }

    public int compareTo(Card aCard) {
        return (int)Rank.convertRankToChar(rank) - (int)(Rank.convertRankToChar(aCard.getRank()));
    }

    public boolean isOneRankFarFromThisCard(Card otherCard){
        return Math.abs(compareTo(otherCard)) == 1;
    }
}
