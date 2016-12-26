package com.wyc.tips.design.pattern.observer;/**
 * Created by wyc on 2016/12/26.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wangyongcan
 * @Date 2016/12/26 19:57
 */
public class Subject {
    private List<Observer> observers = new CopyOnWriteArrayList<Observer>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers() {
        for(Observer observer : observers) {
            observer.update();
        }
    }

}
