package com.demo.treetest;

import java.util.LinkedList;

/**
 * 一棵二叉排序树
 *
 * @Author TomShiDi
 * @Since 2019/9/2
 * @Version 1.0
 */
public class TomTree {

    /**
     * 根节点
     */
    private Node rootNode = null;

    /**
     * 节点数
     */
    private int nodeNum = 0;

    /**
     * 修改数
     */
    private int modifyNum = 0;

    /**
     * 广度遍历时使用的队列
     */
    LinkedList<Node> queue = null;

    public TomTree() {
        queue = new LinkedList<>();
    }

    /**
     * 添加一个节点 这里会进行节点的创建以及比较和节点的链接
     * 相同的值不会添加
     * @param value 待加入的值
     * @return 返回true 如果添加成功  返回false 如果添加失败
     */
    public boolean add(int value) {
        if (rootNode == null) {
            rootNode = new Node(value);
            return true;
        }
        Node next = compareAndReturn(rootNode, value);
        if (next != null) {
            if (value < next.getValue()) {
                next.setLeft(new Node(value));
                nodeNum++;
                return true;
            } else {
                next.setRight(new Node(value));
                nodeNum++;
                return true;
            }
        }
        return false;
    }


    /**
     * 对数的节点遍历 寻找待插入的下一个节点的父节点
     * @param startNode 开始查询的节点，递归调用
     * @param value 待插入的值
     * @return 返回null 如果没找到父节点或者树中有相同值的节点  返回父节点 如果值“可以”当做其子节点
     */
    private Node compareAndReturn(Node startNode, int value) {
        /**
         * 如果当前节点是叶子节点 直接返回当前节点
         */
        if (startNode.getLeft() == null && startNode.getRight() == null) {
            return startNode;
        }

        /**
         * 新节点在左子树
         */
        if (value < startNode.getValue()) {
            if (startNode.getLeft() != null) {
                return compareAndReturn(startNode.getLeft(), value);
            } else {
                return startNode;
            }
        }
        /**
         * 新节点在右子树
         */
        if (value > startNode.getValue()) {
            if (startNode.getRight() != null) {
                return compareAndReturn(startNode.getRight(), value);
            } else {
                return startNode;
            }
        }

        return null;
    }

    /**
     * 先序遍历
     * @param currNode 当前节点 递归调用
     */
    public void showTreeFirst(Node currNode) {
        System.out.print(currNode.getValue() + " ");
        if (currNode.getLeft() != null) {
            showTreeFirst(currNode.getLeft());
        }

        if (currNode.getRight() != null) {
            showTreeFirst(currNode.getRight());
        }
    }

    /**
     * 中序遍历
     * @param currNode 当前节点 递归调用
     */
    public void showTreeMid(Node currNode) {
        if (currNode.getLeft() != null) {
            showTreeMid(currNode.getLeft());
        }
        System.out.print(currNode.getValue() + " ");
        if (currNode.getRight() != null) {
            showTreeMid(currNode.getRight());
        }
        return;
    }

    /**
     * 后序遍历
     * @param currNode 当前节点 递归调用
     */
    public void showTreeLast(Node currNode) {
        if (currNode.getLeft() != null) {
            showTreeLast(currNode.getLeft());
        }
        if (currNode.getRight() != null) {
            showTreeLast(currNode.getRight());
        }
        System.out.print(currNode.getValue() + " ");
    }

    /**
     * 广度遍历
     * @param currNode 当前节点 递归调用
     */
    public void showTreeLevel(Node currNode) {
        showTreeLevel(currNode, 1);
    }

    private void showTreeLevel(Node currNode, int level) {
        /**
         * 使用全局队列保存广度遍历的值
         */
        queue.offer(currNode);
        //左子树非空
        if (currNode.getLeft() != null) {
            showTreeLevel(currNode.getLeft(), level + 1);
        }
        //右子树非空
        if (currNode.getRight() != null) {
            showTreeLevel(currNode.getRight(), level + 1);
        }
        //在最上层递归中输出遍历值
        if (level == 1) {
            while (!queue.isEmpty()) {
                Node t = queue.poll();
                System.out.print(t.getValue() + " ");
            }
        }
    }

    public Node getRootNode() {
        return rootNode;
    }

    public int getNodeNum() {
        return nodeNum;
    }

    /**
     * 定义节点数据结构
     */
    public static class Node {
        private Node left;

        private Node right;

        private int value;

        public Node(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        Node getLeft() {
            return left;
        }

        void setLeft(Node left) {
            this.left = left;
        }

        Node getRight() {
            return right;
        }

        void setRight(Node right) {
            this.right = right;
        }
    }
}
