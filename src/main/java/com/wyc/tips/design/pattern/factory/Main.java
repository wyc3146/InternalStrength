package com.wyc.tips.design.pattern.factory;

/**
 * Created by Administrator on 2016/12/25 0025.
 */
public class Main {
    public static void main(String args[]) {
        Shape rectangle = ShapeFactory.getShape(ShapeFactory.ShapeType.RECTANGLE);
        rectangle.draw();
        Shape square = ShapeFactory.getShape(ShapeFactory.ShapeType.SQUARE);
        square.draw();
        Shape circle = ShapeFactory.getShape(ShapeFactory.ShapeType.CIRCLE);
        circle.draw();
    }
}
