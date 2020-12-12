package dssc.assignment.cribbage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Utils {

    public static<T> List<List<T>> findAllDistinctPermutations(List<T> listToPermute) {

        if(listToPermute==null)
            throw new IllegalArgumentException("The given list cannot be null.");

        List<T> listToPermute_localCopy = new LinkedList<>(listToPermute);
        List<List<T>> permutations = new LinkedList<>();

        permutations.add(listToPermute);

        for(int i=0; i<listToPermute.size(); i++) {
            List<T> listToExplore = new LinkedList<>(listToPermute_localCopy);

            removeLastElementOfList_destructively(listToExplore);
            permutations.addAll(exploreListAndReturnPermutations(listToExplore));

            Collections.rotate(listToPermute_localCopy,1);
        }

        return permutations;
    }

    public static<T> List<List<T>> findAllDistinctPermutationsOfNElements(List<T> listToPermute,
                                                                          int NElements) {
        // TODO refactor: duplication of code wrt findAllDistinctPermutations()

        if(listToPermute==null)
            throw new IllegalArgumentException("The given list cannot be null.");

        List<T> listToPermute_localCopy = new LinkedList<>(listToPermute);
        List<List<T>> permutations = new LinkedList<>();

        if(listToPermute.size() == NElements)
            permutations.add(listToPermute);

        for(int i=0; i<listToPermute.size(); i++) {
            List<T> listToExplore = new LinkedList<>(listToPermute_localCopy);

            removeLastElementOfList_destructively(listToExplore);
            for(List l : exploreListAndReturnPermutations(listToExplore))
                if(l.size()==NElements)
                    permutations.add(l);

            Collections.rotate(listToPermute_localCopy,1);
        }

        return permutations;
    }

    private static<T> List<List<T>> exploreListAndReturnPermutations(List<T> listToExplore){
        List<List<T>> permutations = new LinkedList<>();

        if(listToExplore.size() > 0) {

            permutations.add(new LinkedList<>(listToExplore));

            removeLastElementOfList_destructively(listToExplore);
            for (List<T> permutation : exploreListAndReturnPermutations(listToExplore)) {
                if(permutation.size()>0) {
                    permutations.add(permutation);
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

    public static<T> List<List<List<T>>> findAllDistinctPermutationsOfNElementsWithoutRepetition(List<T> listToPermuteWithoutRepetitions,
                                                                                                 int NElements) {
        if(listToPermuteWithoutRepetitions==null)
            throw new IllegalArgumentException("The given list cannot be null.");

        List<List<List<T>>> permutationOfNElementsWithoutRepetitions = new LinkedList<>();
        List<T> listToPermuteWithoutRepetitions_localCopy = new LinkedList<>(listToPermuteWithoutRepetitions);

        // TODO: refactor, this method is very inefficient
        if(listToPermuteWithoutRepetitions.size()==NElements) {
            List<List<T>> wrapperListOfPermutations =  new LinkedList<List<T>>();
            wrapperListOfPermutations.add(listToPermuteWithoutRepetitions);
            permutationOfNElementsWithoutRepetitions.add(wrapperListOfPermutations);
        } else {

            for(int i=0; i<listToPermuteWithoutRepetitions_localCopy.size(); i++) {

                List<T> listToPermuteWithoutRepetitions_localCopy_inner = new LinkedList<>(listToPermuteWithoutRepetitions_localCopy);
                List<List<T>> permutationOfNElementsWithoutRepetitions_inner = new LinkedList<>();

                for( ;
                     listToPermuteWithoutRepetitions_localCopy_inner.size() >= NElements;
                     removeFirstNElements(listToPermuteWithoutRepetitions_localCopy_inner,NElements)) {

                    List<List<T>> allPermutations = exploreListAndReturnPermutations(new LinkedList<>(listToPermuteWithoutRepetitions_localCopy_inner));

                    for (List<T> permutation : allPermutations) {
                        if (permutation.size() == NElements) {
                            permutationOfNElementsWithoutRepetitions_inner.add(permutation);
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

    public static<T> void printListOfList(List<List<T>> list) {

        System.out.println("<");
        for (List l : list) {
            System.out.print("\t<");
            printList(l);
            System.out.println(">");
        }
        System.out.println(">");

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

        printListOfList(findAllDistinctPermutationsOfNElementsWithoutRepetition(list,2));
        System.out.println();
        printListOfList(findAllDistinctPermutationsOfNElementsWithoutRepetition(list,3));

        System.out.println(); System.out.println();

        printListOfList(findAllDistinctPermutationsOfNElementsWithoutRepetition(list2,2));
        System.out.println();
        printListOfList(findAllDistinctPermutationsOfNElementsWithoutRepetition(list2,3));

    }

}
