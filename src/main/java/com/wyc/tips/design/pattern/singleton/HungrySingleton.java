package com.wyc.tips.design.pattern.singleton;

/**
 * Created by Administrator on 2016/12/25 0025.
 */
public class HungrySingleton {
    private HungrySingleton() {}

    private static HungrySingleton instance = new HungrySingleton();
    public static HungrySingleton getInstance() {
        return instance;
    }

}
