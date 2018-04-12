package com.leetcode;

/**
 *
 */
public class Array2 {
    public int maxAreaOfIsland(int[][] grid) {
        int pro = 0 ;
        for (int i = 0; i <grid.length ; i++) {
            int sum = 0;
            for (int j = 0; j <grid[i].length ; j++) {
                sum +=grid[i][j];
            }
            if (pro<sum){
                pro = sum;
            }
        }
        return  pro;
    }
}
