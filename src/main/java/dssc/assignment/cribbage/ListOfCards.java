package dssc.assignment.cribbage;

import java.util.*;

public class ListOfCards implements List<Card> {

    private List<Card> cardList;

    public ListOfCards(List<Card> cardList){
        if(cardList==null)
            throw new NullPointerException("The given list cannot be null");
        this.cardList = new ArrayList<>(cardList);
    }

    public ListOfCards(int howManyCards){
        this.cardList = new ArrayList<>(howManyCards);
    }

    public ListOfCards(){
        this(0);
    }

    public boolean isARun() {
        ListOfCards cardList_localCopy = new ListOfCards(cardList);
        Collections.sort(cardList_localCopy);

        boolean isARun = true;
        for(int i=0; i<cardList_localCopy.size()-1;i++){
            Card aCard = cardList_localCopy.get(i);
            Card otherCard = cardList_localCopy.get(i+1);
            isARun = isARun && aCard.isOneRankFarFromThisCard(otherCard);
        }

        return isARun;
    }

    List<Permutation<Card>> findAllDistinctPermutationsOfNElementsWithoutRepetition(int NCards){

        // TODO refactor  (this method could belong to the class Permutation)

        List<Permutation<Card>> listOfAllPermutations = Utils.findAllDistinctPermutationsOfNElementsWithoutRepetition(cardList,NCards);
        return listOfAllPermutations;
    }

    public static ListOfCards castToThisClass(List<Card> cardListToCast){
        ListOfCards listOfCards_afterCasting = new ListOfCards();
        for(Card card : cardListToCast)
            listOfCards_afterCasting.add(card);
        return listOfCards_afterCasting;
    }

    public ListOfCards removeCards(ListOfCards cardsToRemove){
        if(cardsToRemove==null)
            throw new IllegalArgumentException("The given list of card to remove cannot be null.");

        for(Card card : cardsToRemove) {
            if(!cardList.contains(card))
                throw new RuntimeException("The given card list does not contain the cards you want to remove.");
            cardList.remove(card);
        }

        return this;
    }

    public static List<ListOfCards> convertPermutationToListOfListOfCards(Permutation<Card> permutation){
        if(permutation==null)
            throw new NullPointerException("The given permutation cannot be null.");

        List<ListOfCards> afterConversion = new ArrayList<>();
        for (List<Card> list : permutation)
            afterConversion.add(new ListOfCards(list));

        return afterConversion;
    }

    // returns a list of permutation
    public List<Permutation<Card>> getAllPermutationsOfNConsecutiveCardsWithoutRepetition(int LengthOfTheListOfCardsInThePermutation) {

        List<Permutation<Card>> listOfPermutationsOfCards = findAllDistinctPermutationsOfNElementsWithoutRepetition(LengthOfTheListOfCardsInThePermutation);

        List<Permutation<Card>> listOfPermutationsWhichCorrespondsToDistinctRuns = new ArrayList<>();

        for(Permutation<Card> aPermutation : listOfPermutationsOfCards) {

            Permutation<Card> permutationWhichIsARunInThisPermutation = new Permutation<>();

            for(ListOfCards possibleRun : ListOfCards.convertPermutationToListOfListOfCards(aPermutation)) {
                if (possibleRun.isARun())
                    permutationWhichIsARunInThisPermutation.add(possibleRun);
            }

            if(!permutationWhichIsARunInThisPermutation.isEmpty())
                listOfPermutationsWhichCorrespondsToDistinctRuns.add(permutationWhichIsARunInThisPermutation);
        }

        return listOfPermutationsWhichCorrespondsToDistinctRuns;
    }


    @Override
    public int size() {
        return cardList.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return cardList.contains(o);
    }

    @Override
    public Iterator<Card> iterator() {
        return cardList.iterator();
    }

    @Override
    public Object[] toArray() {
        return cardList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return cardList.toArray(ts);
    }

    @Override
    public boolean add(Card card) {
        return cardList.add(card);
    }

    @Override
    public boolean remove(Object o) {
        return cardList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return cardList.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends Card> collection) {
        return cardList.addAll(collection);
    }

    @Override
    public boolean addAll(int i, Collection<? extends Card> collection) {
        return cardList.addAll(i,collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return cardList.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return cardList.retainAll(collection);
    }

    @Override
    public void clear() {
        cardList.clear();
    }

    @Override
    public Card get(int i) {
        return cardList.get(i);
    }

    @Override
    public Card set(int i, Card card) {
        return cardList.set(i, card);
    }

    @Override
    public void add(int i, Card card) {
        cardList.add(i, card);
    }

    @Override
    public Card remove(int i) {
        return cardList.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        illegalArgumentExceptionIfWrongType(o);
        return cardList.indexOf((Card) o);
    }

    private void illegalArgumentExceptionIfWrongType(Object o){
        if(! (o instanceof Card))
            throw new IllegalArgumentException("Object of type " + Card.class + " expected.");
    }

    @Override
    public int lastIndexOf(Object o) {
        illegalArgumentExceptionIfWrongType(o);
        return cardList.lastIndexOf((Card) o);
    }

    @Override
    public ListIterator<Card> listIterator() {
        return cardList.listIterator();
    }

    @Override
    public ListIterator<Card> listIterator(int i) {
        return cardList.listIterator(i);
    }

    @Override
    public List<Card> subList(int i, int i1) {
        return cardList.subList(i,i1);
    }
}