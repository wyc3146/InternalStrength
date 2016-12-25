package com.wyc.tips.design.pattern.singleton;

/**
 * 枚举类，不仅是线程安全而且自动支持序列化，防止反序列化后重新创建对象
 * Created by Administrator on 2016/12/25 0025.
 */
public enum EnumSingleton {
    INSTANCE;
    public void whateverMethod() {

    }
}
