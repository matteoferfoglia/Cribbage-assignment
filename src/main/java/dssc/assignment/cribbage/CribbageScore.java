package dssc.assignment.cribbage;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;


public class CribbageScore {

    public static int scoreComputedAccordingToTheRuleOfFifteenTwos(ListOfCards cards) {
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

    public static int scoreComputedAccordingToTheRuleOfRuns(ListOfCards cardList) {

        // TODO refactor: you may use a recursive function
        // TODO refactor: code smells: long class and duplication of code

        final int scoreWithARunOfThreeConsecutiveCards = 3 ;  //three points for a run of three consecutive cards (regardless of suit)
        final int scoreWithARunOfFourConsecutiveCards = 4;  //four points for a run of four
        final int scoreWithARunOfFiveConsecutiveCards = 5;  //five points for a run of five

        List<Integer> possibleScores = new ArrayList<>();

        // Try with all possible combinations: the score is the one with max value
        for(Permutation<Card> aPermutationOf3ConsecutiveCardsWithoutRepetition : cardList.getAllPermutationsOfNConsecutiveCardsWithoutRepetition( 3)) {

            int howManyRunsOf3InThisPermutation = 0;
            for (ListOfCards aRunOfThreeConsecutiveCards : ListOfCards.convertPermutationToListOfListOfCards(aPermutationOf3ConsecutiveCardsWithoutRepetition)) {

                howManyRunsOf3InThisPermutation ++ ;
                ListOfCards cardList_localCopyForRuleOfThreeConsecutiveCards = new ListOfCards(cardList);
                cardList_localCopyForRuleOfThreeConsecutiveCards.removeCards(aRunOfThreeConsecutiveCards);

                List<Permutation<Card>> allPermutationsOf4ConsecutiveCardsWithoutRepetition = cardList_localCopyForRuleOfThreeConsecutiveCards.getAllPermutationsOfNConsecutiveCardsWithoutRepetition(4);

                for (Permutation<Card> aPermutationOf4 : allPermutationsOf4ConsecutiveCardsWithoutRepetition) {

                    int howManyRunsOf4InThisPermutation = 0;
                    for (ListOfCards aRunOfFourConsecutiveCards : ListOfCards.convertPermutationToListOfListOfCards(aPermutationOf4)) {
                        howManyRunsOf4InThisPermutation ++ ;

                        ListOfCards cardList_localCopyForRuleOfFourConsecutiveCards = new ListOfCards(cardList_localCopyForRuleOfThreeConsecutiveCards);
                        cardList_localCopyForRuleOfFourConsecutiveCards.removeCards(aRunOfFourConsecutiveCards);

                        List<Permutation<Card>> allPermutationsOf5ConsecutiveCardsWithoutRepetition = cardList_localCopyForRuleOfFourConsecutiveCards.getAllPermutationsOfNConsecutiveCardsWithoutRepetition(5);

                        for (Permutation<Card> aPermutationOf5 : allPermutationsOf5ConsecutiveCardsWithoutRepetition) {
                            int howManyRunsOf5InThisPermutation = 0;
                            for (ListOfCards aRunOfFiveConsecutiveCards : ListOfCards.convertPermutationToListOfListOfCards(aPermutationOf5)) {
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
        for(Permutation<Card> aPermutationOf4ConsecutiveCardsWithoutRepetition : cardList.getAllPermutationsOfNConsecutiveCardsWithoutRepetition( 4)) {

            int howManyRunsOf4InThisPermutation = 0;
            for (ListOfCards aRunOfFourConsecutiveCards : ListOfCards.convertPermutationToListOfListOfCards(aPermutationOf4ConsecutiveCardsWithoutRepetition)) {

                howManyRunsOf4InThisPermutation ++ ;
                ListOfCards cardList_localCopyForRuleOfFourConsecutiveCards = new ListOfCards(cardList);
                cardList_localCopyForRuleOfFourConsecutiveCards.removeCards(aRunOfFourConsecutiveCards);

                List<Permutation<Card>> allPermutationsOf5ConsecutiveCardsWithoutRepetition = cardList_localCopyForRuleOfFourConsecutiveCards.getAllPermutationsOfNConsecutiveCardsWithoutRepetition(5);

                for (Permutation<Card> aPermutationOf5 : allPermutationsOf5ConsecutiveCardsWithoutRepetition) {

                    int howManyRunsOf5InThisPermutation = 0;
                    for (ListOfCards aRunOfFiveConsecutiveCards : ListOfCards.convertPermutationToListOfListOfCards(aPermutationOf5)) {
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
        for(Permutation<Card> aPermutationOf5ConsecutiveCardsWithoutRepetition : cardList.getAllPermutationsOfNConsecutiveCardsWithoutRepetition( 5)) {

            int howManyRunsOf5InThisPermutation = 0;
            for (ListOfCards aRunOfFiveConsecutiveCards : ListOfCards.convertPermutationToListOfListOfCards(aPermutationOf5ConsecutiveCardsWithoutRepetition)) {
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

}
