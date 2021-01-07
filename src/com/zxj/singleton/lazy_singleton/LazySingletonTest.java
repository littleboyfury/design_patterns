package com.zxj.singleton.lazy_singleton;

/**
 * @author littleboy
 * @description 懒加载单例模式
 * @date 2020/2/28 23:44
 */
public class LazySingletonTest {

    public static void main(String[] args) {
        // 多线程调用也是单例
        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();
        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();
    }
}

/**
 * 懒汉模式：当实际调用的时候才去创建对象，避免启动时加载过多的对象
 */
class LazySingleton {

    // volatile 防止指令重排
    private volatile static LazySingleton instance;

    // 私有的构造方法，禁止外部创建对象
    private LazySingleton() {

    }

    /**
     * double check双重检查，提高性能
     * 公开的获取示例的方法
     * @return
     */
    public static LazySingleton getInstance() {

        if (instance == null) {
            // 如果有并发操作，会在这行代码锁住，只允许一个线程执行
            // 如果没有加锁，当两个线程执行到instance == null时，会同时创建一个新的对象
            synchronized (LazySingleton.class) {
                // 如果有线程已经解锁，第二个线程也执行这行代码，则instance不会空，不会重复创建实例
                // 第二次检查
                if (instance == null) {
                    instance = new LazySingleton();
                }
                // new 一个对象，在字节码上的操作
                // JIT 即时编译 just in time，或者CPU 可能会对会对字节码上的指令进行重新排序
                // 1 分配空间
                // 2 初始化空间
                // 3 将空间地址进行赋值
                // 正常的顺序是123，可能重新排序后就是132，单线程结果是没有影响的

                // 如果有两个线程，第一个线程拿到了锁，执行了操作13，但是还没有进行初始化空间
                // instance已经不会空，但是指向的空间为空
                // 这时第二个线程也调用该方法获得实例，发现instance不为空，则返回了这个地址
                // 但是空间还没有初始化，调用方可能会出现空指针异常

                // 解决方法 volatile 防止指令重排序
            }
        }
        return instance;
    }
}
