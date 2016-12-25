package com.wyc.tips.design.pattern.factory;

import com.wyc.tips.design.pattern.factory.impl.Circle;
import com.wyc.tips.design.pattern.factory.impl.Rectangle;
import com.wyc.tips.design.pattern.factory.impl.Square;

/**
 * 工厂类，对客户端生产对象
 * Created by Administrator on 2016/12/25 0025.
 */
public class ShapeFactory {
    public static Shape getShape(ShapeType shapeType) {
        switch (shapeType){
            case RECTANGLE:
                return new Rectangle();
            case SQUARE:
                return new Square();
            case CIRCLE:
                return new Circle();
        }
        return null;
    }

    public static enum ShapeType {
        RECTANGLE("rectangle",0),
        SQUARE("Square",1),
        CIRCLE("Circle",2);
        private String name;
        private int value;
        private ShapeType(String name,int value) {
            this.name = name;
            this.value = value;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }
    }
}
