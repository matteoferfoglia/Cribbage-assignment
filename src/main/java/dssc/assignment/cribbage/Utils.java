package dssc.assignment.cribbage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Utils {

    public static<T> Permutation<T> findAllDistinctPermutations(List<T> listToPermute) {

        if(listToPermute==null)
            throw new IllegalArgumentException("The given list cannot be null.");

        List<T> listToPermute_localCopy = new LinkedList<>(listToPermute);
        Permutation permutation = new Permutation();

        permutation.add(listToPermute);

        for(int i=0; i<listToPermute.size(); i++) {
            List<T> listToExplore = new LinkedList<>(listToPermute_localCopy);

            removeLastElementOfList_destructively(listToExplore);
            permutation.addAll(exploreListAndReturnPermutations(listToExplore));

            Collections.rotate(listToPermute_localCopy,1);
        }

        return permutation;
    }

    public static<T> Permutation<T> findAllDistinctPermutationsOfNElements(List<T> listToPermute,
                                                                          int NElements) {
        // TODO refactor: duplication of code wrt findAllDistinctPermutations()

        if(listToPermute==null)
            throw new IllegalArgumentException("The given list cannot be null.");

        List<T> listToPermute_localCopy = new LinkedList<>(listToPermute);
        Permutation<T> permutation = new Permutation<>();

        if(listToPermute.size() == NElements)
            permutation.add(listToPermute);

        for(int i=0; i<listToPermute.size(); i++) {
            List<T> listToExplore = new LinkedList<>(listToPermute_localCopy);

            removeLastElementOfList_destructively(listToExplore);
            for(List l : exploreListAndReturnPermutations(listToExplore))
                if(l.size()==NElements)
                    permutation.add(l);

            Collections.rotate(listToPermute_localCopy,1);
        }

        return permutation;
    }

    private static<T> Permutation<T> exploreListAndReturnPermutations(List<T> listToExplore){
        Permutation<T> permutations = new Permutation<>();

        if(listToExplore.size() > 0) {

            permutations.add(new LinkedList<>(listToExplore));

            removeLastElementOfList_destructively(listToExplore);
            for (List<T> aListOfThePermutation : exploreListAndReturnPermutations(listToExplore)) {
                if(aListOfThePermutation.size()>0) {
                    permutations.add(aListOfThePermutation);
                }
            }

        }

        return permutations;
    }

    private static<T> void removeLastElementOfList_destructively(List<T> list){
        if(list.size()>0) {
            list.remove(list.size() - 1);
        }
    }

    public static<T> void printList(List<T> list){
        for(T val : list)
            System.out.print(val);
    }

    public static<T> List<Permutation<T>> findAllDistinctPermutationsOfNElementsWithoutRepetition(List<T> listToPermuteWithoutRepetitions,
                                                                                                 int NElements) {
        if(listToPermuteWithoutRepetitions==null)
            throw new IllegalArgumentException("The given list cannot be null.");

        List<Permutation<T>> permutationOfNElementsWithoutRepetitions = new ArrayList<>();
        List<T> listToPermuteWithoutRepetitions_localCopy = new ArrayList<>(listToPermuteWithoutRepetitions);

        // TODO: refactor, this method is very inefficient
        if(listToPermuteWithoutRepetitions.size()==NElements) {
            Permutation<T> wrapperListOfPermutations =  new Permutation<T>();
            wrapperListOfPermutations.add(listToPermuteWithoutRepetitions);
            permutationOfNElementsWithoutRepetitions.add(wrapperListOfPermutations);
        } else {

            for(int i=0; i<listToPermuteWithoutRepetitions_localCopy.size(); i++) {

                List<T> listToPermuteWithoutRepetitions_localCopy_inner = new LinkedList<>(listToPermuteWithoutRepetitions_localCopy);
                Permutation<T> permutationOfNElementsWithoutRepetitions_inner = new Permutation<>();

                for( ;
                     listToPermuteWithoutRepetitions_localCopy_inner.size() >= NElements;
                     removeFirstNElements(listToPermuteWithoutRepetitions_localCopy_inner,NElements)) {

                    Permutation<T> aPermutation = exploreListAndReturnPermutations(new ArrayList<>(listToPermuteWithoutRepetitions_localCopy_inner));

                    for (List<T> listOfThePermutation : aPermutation) {
                        if (listOfThePermutation.size() == NElements) {
                            permutationOfNElementsWithoutRepetitions_inner.add(listOfThePermutation);
                        }
                    }

                }

                permutationOfNElementsWithoutRepetitions.add(permutationOfNElementsWithoutRepetitions_inner);
                Collections.rotate(listToPermuteWithoutRepetitions_localCopy,1);

            }

        }

        return permutationOfNElementsWithoutRepetitions;
    }

    private static void removeFirstNElements(List list, int NElementsToRemove) {

        if(list==null)
            throw new IllegalArgumentException("The given list cannot be null.");

        if(list.size()<NElementsToRemove)
            throw new RuntimeException("The given list is too short: you are trying to remove "
                                        + NElementsToRemove + " from a list which has only "
                                        + list.size() + " elements.");

        for(int i=0; i<NElementsToRemove; i++) {
            list.remove(0);
        }
    }


    // Examples
    public static void main(String[] strings){
        LinkedList<Integer> list = new LinkedList<>(); list.add(1); list.add(2); list.add(2); list.add(3);
        printList(findAllDistinctPermutations(list));

        System.out.println(); System.out.println();

        LinkedList<Character> list2 = new LinkedList<>(); list2.add('A'); list2.add('B'); list2.add('C');
        printList(findAllDistinctPermutations(list2));

        System.out.println(); System.out.println();

        printList(findAllDistinctPermutationsOfNElements(list,3));
        System.out.println();
        printList(findAllDistinctPermutationsOfNElements(list2,3));

        System.out.println(); System.out.println();

        System.out.println((ListOfListOfElements)findAllDistinctPermutationsOfNElementsWithoutRepetition(list,2));
        System.out.println();
        System.out.println((ListOfListOfElements)findAllDistinctPermutationsOfNElementsWithoutRepetition(list,3));

        System.out.println(); System.out.println();

        System.out.println((ListOfListOfElements)findAllDistinctPermutationsOfNElementsWithoutRepetition(list2,2));
        System.out.println();
        System.out.println((ListOfListOfElements)findAllDistinctPermutationsOfNElementsWithoutRepetition(list2,3));

    }

}
