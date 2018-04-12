package com.edu.datastructures.chart1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

//
public class SequenceListTest {
    @Test
    public void addTest(){
        SequenceList<Integer> sequenceList = new SequenceList<Integer>(10);
        for (int i = 0; i <12 ; i++) {
            sequenceList.add(i);
        }
        System.out.println(sequenceList);
    }
    @Test
    public void removeTest(){
        SequenceList<Integer> sequenceList = new SequenceList<Integer>(10);
        for (int i = 0; i <12 ; i++) {
            sequenceList.add(i);
        }
       // sequenceList.remove(23);
        sequenceList.remove(1);
        System.out.println(sequenceList);
    }
    @Test
    public void findTest(){
        SequenceList<Integer> sequenceList = new SequenceList<Integer>(10);
        for (int i = 0; i <12 ; i++) {
            sequenceList.add(i);
        }
        System.out.println(sequenceList.find(10));
        System.out.println(sequenceList.find(15));
        System.out.println(sequenceList.find(25));
    }
    @Test
    public void  modfity(){
        SequenceList<Integer> sequenceList = new SequenceList<Integer>(10);
        for (int i = 0; i <12 ; i++) {
            sequenceList.add(i);
        }
        sequenceList.modify(0,10);
        sequenceList.modify(15,2);
        System.out.println(sequenceList.toString());
        //sequenceList.modify(21,0);
    }
    @Test
    public void clear(){
        SequenceList<Integer> sequenceList = new SequenceList<Integer>(10);
        for (int i = 0; i <12 ; i++) {
            sequenceList.add(i);
        }
        System.out.println(sequenceList.toString());
        sequenceList.clear();
        System.out.println(sequenceList.toString());
    }

    @Test
    public void size(){
        SequenceList<Integer> sequenceList = new SequenceList<Integer>(10);
        for (int i = 0; i <12 ; i++) {
            sequenceList.add(i);
        }
        Assert.assertEquals(sequenceList.size(),12);
    }

    @Test
    public void isEmpty(){
        SequenceList<Integer> sequenceList = new SequenceList<Integer>(10);
        for (int i = 0; i <12 ; i++) {
            sequenceList.add(i);
        }
        System.out.println(sequenceList.toString());
        sequenceList.clear();
        Assert.assertEquals(sequenceList.isEmpty(),true);

       // ArrayList
    }
}
