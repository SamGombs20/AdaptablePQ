package org.example.classes;

import org.example.interfaces.Entry;
import org.example.interfaces.PriorityQueue;

import java.security.Key;
import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V>{
      protected static class PQEntry<K,V> implements Entry<K,V>{
           private K key;
           private V value;
           public PQEntry(K key, V value){
               this.key = key;
               this.value = value;
           }

           @Override
           public K getKey() {
               return key;
           }

           @Override
           public V getValue() {
              return value;
           }
           protected void setKey(K key){
              this.key = key;
           }
           protected void setValue(V value){
              this.value = value;
           }
      }
      private Comparator<K> comp;
      public AbstractPriorityQueue(Comparator<K> c){
          comp = c;
      }
      public AbstractPriorityQueue(){
       this (new DefaultComparator<K>());
      }
      public int compare(Entry<K,V> entry1, Entry<K,V> entry2){
       return comp.compare(entry1.getKey(), entry2.getKey());
      }
      protected boolean checkKey(K key){
       try{
        return comp.compare(key,key)==0;
       }
       catch (ClassCastException e){
        throw new IllegalArgumentException("Incompatible key");
       }
      }
      public boolean isEmpty(){
       return size()==0;
      }
}
