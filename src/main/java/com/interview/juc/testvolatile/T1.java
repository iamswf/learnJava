package com.interview.juc.testvolatile;

/**
 * @author sunwenfei
 * @date 2021-01-04 22:32
 */


/*
    可以通过javap -c classFile来查看本类的Java字节码
    this.num = this.num + 1这行代码包含了3个子步骤
    getfield iadd putfield
    因此this.num = this.num + 1(或者num++)并不是一个原子操作
 */


public class T1 {
    public volatile int num;

    public void addOne() {
        this.num = this.num + 1;
    }
}
