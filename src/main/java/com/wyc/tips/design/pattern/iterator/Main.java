package com.wyc.tips.design.pattern.iterator;/**
 * Created by wyc on 2016/12/27.
 */

import java.util.ArrayList;

/**
 * 迭代器模式
 * 不同遍历对象可以用统一接口遍历，无需暴露对象内部表示
 * @author wangyongcan
 * @Date 2016/12/27 11:46
 */
public class Main {
    public static void main(String args[]) {
        Container container = new NameRepository();
        MyIterator myIterator = container.iterator();
        while(myIterator.hasNext()) {
            System.out.println(myIterator.next());
        }
    }
}
