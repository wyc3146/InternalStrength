package com.wyc.tips.design.pattern.iterator;/**
 * Created by wyc on 2016/12/27.
 */

/**
 * @author wangyongcan
 * @Date 2016/12/27 11:43
 */
public class NameRepository implements Container {
    private String names[] = {"wyc","cc","www","qqq"};

    @Override
    public MyIterator iterator() {
        return new NameIterator();
    }

    private class NameIterator implements MyIterator {

        int index = 0;

        @Override
        public boolean hasNext() {
            if(index < names.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return names[index++];
        }
    }

}
