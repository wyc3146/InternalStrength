package com.wyc.tips.design.pattern.singleton;

/**
 * Created by Administrator on 2016/12/25 0025.
 */
public class LazySingletonThreadSafe {
    private LazySingletonThreadSafe() {
    }

    private static LazySingletonThreadSafe instance;
    public static synchronized LazySingletonThreadSafe getInstance(){
        if(instance == null) {
            instance = new LazySingletonThreadSafe();
        }
        return instance;
    }
}
