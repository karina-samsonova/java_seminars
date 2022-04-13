package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        HashMap<Integer, String> alphabet = new HashMap<>();
        alphabet.put(1,"Aa");
        alphabet.put(2,"Bb");
        alphabet.put(3,"Cc");
        alphabet.put(4,"Dd");
        alphabet.put(5,"Ee");

        MapIterator<Integer, String> it = new MapIterator<>(alphabet);
        it.next();
        it.remove();    //удалили первый элемент из словаря
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
