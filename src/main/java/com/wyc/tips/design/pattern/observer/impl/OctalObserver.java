package com.wyc.tips.design.pattern.observer.impl;/**
 * Created by wyc on 2016/12/26.
 */

import com.wyc.tips.design.pattern.observer.Observer;
import com.wyc.tips.design.pattern.observer.Subject;

/**
 * @author wangyongcan
 * @Date 2016/12/26 20:09
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        subject.subscribe(this);
    }

    @Override
    public void update() {
        System.out.println("OctalString : " + Integer.toOctalString( subject.getState()));
    }
}
