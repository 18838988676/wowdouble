package com.edu.datastructures.chart1;

import java.util.Arrays;

/**
 * 顺序存储的线性表
 */
public class SequenceList<T> {
    private int length ;
    private  static  int defaultSize = 10 ;
    private  T[] data ;

    private int size;
    //构造方法
    public SequenceList() {
        this.length = defaultSize;
        data = (T[])new Object[length];
    }
    public SequenceList(int length) {
        if(length<=0){
            throw  new IndexOutOfBoundsException();
        }
        this.length = length;
        data = (T[])new Object[length];
    }

    //新增
    public boolean add(T t ){
        if (size==length){
            //扩容
            length = length*2;
            T[] newData = (T[])new Object[length];
            for (int i = 0; i <size ; i++) {
                newData[i]=data[i];
            }
            data = newData;
        }
        data[size] = t ;
        size++;
        return true;
    }
    //删除
    public  T remove (int index ){
        if (index>size||index<0){
            throw new IndexOutOfBoundsException();
        }
        T old = data[index];
        for (int i = index; i <size-1 ; i++) {
            data[i] = data[i+1];
        }
        size--;
        return  old;
    }
    //修改
    public T modify(int index , T t ){
        if (index<0||index>size)
            throw new IndexOutOfBoundsException();
        T old = data[index];
        data[index] = t ;
        return  old ;
    }
    //查找
    public  int find(T t ){
        int index = -1 ;
        for (int i = 0; i <size ; i++) {
            if (t==data[i]||t.equals(data[i]))
                return i;
        }
        return  index;
    }
    //大小
    public int size(){
        return  size;
    }
    //是否空
    public boolean isEmpty(){
        return  size==0?true:false;
    }
    //清空
    public  void clear(){
        size = 0 ;
        data = (T[])new Object[length];
    }

    //toString()

    @Override
    public String toString() {
        return "SequenceList{" +
                "length=" + length +
                ", data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
