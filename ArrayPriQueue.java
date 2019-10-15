package edu.sdsu.cs.datastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * cssc0229 Tommy Cao
 * cssc0210 Ramon Levya
 * CS 310
 **/
public class ArrayPriQueue <E> implements IPriorityQueue<E>{

    private ArrayList queue = new ArrayList();
    private Comparator guage = Comparator.naturalOrder();

    public ArrayPriQueue(){
    }

    public ArrayPriQueue(Comparator userGuage){
        guage = userGuage;
    }


    @Override
    public Comparator<E> comparator() {
        return guage;
    }

    @Override
    public void enqueue(E item) {
        int indexOf = binarySearch(queue,item,0,size());
        queue.add(indexOf, item);
    }

    @Override
    public E poll() {
        return (E) queue.remove(size()-1);
    }


    @Override
    public E peek() {
        if (size() == 0)
            return null;
        return (E) queue.get(size()-1);
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
    public Iterator<E> iterator() {
        return queue.iterator();
    }

    private int binarySearch(ArrayList<E> queue, E target, int low, int high){

        if (low <high){
            int mid = low + (high-low)/2;
            E midItem = queue.get(mid);
            int cmp = comparator().compare(target, midItem);
            if (cmp == 0)
                return mid;
            if (cmp > 0)
                return binarySearch(queue,target,mid+1,high);
            return binarySearch(queue, target, low ,mid);
        }
        return low;
    }
}
