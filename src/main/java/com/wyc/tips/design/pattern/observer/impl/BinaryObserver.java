package com.wyc.tips.design.pattern.observer.impl;/**
 * Created by wyc on 2016/12/26.
 */

import com.wyc.tips.design.pattern.observer.Observer;
import com.wyc.tips.design.pattern.observer.Subject;

/**
 * @author wangyongcan
 * @Date 2016/12/26 20:08
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        subject.subscribe(this);
    }

    @Override
    public void update() {
        System.out.println("BinaryString : " + Integer.toBinaryString(subject.getState()));
    }
}
