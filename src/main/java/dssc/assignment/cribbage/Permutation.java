package dssc.assignment.cribbage;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for modelling a permutation of a list.
 */
public class Permutation<TypeOfElements> extends ListOfListOfElements<TypeOfElements>{

    private List<List<TypeOfElements>> permutation; // Permutation of a list is a list of list

    public Permutation(List<List<TypeOfElements>> listOfListOfElements) {
        super(listOfListOfElements);
        copyReferenceOfSuperClass();
    }

    private void copyReferenceOfSuperClass(){
        permutation = super.listOfListOfElements;
    }

    public Permutation(){
        this(new ArrayList<>());
    }
}
