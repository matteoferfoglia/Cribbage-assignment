package dssc.assignment.cribbage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParseHandTest {

    @Test
    void parseOneCard() {
        Card card = new Card("5H");
        assertAll(
                () -> assertEquals('5', card.getRank().convertRankToChar()),
                () -> assertEquals(Rank.FIVE, card.getRank()),
                () -> assertEquals(Suite.HEARTS, card.getSuite())
        );
    }
}
