package com.leetcode;

/**
 *Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 If the last word does not exist, return 0.
 */
public class LegthOfLastWord {
    public static int lengthOfLastWord(String s) {
        int rtn = 0 ; int index = -1 ;
        if ( (index = s.lastIndexOf(" "))==s.length()){
            return  rtn;
        }
        rtn = s.substring(index+1).length();
        return rtn;
    }

    public static void main(String[] args) {
        String s = "hello world";
       System.out.println( lengthOfLastWord(s));
    }
}
