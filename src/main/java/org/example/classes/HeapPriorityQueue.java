package org.example.classes;

import org.example.interfaces.Entry;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

    public HeapPriorityQueue(){super();}
    public HeapPriorityQueue(Comparator<K> comparator){super(comparator);}
    protected int parent(int j){
        return (j-1)/2;
    }
    protected int leftChild(int j){
        return 2*j +1;
    }
    protected int rightChild(int j){
        return 2*j +2;
    }
    protected boolean hasLeft(int j){
        return (leftChild(j)<heap.size());
    }
    protected boolean hasRight(int j){
        return rightChild(j)< heap.size();
    }
    protected void swap(int i, int j){
        Entry<K,V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    protected void upHeap(int j){
        while (j>0){
            int p = parent(j);
            if(compare(heap.get(j), heap.get(p))>=0) break;
            swap(j,p);
            j=p;
        }
    }
    protected void downHeap(int j){
        while (hasLeft(j)){
            int smallChildIndex = leftChild(j);
            if(hasRight(j)){
                int rightIndex = rightChild(j);
                if(compare(heap.get(smallChildIndex), heap.get(rightIndex))>0){
                    smallChildIndex = rightIndex;
                }
            }
            if(compare(heap.get(smallChildIndex), heap.get(j))>=0) break;
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }
    public int size(){
        return heap.size();
    }
    public Entry<K,V> min(){
        if(heap.isEmpty()) return null;
        return heap.get(0);
    }

    @Override
    public Entry<K, V> removeMin() {
        if(heap.isEmpty()) return null;
        Entry<K,V> removed = heap.get(0);
        swap(0, heap.size()-1);
        heap.remove(heap.size()-1);
        downHeap(0);
        return removed;
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upHeap(heap.size()-1);
        return newest;
    }

}
