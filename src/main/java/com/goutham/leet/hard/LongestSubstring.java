package com.goutham.leet.hard;

import java.util.HashSet;
import java.util.Set;

//Longest Substring Without Repeating Characters
public class LongestSubstring {

    public  int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0) return 0;

        int left = 0, maxLength = 0;

        Set<Character> seen = new HashSet<>();

        for( int right = 0; right < s.length(); right++ ){
            char currentChar = s.charAt(right);

            while(seen.contains(currentChar)){
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(currentChar);
            int length = right - left + 1;
            maxLength = Math.max(maxLength, length);

        }

        return maxLength;
    }
}
