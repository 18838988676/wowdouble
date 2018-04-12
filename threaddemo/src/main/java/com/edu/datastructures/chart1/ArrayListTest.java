package com.edu.datastructures.chart1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ArrayListTest {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<Integer>();
        for (int i = 0; i <5 ; i++) {
            list1.add(i);
        }
        List<Integer> list2 = new ArrayList<Integer>();
        for (int i = 3; i <10 ; i++) {
            list2.add(i);
        }
        int i = list1.size();int j = list2.size();
        List<Integer> list = new ArrayList<Integer>(i+j);
        for (int k = 0; k <i+j ; k++) {
            list.add(-1);
        }
        System.out.println(list.size());
        System.out.println(list);
    }
}
