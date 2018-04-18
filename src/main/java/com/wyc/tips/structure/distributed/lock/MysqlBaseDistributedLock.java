package com.wyc.tips.structure.distributed.lock;

import java.sql.Connection;

/**
 * 基于mysql的分布式锁，伪代码
 * 原理是用mysql的排它锁，开启一个事务，拿到锁，直到处理完毕后释放锁。
 * @author wangyongcan.
 * @date 2018/4/17.
 */
public class MysqlBaseDistributedLock {

    Connection connection;

    static int maxTry = 10;

    public boolean lock() throws Exception {
        connection.setAutoCommit(false);

        int count = 0;
        while(count < maxTry) {
            try {
                Object result = null; // = select * from distributed_lock where name = 'my_test_lock' for update;
                if(result != null) {
                    // 已经取到锁
                    return true;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
            count++;
        }
        return false;
    }

    public void relese() throws Exception {
        connection.commit();
    }

    public static void main(String[] args) {

    }
}
