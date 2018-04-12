package com.leetcode;

public class ReserveString {
    public String reserveString(String str){
        int length = str.length();
        StringBuilder rtnStr = new StringBuilder(length);
        for (int i = length-1; i >=0 ; i--) {
            rtnStr.append(str.charAt(i));
        }
        return rtnStr.toString();
    }

    public static void main(String[] args) {
        String str = "hello";
        ReserveString reserveString = new ReserveString();
        str = reserveString.reserveString(str);
        System.out.println(str);
    }
}
