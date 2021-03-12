package com.book.javaconcurrencyinpractice.chapter1;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * @author sunwenfei
 * @date 2021-02-23 17:21
 */
public class Listing1_1 {
    public static void main(String[] args) {
        // singleThreadRun();
        multiThreadRun();
    }

    private static void singleThreadRun() {
        UnsafeSequence unsafeSequence = new UnsafeSequence();
        for (int i = 0; i < 1000; i++) {
            int next = unsafeSequence.getNext();
            System.out.println(next);
        }
    }

    private static void multiThreadRun() {
        UnsafeSequence unsafeSequence = new UnsafeSequence();
        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 500; j++) {
                        int next = unsafeSequence.getNext();
                        System.out.println(next);
                    }
                }
            });
            thread.start();
        }
    }
}

@NotThreadSafe
class UnsafeSequence {
    private int value;

    /** Returns a unique value. */
    public synchronized int getNext() {
        return value++;
    }
}



