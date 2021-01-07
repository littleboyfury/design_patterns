package com.zxj.singleton.hungry_singleton;

/**
 * @author littleboy
 * @description 饿汉单例模式
 * @date 2020/2/28 23:51
 */
public class HungrySingletonTest {
    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();
        HungrySingleton instance1 = HungrySingleton.getInstance();
        System.out.println(instance == instance1);
    }
}

/**
 * 当调用getInstance的，jvm会判断内存是否有HungrySingleton这个实例，如果没有这会先加载类
 * 1. 加载二进制数据到内存中， 生成对应的Class数据结构，
 * 2. 连接:
 *    a. 验证
 *    b. 准备(给类的静态成员变量赋默认值)
 *    c. 解析
 * 3. 初始化: 给类的静态变量赋初值
 * jvm可以保证该流程只执行一次，可以保证instance为单例
 */
class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
