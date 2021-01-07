package com.zxj.singleton.inner_class_singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author littleboy
 * @description 静态内部类单例模式
 * @date 2020/2/29 16:26
 */
public class InnerClassSingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, ClassNotFoundException {
        // InnerClassSingleton instance = InnerClassSingleton.getInstance();
        // InnerClassSingleton instance1 = InnerClassSingleton.getInstance();
        // System.out.println(instance == instance1);

        /*
        //反射机制，获取构造方法
        Constructor<InnerClassSingleton> declaredConstructor = InnerClassSingleton.class.getDeclaredConstructor();
        //将private改为public
        declaredConstructor.setAccessible(true);
        //创建一个实例
        InnerClassSingleton innerClassSingleton = declaredConstructor.newInstance();

        //获取内存中的示例
        InnerClassSingleton innerClassSingleton1 = InnerClassSingleton.getInstance();
        System.out.println(innerClassSingleton == innerClassSingleton1);
        */


        InnerClassSingleton instance = InnerClassSingleton.getInstance();

        //序列号到文件中
        // ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("testSerializable"));
        // oos.writeObject(instance);
        // oos.close();

        //读取文件
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testSerializable"));
        InnerClassSingleton o = ((InnerClassSingleton) ois.readObject());

        System.out.println(o == instance);
    }
}

/**
 * 静态内部类方式，懒加载模式
 */
class InnerClassSingleton implements Serializable {
    //序列化后兼容各个版本，如果有修改代码
    public static final long serialVersionUID = 42L;
    // 当被调用的时候才会被加载
    private static class InnerClassHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    private InnerClassSingleton() {
        // 构造方法中添加判断，可以方式反射重复实例化
        if(InnerClassHolder.instance != null) {
            throw new RuntimeException("单例不允许重复实例化");
        }

    }

    public static InnerClassSingleton getInstance() {
        return InnerClassHolder.instance;
    }

    /**
     * 使反序列化后的实例和内存中的实例为同一个实例
     * @return
     * @throws ObjectStreamException
     */
    Object readResolve() throws ObjectStreamException {
        return InnerClassHolder.instance;
    }
}
