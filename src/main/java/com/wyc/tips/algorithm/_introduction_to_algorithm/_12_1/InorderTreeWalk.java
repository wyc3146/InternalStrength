package com.wyc.tips.algorithm._introduction_to_algorithm._12_1;

import java.util.Stack;

/**
 * Created by cc on 2017/1/9.
 */
public class InorderTreeWalk {

    public static void walkWithRecursion(Node root) {
        if(root != null) {
            if(root.l != null) {
                walkWithRecursion(root.l);
            }
            System.out.println(root.key);
            if(root.r != null) {
                walkWithRecursion(root.r);
            }
        }
    }

    public static void walkWithStack(Node root) {
        Stack<Node> stack = new Stack<Node>();
        Node node = root;
        while(node != null) {
            for(;node != null;node = node.l) {
                stack.push(node);
                // 先序遍历
//              System.out.println(node.key);
            }
            for(;node == null && !stack.empty();node = node.r) {
                node = stack.pop();
                // 中序遍历
                System.out.println(node.key);
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.l = new Node(5);
        root.r = new Node(15);
        root.l.l = new Node(2);
        root.l.r = new Node(6);
        root.r.l = new Node(12);
        root.r.r = new Node(17);

        walkWithStack(root);

    }

    private static class Node {
        public Node(int key) {
            this.key = key;
        }
        public int key;
        public Node l;
        public Node r;
        public Node p;

        @Override
        public String toString() {
            return key+"";
        }
    }

}
