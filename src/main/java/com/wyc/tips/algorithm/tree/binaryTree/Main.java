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
        BinarySearchTree<Integer,String> tree = new BinarySearchTree<Integer,String>();
        Map<Integer,String> map = new HashMap<Integer, String>();

        Random random = new Random();
        for(int i=0;i<10;i++) {
            int randomValue = random.nextInt(100);
            map.put(randomValue,randomValue+"");
        }
        tree.buildTree(map);

        BinaryTreeNode<Integer,String> node = tree.getRoot();

        printTree(node);

    }

    private static void printTree(BinaryTreeNode<Integer,String> node) {
        if(node.getLeftChild() != null) {
            printTree(node.getLeftChild());
        }
        System.out.println(node.getValue());
        if(node.getRightChild() != null) {
            printTree(node.getRightChild());
        }
    }

}
