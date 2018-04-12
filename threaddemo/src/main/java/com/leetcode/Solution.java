package com.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *给的一个int数组和一个int值, 数组中三个数据和与int最接近的 和.
 */
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int res = -1 ;
        //排序
        Arrays.sort(nums);
        //连续三个相加,与target算差值,如果后一个差值比前一个大,那么前一个就是.跳槽循环
        Map<Integer,Integer> ints = new HashMap<Integer,Integer>(nums.length-2);
        int pro = Integer.MAX_VALUE ;
        for (int i = 0; i <nums.length-2 ; i++) {
           int c = nums[i]+nums[i+1]+nums[i+2]-target;
           c = c < 0 ? -c : c ;
           if(pro<c){
                res = nums[i+1] ;
                break;
           }else {
               pro = c ;
           }
        }
        return  res ;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int [] ints = {-1 ,2 ,1, -4};
        int target = 1 ;
        int res = s.threeSumClosest(ints,target);
        System.out.println(res);
    }
}
