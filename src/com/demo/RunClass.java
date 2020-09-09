package com.demo;

import com.demo.treetest.TomTree;

public class RunClass {
    public static void main(String []args){
        showTreeFirst();
        System.out.print("\t先序遍历\n");
        showTreeMid();
        System.out.print("\t中序遍历\n");
        showTreeLast();
        System.out.print("\t后序遍历\n");
        showTreeLevel();
        System.out.print("\t广度遍历\n");

    }


    public static void showTreeFirst() {
        TomTree tomTree = new TomTree();
        tomTree.add(2);
        tomTree.add(1);
        tomTree.add(3);
        tomTree.add(4);
        tomTree.add(5);
        tomTree.showTreeFirst(tomTree.getRootNode());
    }

    public static void showTreeMid() {
        TomTree tomTree = new TomTree();
        tomTree.add(2);
        tomTree.add(1);
        tomTree.add(3);
        tomTree.add(4);
        tomTree.add(5);
        tomTree.showTreeMid(tomTree.getRootNode());
    }

    public static void showTreeLast() {
        TomTree tomTree = new TomTree();
        tomTree.add(2);
        tomTree.add(1);
        tomTree.add(3);
        tomTree.add(4);
        tomTree.add(5);
        tomTree.showTreeLast(tomTree.getRootNode());
    }

    public static void showTreeLevel() {
        TomTree tomTree = new TomTree();
        tomTree.add(2);
        tomTree.add(1);
        tomTree.add(3);
        tomTree.add(4);
        tomTree.add(5);
        tomTree.showTreeLevel(tomTree.getRootNode());
    }
}
