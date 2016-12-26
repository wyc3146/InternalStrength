package com.wyc.tips.design.pattern.observer;/**
 * Created by wyc on 2016/12/26.
 */

import com.wyc.tips.design.pattern.observer.impl.BinaryObserver;
import com.wyc.tips.design.pattern.observer.impl.HexObserver;
import com.wyc.tips.design.pattern.observer.impl.OctalObserver;

/**
 * @author wangyongcan
 * @Date 2016/12/26 20:11
 */
public class Main {
    public static void main(String args[]) {
        Subject subject = new Subject();
        new HexObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        subject.setState(16);

    }
}
