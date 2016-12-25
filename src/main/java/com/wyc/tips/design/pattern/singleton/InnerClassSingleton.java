package com.wyc.tips.design.pattern.singleton;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

/**
 * Created by Administrator on 2016/12/25 0025.
 */
public class InnerClassSingleton {
    private InnerClassSingleton(){}

    private static class SingletonHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
