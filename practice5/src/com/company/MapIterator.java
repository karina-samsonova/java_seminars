package com.company;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class MapIterator <K, V> {
    public HashMap<K, V> map;
    public Iterator it;

    //индикаторы для отслеживания IllegalStateException (если метод next() еще не был
    //вызван, или если remove() уже был вызван после последнего вызова next())
    public boolean removeIndicator;
    public boolean nextIndicator;

    public MapIterator(HashMap<K, V> map){
        this.map = map;
        this.it = this.map.entrySet().iterator();
        this.removeIndicator = false;
        this.nextIndicator = false;
    }

    public boolean hasNext(){
        return it.hasNext();
    }

    public Map.Entry<K, V> next(){
        if (!it.hasNext()){
            throw new NoSuchElementException("такого элемента не существует");
        }
        Map.Entry entry = (Map.Entry)it.next();
        nextIndicator = true;
        removeIndicator = false;
        return entry;
    }

    public void remove(){
        if (!nextIndicator){
            throw new IllegalStateException("невозможно вызвать метод remove(), так как метод next() еще не был вызван");
        }
        if (removeIndicator){
            throw new IllegalStateException("метод remove() уже был вызван после последнего вызова next()");
        }
        it.remove();
        removeIndicator = true;
    }
}
