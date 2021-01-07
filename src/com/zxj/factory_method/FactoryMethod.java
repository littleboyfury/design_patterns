package com.zxj.factory_method;

/**
 * @author littleboy
 * @description 工厂方法
 * @date 2020/2/29 22:59
 */
public class FactoryMethod {
    public static void main(String[] args) {
        AbstractFactoryProduct abstractFactoryProduct = new ConcreateProductC();
        Product product = abstractFactoryProduct.getProduct();
        product.method();
    }
}

/**
 * 简单工厂，不是设计模式，只是编程习惯
 */
class SimpleFactory {
    public static Product createProduct(String type) {
        switch (type) {
            case "a":
                return new ProductA();
            case "b":
                return new ProductB();
            default:
                return null;
        }
    }
}

/**
 * 进行product的抽象
 */
interface Product {
    void method();
}

class ProductA implements Product{
    @Override
    public void method() {
        System.out.println("product a");
    }
}

class ProductB implements Product{
    @Override
    public void method() {
        System.out.println("product b");
    }
}

/**
 * 新建了productC，需要实现product
 */
class ProductC implements Product {
    @Override
    public void method() {
        System.out.println("product c");
    }
}

// class Application {
//     private Product createProduct(String type) {
//         // TODO something you want to do
//         return SimpleFactory.createProduct(type);
//     }
//
//     Product getProductA(String type) {
//         Product productA = createProduct(type);
//         return productA;
//     }
// }



/**
 * 抽象工厂
 * 抽象方法，将创建对象延迟到子类
 * 把创建对象的方法定义为抽象，具体创建由子类创建
 * 如果有新添加的product，只需要新建一个创建的工厂继承该抽象类，即可创建新product
 * 符合开闭原则，对扩展打开，对修改关闭
 */
abstract class AbstractFactoryProduct {
    abstract Product createProduct();

    Product getProduct() {
        Product product = createProduct();
        // TODO something you want to do
        return product;
    }
}

/**
 * 创建productA的工厂
 */
class ConcreateProductA extends AbstractFactoryProduct {
    @Override
    Product createProduct() {
        // TODO something you want to do
        return new ProductA();
    }
}

/**
 * 创建productB的工厂
 */
class ConcreateProductB extends AbstractFactoryProduct {
    @Override
    Product createProduct() {
        // TODO something you want to do
        return new ProductB();
    }
}

/**
 * 新建product c的时候，只需要继承该抽象工厂
 */
class ConcreateProductC extends AbstractFactoryProduct {
    @Override
    Product createProduct() {
        return new ProductC();
    }
}


