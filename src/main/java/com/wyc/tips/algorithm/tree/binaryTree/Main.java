package com.wyc.tips.algorithm.tree.binaryTree;/**
 * Created by wyc on 2016/12/28.
 */

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wangyongcan
 * @Date 2016/12/28 18:12
 */
public class Main {
    public static void main(String args[]) {
        BinarySearchTree tree = new BinarySearchTree();
        Map<String,String> map = new HashMap<String, String>();

        Random random = new Random();
        for(int i=10;i>0;i--) {
            int randomValue = random.nextInt(100);
            map.put(randomValue+"",randomValue+"");
        }
        tree.buildTree(map);

        BinaryTreeNode node = tree.getRoot();
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(node);
        while((node = queue.poll()) != null) {
            System.out.println(node.getKey());
            if(node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if(node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
        }

    }
}
