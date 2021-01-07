package com.zxj.singleton.enum_singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author littleboy
 * @description 枚举的单例
 * @date 2020/2/29 16:57
 */
public class  EnumSingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // EnumSingleton instance = EnumSingleton.INSTANCE;
        // EnumSingleton instance1 = EnumSingleton.INSTANCE;
        // System.out.println(instance == instance1);

        //反射创建enum的对象，会报错，enum天生不支持反射创建对象
        Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingleton instance = declaredConstructor.newInstance("INSTANCE", 0);

    }
}

enum EnumSingleton {
    INSTANCE;
    public void print() {
        System.out.println(this.hashCode());
    }
}

