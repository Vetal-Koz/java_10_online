package org.example;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1,2,3,4,5};
        MatList<Integer> matList = new MatList<>(array);
        MatList<Integer> matList2 = new MatList<>();
        for (int i = 6; i < 11; i++) {
            matList2.add(i);
        }

        MatList<Integer> matList3  = new MatList<>(matList, matList2);

        matList3.add(1,2,3,4);
        
    }
}