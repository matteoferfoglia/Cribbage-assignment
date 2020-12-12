package dssc.assignment.cribbage;

import java.util.*;

public class ListOfListOfElements<E> implements List<List<E>> {

    protected List<List<E>> listOfListOfElements;

    public ListOfListOfElements(List<List<E>> listOfListOfElements) {
        if(listOfListOfElements==null)
            throw new IllegalArgumentException("The argument cannot be null.");

        this.listOfListOfElements = listOfListOfElements;
    }

    @Override
    public String toString() {

        String str = "";

        str += "[" + System.lineSeparator();
        for (List<E> list : listOfListOfElements) {
            str += "\t"
                    + Arrays.toString(listOfListOfElements.toArray())
                    + System.lineSeparator();
        }
        str += "]" + System.lineSeparator();

        return str;
    }

    @Override
    public int size() {
        return listOfListOfElements.size();
    }

    @Override
    public boolean isEmpty() {
        return listOfListOfElements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return listOfListOfElements.contains(o);
    }

    @Override
    public Iterator<List<E>> iterator() {
        return listOfListOfElements.iterator();
    }

    @Override
    public Object[] toArray() {
        return listOfListOfElements.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return listOfListOfElements.toArray(ts);
    }

    @Override
    public boolean add(List<E> es) {
        return listOfListOfElements.add(es);
    }

    @Override
    public boolean remove(Object o) {
        return listOfListOfElements.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return listOfListOfElements.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends List<E>> collection) {
        return listOfListOfElements.addAll(collection);
    }

    @Override
    public boolean addAll(int i, Collection<? extends List<E>> collection) {
        return listOfListOfElements.addAll(i,collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return listOfListOfElements.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return listOfListOfElements.retainAll(collection);
    }

    @Override
    public void clear() {
        listOfListOfElements.clear();
    }

    @Override
    public List<E> get(int i) {
        return listOfListOfElements.get(i);
    }

    @Override
    public List<E> set(int i, List<E> es) {
        return listOfListOfElements.set(i, es);
    }

    @Override
    public void add(int i, List<E> es) {
        listOfListOfElements.add(i, es);
    }

    @Override
    public List<E> remove(int i) {
        return listOfListOfElements.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return listOfListOfElements.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return listOfListOfElements.lastIndexOf(o);
    }

    @Override
    public ListIterator<List<E>> listIterator() {
        return listOfListOfElements.listIterator();
    }

    @Override
    public ListIterator<List<E>> listIterator(int i) {
        return listOfListOfElements.listIterator(i);
    }

    @Override
    public List<List<E>> subList(int i, int i1) {
        return listOfListOfElements.subList(i,i1);
    }
}