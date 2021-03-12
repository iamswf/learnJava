package com.interview.juc.testvolatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定义一个位于堆内存上的共享对象
 * 堆内存上对象的成员属性也位于堆内存上，也是共享的
 */
class SharedData {
    // int num = 0;
    volatile int num = 0;

    AtomicInteger atomicIntegerNum = new AtomicInteger(0);

    public void setToNewNum(int newNum) {
        this.num = newNum;
    }

    public void addOne() {
        // num++;
        this.num = this.num + 1;
    }

    public void atomicAddOne() {
        this.atomicIntegerNum.getAndIncrement();
    }
}

/**
 * @author sunwenfei
 * @date 2021-01-03 13:30
 */
public class TestVolatile {
    /*
    Q: Volatile的3大特性是什么?
    A:
        1. 保证变量的可见性(每次读取Volatile声明的变量时都从主内存读取，每次更新完Volatile声明的变量时都立即写回主内存)
        2. 不保证原子性
        3. 禁止指令重排

    参考资料：
        1. http://tutorials.jenkov.com/java-concurrency/java-memory-model.html
        2. https://www.youtube.com/watch?v=GEZEkK0ukIE&list=PLmOn9nNkQxJGEztWTgs2r-ihSAQtcgaCi&index=4
     */

    public static void main(String[] args) {
        // testGuaranteeVisibility();
        testNoGuaranteeAtomic();
    }

    /**
     * 不保证原子性代码验证
     */
    private static void testNoGuaranteeAtomic() {
        SharedData sharedData = new SharedData();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    sharedData.addOne();
                    sharedData.atomicAddOne();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println("sharedData.num最终值为：" + sharedData.num);
        System.out.println("sharedData.atomicIntegerNum：" + sharedData.atomicIntegerNum);
    }

    /**
     * 保证可见性代码验证
     */
    private static void testGuaranteeVisibility() {
        SharedData sharedData = new SharedData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "开始执行.");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sharedData.setToNewNum(60);
            System.out.println("更新后的变量值为：" + sharedData.num);
        }, "aaa").start();

        while (sharedData.num == 0) {
            // 循环直到感知到sharedData.num发布变化
        }
        System.out.println("sharedData.num值发送变化，值为：" + sharedData.num);
    }
}
