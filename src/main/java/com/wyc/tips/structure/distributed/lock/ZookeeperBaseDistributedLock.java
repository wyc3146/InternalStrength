package com.wyc.tips.structure.distributed.lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.Comparator;
import java.util.List;

/**
 * @author wangyongcan.
 * @date 2018/4/17.
 */
public class ZookeeperBaseDistributedLock {

    private static final String LOCK_PATH = "distributed_lock_path";

    private String host = "192.168.22.21:2181";

    private String myIndex;

    public boolean lock() throws Exception {
        ZooKeeper zk = new ZooKeeper(host, 2000, null);

        myIndex = zk.create(LOCK_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        List<String> lockNodes = zk.getChildren(LOCK_PATH, false);
        lockNodes.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        });

        return myIndex.equals(lockNodes.get(0));
    }

    public void release() throws Exception {
        if(myIndex != null) {
            ZooKeeper zk = new ZooKeeper(host, 2000, null);
            zk.delete(LOCK_PATH + "/" + myIndex, -1);
        }
    }

    public static void main(String[] args) throws Exception {
        String host = "192.168.22.21:2181";
        String zpath = "/test/";

        ZooKeeper zk = new ZooKeeper(host, 2000, null);
//        List<String> zooChildren = zk.getChildren("/", false);
//        System.out.println(zooChildren);

        String s = zk.create(zpath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(s);

        List<String> zooChildren = zk.getChildren("/test", false);
        zooChildren.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        });
        System.out.println(zooChildren);

        Thread.sleep(100000);
    }
}
