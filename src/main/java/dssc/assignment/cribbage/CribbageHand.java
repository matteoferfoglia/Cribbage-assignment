package dssc.assignment.cribbage;

import java.util.ArrayList;
import java.util.Optional;

public class CribbageHand {

    private final ListOfCards fourHandCards;
    // The starter card is an attribute of the game rather than a cribbage hand.

    public CribbageHand(ListOfCards fourHandCards) {
        if(fourHandCards.size()!=4)
            throw new IllegalArgumentException("A list of 4 elements is expected, " + fourHandCards.size() + " elements found.");
        this.fourHandCards = new ListOfCards(fourHandCards);
    }

    public CribbageHand(String cribbageHandAsString) {
       this(convertCribbageHandFromStringRepresentationToListOfCards(
               Optional .of(cribbageHandAsString)
                        .filter(CribbageHand::isValidStringRepresentationForCribbageHand)
                        .orElseThrow(IllegalArgumentException::new)
               //Todo:  this works, but could be done better
       ));
    }

    private static boolean isValidStringRepresentationForCribbageHand(String cribbageHandAsString){
        try {
            isValidStringRepresentationForCribbageHandAndThrowExceptionIfInvalid(cribbageHandAsString);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private static void isValidStringRepresentationForCribbageHandAndThrowExceptionIfInvalid(String cribbageHandAsString)
            throws IllegalArgumentException {

        if(cribbageHandAsString == null || cribbageHandAsString.length()==0) {
            throw new IllegalArgumentException("A cribbage hand cannot be neither null nor empty.");
        }

        if(cribbageHandAsString.length()%2 != 0) {
            throw new IllegalArgumentException("A cribbage hand cannot be represented by an odd-length string.");
        }

        convertCribbageHandFromStringRepresentationToListOfCards(cribbageHandAsString);
    }

    private static ListOfCards convertCribbageHandFromStringRepresentationToListOfCards(String cribbageHandAsString) {
        ListOfCards cribbageHandAsList = new ListOfCards(cribbageHandAsString.length()/2);
        for(int i=0; i<cribbageHandAsString.length(); i+=2) {
            String cardAsString = cribbageHandAsString.substring(i,i+2);
            cribbageHandAsList.add(new Card(cardAsString));
        }
        return cribbageHandAsList;
    }

    public String convertToString(Card starterCard) {
        StringBuilder cribbageHandAsString = new StringBuilder();
        for(Card card : fourHandCards){
            cribbageHandAsString.append(card.toString());
        }
        cribbageHandAsString.append(starterCard.toString());
        return cribbageHandAsString.toString();
    }

    public ListOfCards getFourHandCards() {
        return fourHandCards;
    }
}
