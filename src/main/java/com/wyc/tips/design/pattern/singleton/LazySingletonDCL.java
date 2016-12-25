package com.wyc.tips.design.pattern.singleton;

/**
 * 双重检验锁
 * Created by Administrator on 2016/12/25 0025.
 */
public class LazySingletonDCL {
    private LazySingletonDCL(){}

    private static LazySingletonDCL instance;
    public static LazySingletonDCL getInstance() {
        if(instance == null) {
            synchronized (LazySingletonDCL.class) {
                if(instance == null) {
                    instance = new LazySingletonDCL();
                }
            }
        }
        return instance;
    }

}
