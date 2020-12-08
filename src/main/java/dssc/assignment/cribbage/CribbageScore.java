package dssc.assignment.cribbage;

import javax.rmi.CORBA.Util;
import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class CribbageScore {

    public static int scoreComputedAccordingToFifteenTwosRule(List<Card> cards) {
        // two points for each separate combination of two or more cards totalling exactly
        //  fifteen (Jack, Queen and King count as 10, Ace count as 1)

        final int TARGET_SCORE_AS_SUM_OF_RANKS = 15;

        //Numeric rank of the card depends on the rule ((maybe) not generalizable)  // todo Refactor?
        ToIntFunction<Card> getNumericRankOfTheCard = card -> {
            int scoreCorrespondingToGivenRank;
            Rank rank = card.getRank();
            switch (rank) {
                case ACE:
                    scoreCorrespondingToGivenRank = 1;
                    break;
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                    scoreCorrespondingToGivenRank = Rank.getDefaultNumericValue(rank);
                    break;
                case JACK:
                case QUEEN:
                case KING:
                    scoreCorrespondingToGivenRank = 10;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid rank.");
            }
            return scoreCorrespondingToGivenRank;
        };

        List<Integer> rankOfCardsAsList = cards .stream()
                                                .mapToInt(getNumericRankOfTheCard)
                                                .boxed()
                                                .collect(Collectors.toList());

        return (int) Utils.findAllDistinctPermutations(rankOfCardsAsList).stream()
                .map(permutation -> permutation.stream()
                        .mapToInt(Integer::intValue)
                        .sum())
                .filter(sumVal -> sumVal == TARGET_SCORE_AS_SUM_OF_RANKS)
                .count();

    }

}
