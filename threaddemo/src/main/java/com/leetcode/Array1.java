package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

 You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class Array1 {
    public int majorityElement(int[] nums) {
        int half = nums.length/2 ;
        Map<Integer,Integer> r = new HashMap<Integer, Integer>(nums.length);
        for (int i = 0; i <nums.length ; i++) {
            int curNum = nums[i] ;
           Integer times =  r.get(curNum);
            if(times==null){
                r.put(curNum,1);
            }else{
                r.put(curNum,times+1);
                if(times>half){
                    return curNum;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int [] nums ={1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,3,3,3,4,4,5,5,6};
        Array1 array1 = new Array1();
        int s = array1.majorityElement(nums);
        System.out.println(s);
    }
}
