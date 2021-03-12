package com.book.javathreads.examples.ch02.example1;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sunwenfei
 * @date 2020-11-27 22:42
 */
public class Factorial {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int fact = 1;
        while (n > 1) {
            fact = fact * n;
            n = n - 1;
        }
        System.out.println("fact = " + fact);
    }
}
