package com.edu.datastructures.chart1;

import java.util.ArrayList;
import java.util.Arrays;

public class SimpleHashMap<K,V> {
    private Node<K,V>[]  data ;
    private int length ;

    public SimpleHashMap(int ength){
        if(ength<0){
            throw  new IndexOutOfBoundsException();
        }
        length = ength;
        data = new Node[ength];
    }

    public SimpleHashMap(){
        length = 2 ;
        data = new Node[2];
    }

    public  boolean put(K k ,V v){
        if (k == null ){
            return  false;
        }
        int hash = k.hashCode();
        int index = hash%length;
        Node<K,V> node = data[index];
        if(node==null){
            data[index] = new Node<K, V>(k,v);
        }else {
            Node<K,V> head = node;
            boolean flag = true;
            while (node.next!=null){
                if(node.getKey().equals(k)){
                    node.setData(v);
                    flag = false;
                    break;
                }
                node =node.next;
            }
            if(flag){
                node = new Node<K, V>(k,v);
            }
            data[index] = head;
        }
        return  true;
    }

    public V get(K k){
        if(k==null){
            throw  new NullPointerException();
        }
        int index = k.hashCode()%length;
        Node<K,V> node = data[index] ;
        if(node==null){
            return  null ;
        }else {
            while (node.next!=null){
                if(node.getKey().equals(k))
                    return node.getData();
                else
                    node = node.next;
            }
        }
        return  null;
    }

    private static  class Node<K,V>{
        private  Node next ;
        private  V data ;
        private  K key ;

        public Node (K k , V v){
            data = v;
            key = k ;
            next = null;
        }
        public Node (K k ,V v ,Node node){
            data = v;
            key = k ;
            next = node;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public V getData() {
            return data;
        }

        public void setData(V data) {
            this.data = data;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", data=" + data +
                    ", key=" + key +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SimpleHashMap{" +
                "data=" + Arrays.toString(data) +
                ", length=" + length +
                '}';
    }

    public static void main(String[] args) {
        SimpleHashMap<String,Integer> map = new SimpleHashMap<String, Integer>(10);
        for (int i = 0; i <30 ; i++) {
            map.put(i+"key",i);
        }
        System.out.println(map);
        System.out.println(map.get("30key"));
    }
}
