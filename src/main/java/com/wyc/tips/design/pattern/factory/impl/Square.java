package com.wyc.tips.design.pattern.factory.impl;

import com.wyc.tips.design.pattern.factory.Shape;

/**
 * Created by Administrator on 2016/12/25 0025.
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("draw a square");
    }
}
