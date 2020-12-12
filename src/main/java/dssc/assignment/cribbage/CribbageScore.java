package dssc.assignment.cribbage;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CribbageScore {

    public static int scoreComputedAccordingToTheRuleOfFifteenTwos(List<Card> cards) {
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

    public static int scoreComputedAccordingToTheRuleOfRuns(List<Card> cardList) {

        // TODO refactor: you may use a recursive function

        final int scoreWithARunOfThreeConsecutiveCards = 3 ;  //three points for a run of three consecutive cards (regardless of suit)
        final int scoreWithARunOfFourConsecutiveCards = 4;  //four points for a run of four
        final int scoreWithARunOfFiveConsecutiveCards = 5;  //five points for a run of five

        List<Integer> possibleScores = new ArrayList<>();

        // Try with all possible combinations: the score is the one with max value
        for(List<List<Card>> aPermutationOf3ConsecutiveCardsWithoutRepetition : getAllPermutationsOfNConsecutiveCardsWithoutRepetition(cardList, 3)) {

            int howManyRunsOf3InThisPermutation = 0;
            for (List<Card> aRunOfThreeConsecutiveCards : aPermutationOf3ConsecutiveCardsWithoutRepetition) {

                howManyRunsOf3InThisPermutation ++ ;
                List<Card> cardList_localCopyForRuleOfThreeConsecutiveCards = new ArrayList<>(cardList);
                Utils.removeCards(cardList_localCopyForRuleOfThreeConsecutiveCards, aRunOfThreeConsecutiveCards);

                List<List<List<Card>>> allPermutationsOf4ConsecutiveCardsWithoutRepetition = getAllPermutationsOfNConsecutiveCardsWithoutRepetition(cardList_localCopyForRuleOfThreeConsecutiveCards, 4);

                for (List<List<Card>> aPermutationOf4 : allPermutationsOf4ConsecutiveCardsWithoutRepetition) {

                    int howManyRunsOf4InThisPermutation = 0;
                    for (List<Card> aRunOfFourConsecutiveCards : aPermutationOf4) {
                        howManyRunsOf4InThisPermutation ++ ;

                        List<Card> cardList_localCopyForRuleOfFourConsecutiveCards = new ArrayList<>(cardList_localCopyForRuleOfThreeConsecutiveCards);
                        Utils.removeCards(cardList_localCopyForRuleOfFourConsecutiveCards, aRunOfFourConsecutiveCards);

                        List<List<List<Card>>> allPermutationsOf5ConsecutiveCardsWithoutRepetition = getAllPermutationsOfNConsecutiveCardsWithoutRepetition(cardList_localCopyForRuleOfFourConsecutiveCards, 5);

                        for (List<List<Card>> aPermutationOf5 : allPermutationsOf5ConsecutiveCardsWithoutRepetition) {
                            int howManyRunsOf5InThisPermutation = 0;
                            for (List<Card> aRunOfFiveConsecutiveCards : aPermutationOf5) {
                                howManyRunsOf5InThisPermutation ++ ;
                            }
                            possibleScores.add(scoreWithARunOfFiveConsecutiveCards*howManyRunsOf5InThisPermutation + scoreWithARunOfFourConsecutiveCards*howManyRunsOf4InThisPermutation + scoreWithARunOfThreeConsecutiveCards*howManyRunsOf3InThisPermutation);
                        }

                        if (allPermutationsOf5ConsecutiveCardsWithoutRepetition.isEmpty())
                            possibleScores.add(scoreWithARunOfFourConsecutiveCards*howManyRunsOf4InThisPermutation + scoreWithARunOfThreeConsecutiveCards*howManyRunsOf3InThisPermutation);

                    }
                }

                if (allPermutationsOf4ConsecutiveCardsWithoutRepetition.isEmpty())
                    possibleScores.add(scoreWithARunOfThreeConsecutiveCards*howManyRunsOf3InThisPermutation);
            }

            if (aPermutationOf3ConsecutiveCardsWithoutRepetition.isEmpty())
                possibleScores.add(0);

        }



        // ----------------------------- Without considering runs of 3 cards
        for(List<List<Card>> aPermutationOf4ConsecutiveCardsWithoutRepetition : getAllPermutationsOfNConsecutiveCardsWithoutRepetition(cardList, 4)) {

            int howManyRunsOf4InThisPermutation = 0;
            for (List<Card> aRunOfFourConsecutiveCards : aPermutationOf4ConsecutiveCardsWithoutRepetition) {

                howManyRunsOf4InThisPermutation ++ ;
                List<Card> cardList_localCopyForRuleOfFourConsecutiveCards = new ArrayList<>(cardList);
                Utils.removeCards(cardList_localCopyForRuleOfFourConsecutiveCards, aRunOfFourConsecutiveCards);

                List<List<List<Card>>> allPermutationsOf5ConsecutiveCardsWithoutRepetition = getAllPermutationsOfNConsecutiveCardsWithoutRepetition(cardList_localCopyForRuleOfFourConsecutiveCards, 5);

                for (List<List<Card>> aPermutationOf5 : allPermutationsOf5ConsecutiveCardsWithoutRepetition) {

                    int howManyRunsOf5InThisPermutation = 0;
                    for (List<Card> aRunOfFiveConsecutiveCards : aPermutationOf5) {
                        howManyRunsOf5InThisPermutation ++ ;
                    }
                    possibleScores.add(scoreWithARunOfFiveConsecutiveCards*howManyRunsOf5InThisPermutation + scoreWithARunOfFourConsecutiveCards*howManyRunsOf4InThisPermutation);

                }

                if (allPermutationsOf5ConsecutiveCardsWithoutRepetition.isEmpty())
                    possibleScores.add(scoreWithARunOfFourConsecutiveCards*howManyRunsOf4InThisPermutation);
            }

            if (aPermutationOf4ConsecutiveCardsWithoutRepetition.isEmpty())
                possibleScores.add(0);

        }





        // ----------------------------- Without considering runs neither of 3 nor of 4 cards
        for(List<List<Card>> aPermutationOf5ConsecutiveCardsWithoutRepetition : getAllPermutationsOfNConsecutiveCardsWithoutRepetition(cardList, 5)) {

            int howManyRunsOf5InThisPermutation = 0;
            for (List<Card> aRunOfFiveConsecutiveCards : aPermutationOf5ConsecutiveCardsWithoutRepetition) {
                howManyRunsOf5InThisPermutation ++ ;
            }
            possibleScores.add(scoreWithARunOfFiveConsecutiveCards*howManyRunsOf5InThisPermutation);

            if (aPermutationOf5ConsecutiveCardsWithoutRepetition.isEmpty())
                possibleScores.add(0);

        }



        // ----------------------------- Get max score

        int scoreAccordingToThisRule = 0;

        try {
            scoreAccordingToThisRule = possibleScores.stream().mapToInt(x -> x).max().getAsInt();
        } catch (NoSuchElementException e) {}

        return scoreAccordingToThisRule ;

    }

    // returns a list of permutation
    private static List<List<List<Card>>> getAllPermutationsOfNConsecutiveCardsWithoutRepetition(List<Card> cards, int NCards) {
        List<List<List<Card>>> listOfPermutationsOfCards = Utils.findAllDistinctPermutationsOfNCardsWithoutRepetition(cards, NCards);

        List<List<List<Card>>> listOfPermutationsWhichCorrespondsToDistinctRuns = new ArrayList<>();

        for(List<List<Card>> aPermutation : listOfPermutationsOfCards) {

            List<List<Card>> cardListWhichAreARunInThisPermutation = new ArrayList<>();

            for(List<Card> possibleRun : aPermutation) {
                if (Utils.isARun(possibleRun))
                    cardListWhichAreARunInThisPermutation.add(possibleRun);
            }

            if(!cardListWhichAreARunInThisPermutation.isEmpty())
                listOfPermutationsWhichCorrespondsToDistinctRuns.add(cardListWhichAreARunInThisPermutation);
        }

        return listOfPermutationsWhichCorrespondsToDistinctRuns;
    }

}
