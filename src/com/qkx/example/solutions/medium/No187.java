package com.qkx.example.solutions.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by qkx on 17/4/6.
 */
public class No187 {
    /**
     * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
     * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
     * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
     * For example,
     * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
     * Return:
     * ["AAAAACCCCC", "CCCCCAAAAA"].
     */

    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() < 10) return new LinkedList<>();

        Set<String> set = new HashSet<>();
        Set<String> result = new HashSet<>();

        int length = s.length();
        for (int i = 0; i <= length - 10; i++) {
            String subString = s.substring(i, i + 10);
            if (!set.add(subString)) {
                result.add(subString);
            }
        }

        return new LinkedList<>(result);
    }
}
