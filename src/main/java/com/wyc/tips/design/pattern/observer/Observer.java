package com.wyc.tips.design.pattern.observer;/**
 * Created by wyc on 2016/12/26.
 */

/**
 * @author wangyongcan
 * @Date 2016/12/26 20:00
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
