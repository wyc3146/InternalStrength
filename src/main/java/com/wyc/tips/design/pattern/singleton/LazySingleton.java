package com.wyc.tips.design.pattern.singleton;

/**
 * 简单懒汉模式,线程不安全
 * Created by Administrator on 2016/12/25 0025.
 */
public class LazySingleton {

    private LazySingleton() {
    }

    private static LazySingleton instance;
    public static LazySingleton getInstance(){
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
