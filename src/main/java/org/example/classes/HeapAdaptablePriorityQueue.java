package org.example.classes;

import org.example.interfaces.AdaptablePriorityQueue;
import org.example.interfaces.Entry;
import java.util.Comparator;
import java.util.Iterator;

public class HeapAdaptablePriorityQueue<K,V> extends HeapPriorityQueue<K,V>
                                             implements AdaptablePriorityQueue<K,V>,Iterable<Entry<K,V>> {
    public HeapAdaptablePriorityQueue( ) { super( ); }
    public HeapAdaptablePriorityQueue(Comparator<K> comp) { super(comp);}
    public class AdaptablePQIterator implements Iterator<Entry <K,V>>{
        private int j= 0;
        @Override
        public boolean hasNext() {
            return j<size();
        }

        @Override
        public Entry<K,V> next() {
            return heap.get(j++);
        }
    }
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new AdaptablePQIterator();
    }

    public static class AdaptablePQEntry<K,V> extends PQEntry<K,V>{
        private int index;
        public AdaptablePQEntry(K key, V value, int j){
            super(key, value);
            index = j;
        }
        protected void setIndex(int index){
            this.index = index;
        }
        protected int getIndex(){
            return this.index;
        }
    }

    protected AdaptablePQEntry<K,V> validate(Entry<K,V> entry) throws IllegalArgumentException {
        if (!(entry instanceof AdaptablePQEntry<K, V> locator)) throw new IllegalArgumentException("Invalid entry");
        int j = locator.getIndex( );
        if (j >= heap.size( ) || heap.get(j) != locator) throw new IllegalArgumentException("Invalid entry");
        return locator;
    }
    protected void swap(int i, int j) {
        super.swap(i,j);
        ((AdaptablePQEntry<K,V>) heap.get(i)).setIndex(i);
        ((AdaptablePQEntry<K,V>) heap.get(j)).setIndex(j);
    }

    protected void bubble(int j) {
        if (j > 0 && compare(heap.get(j), heap.get(parent(j))) < 0)
            upHeap(j);
        else
            downHeap(j);
    }
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new AdaptablePQEntry<>(key, value, heap.size( ));
        heap.add(newest);
        upHeap(heap.size( ) - 1);
        return newest;
    }
    public void remove(Entry<K,V> entry) throws IllegalArgumentException {
        AdaptablePQEntry<K,V> locator = validate(entry);
        int j = locator.getIndex( );
        if (j == heap.size( ) -1) heap.remove(heap.size( ) - 1);
        else {
            swap(j, heap.size( ) -1);
            heap.remove(heap.size( ) - 1);
            bubble(j);
        }
    }

    public void replaceKey(Entry<K,V> entry, K key) throws IllegalArgumentException {
        AdaptablePQEntry<K,V> locator = validate(entry);
        checkKey(key);
        locator.setKey(key);
        bubble(locator.getIndex( ));
    }
    public void replaceValue(Entry<K,V> entry, V value) throws IllegalArgumentException {
        AdaptablePQEntry<K,V> locator = validate(entry);
        locator.setValue(value);
    }
    public Entry<K,V> get(int i){
        return heap.get(i);
    }

}
