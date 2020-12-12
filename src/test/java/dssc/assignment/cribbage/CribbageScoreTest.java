package dssc.assignment.cribbage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CribbageScoreTest {

    @ParameterizedTest
    @CsvSource({"3H5D7S, 1","5H5D5S, 1", "2D2H2S2C, 0"})
    void createListOfCardsFromStringAndTestScoreAccordingToTheRuleOfFifteenTwos(String cribbageCardListAsString, int score) {
        List<Card> cardList = new ArrayList<>(cribbageCardListAsString.length()/2);

        for(int i=0; i<cribbageCardListAsString.length(); i+=2) {
            String cardAsString = cribbageCardListAsString.substring(i,i+2);
            cardList.add(new Card(cardAsString));
        }

        assertEquals(score,CribbageScore.scoreComputedAccordingToTheRuleOfFifteenTwos(cardList));
    }

    @ParameterizedTest
    @CsvSource({"4H5D6S, 3","5H6D7S8S, 4","5H6D7S8S9S, 5", "2D2H2S2C, 0"})
    void createListOfCardsFromStringAndTestScoreAccordingToTheRuleOfRuns(String cribbageCardListAsString, int score) {
        List<Card> cardList = new ArrayList<>(cribbageCardListAsString.length()/2);

        for(int i=0; i<cribbageCardListAsString.length(); i+=2) {
            String cardAsString = cribbageCardListAsString.substring(i,i+2);
            cardList.add(new Card(cardAsString));
        }

        assertEquals(score,CribbageScore.scoreComputedAccordingToTheRuleOfRuns(cardList));
    }

}