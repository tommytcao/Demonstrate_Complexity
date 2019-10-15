package edu.sdsu.cs.datastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * cssc0229 Tommy Cao
 * cssc0210 Ramon Levya
 * CS 310
 **/

public class UnorderedPriQueue <E> implements IPriorityQueue <E> {


    private ArrayList queue = new ArrayList();
    private Comparator guage = Comparator.naturalOrder();

    public UnorderedPriQueue() {
    }

    public UnorderedPriQueue(Comparator userGuage) {
        guage = userGuage;
    }

    @Override
    public Comparator<E> comparator() {
        return guage;
    }

    @Override
    public void enqueue(E item) {
        queue.add(item);
    }

    @Override
    public E poll() {
        int index = (sequentialSearch(queue));
        return (E) queue.remove(index);
    }

    @Override
    public E peek() {
        if(size()==0){
            return null;
        }
        int index = (sequentialSearch(queue));
        return (E) queue.get(index);
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return queue.iterator();
    }

    private int sequentialSearch(ArrayList<E> queue) {
        E obj1;
        E obj2;
        int biggestLocation=0;
        int currentLocation;

        if (size() > 0) {
            for (int counter = 1; counter < size(); counter++) {
                currentLocation = counter;

                obj1 = queue.get(biggestLocation);
                obj2 = queue.get(currentLocation);
                if (comparator().compare(obj1, obj2) < 0)
                    biggestLocation = currentLocation;
            }
            return biggestLocation;
        }

        return -1;
    }
}
