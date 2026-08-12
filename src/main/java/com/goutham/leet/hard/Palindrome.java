package com.goutham.leet.hard;

import java.util.Stack;

public class Palindrome {

    /*two pointers approach*/
    public boolean isPalindromeTwoPointer(String s) {

        int is = 0, j=s.length()-1;

        for(int i=0; i<j; i++, j--){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;

    }

    public boolean isPalindromeNumber(Integer num) {

        int original = num;
        int reversed = 0;
        while(num > 0){
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return original == reversed;

    }

    public boolean isPalindromeStack(String s) {

        Stack<Character> stack = new Stack<>();
        s= s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        for (char c : s.toCharArray()) {
            stack.push(c);
        }

        for (char c : s.toCharArray()) {
            if (stack.pop() != c) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindromeRecursion(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return checkPalindrome(s,0,s.length()-1);

    }
    private boolean checkPalindrome(String s, int start, int end){
        if(start >= end) return true;
        if(s.charAt(start) != s.charAt(end)) return false;
        return checkPalindrome(s,start+1,end-1);
    }

    public boolean isPalindrome(String s) {
        s=s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindromeTwoPointer("AMA"));
        System.out.println(palindrome.isPalindromeNumber(12321));
        System.out.println(palindrome.isPalindromeStack("A man, a plan, a canal: Panama"));
        System.out.println(palindrome.isPalindromeRecursion("A man, a plan, a canal: Panama"));
        System.out.println(palindrome.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
