package dssc.assignment.cribbage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Utils {

    public static<T> List<List<T>> findAllDistinctPermutations(final List<T> listToPermute) {

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
            System.out.println(val);
    }


    // Examples
    public static void main(String[] strings){
        LinkedList<Integer> list = new LinkedList<>(); list.add(1); list.add(2); list.add(2); list.add(3);
        printList(findAllDistinctPermutations(list));

        LinkedList<Character> list2 = new LinkedList<>(); list2.add('A'); list2.add('B'); list2.add('C');
        printList(findAllDistinctPermutations(list2));
    }

}
