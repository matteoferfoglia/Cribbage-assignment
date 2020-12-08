package dssc.assignment.cribbage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CribbageHandTest {

    @ParameterizedTest
    @ValueSource(strings = {"","5h6","  "})
    void createCribbageHandFromString_shouldThrowException(String cribbageHandAsString) {
        try {
            new CribbageHand(cribbageHandAsString);
            fail("The method should have thrown an exception, but it did not.");
        } catch(Exception e){
            //success
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"5H5D5SJC","0DJHQSAC"})
    void createCribbageHandFromString_shouldNotThrowException(String cribbageHandAsString) {
        try {
            new CribbageHand(cribbageHandAsString);
        } catch(Exception e){
            fail("The method threw an exception, but it should not.");
        }
    }

    @ParameterizedTest
    @CsvSource({"5H5D5SJC, 5C", "0DJHQSAC, 9D"})
    void createCribbageHandFromStringAndReconvertToString(String cribbageHandAsString, String starterCardAsString) {
        CribbageHand cribbageHand;
        try {
            cribbageHand = new CribbageHand(cribbageHandAsString);
            assertEquals(cribbageHandAsString+starterCardAsString,
                    cribbageHand.convertToString(new Card(starterCardAsString)));
        } catch(Exception e){
            fail("The method threw an exception, but it should not.");
        }
    }

    @Test
    void representCribbageHand() {
        Card card = new Card("5H");
        assertAll(
                () -> assertEquals('5', card.getRank().convertRankToChar()),
                () -> assertEquals(Rank.FIVE, card.getRank()),
                () -> assertEquals(Suite.HEARTS, card.getSuite())
        );
    }
}
