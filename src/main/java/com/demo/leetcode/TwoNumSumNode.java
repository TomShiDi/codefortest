package com.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author TomShiDi
 * @description
 * @date 2022/3/26 15:07
 **/
public class TwoNumSumNode {

    public static void main(String[] args) {
        ListNode l1 = create(new int[]{2, 4, 3});
        ListNode l2 = create(new int[]{5, 6, 4});
        ListNode result = addTwoNumbers(l1, l2);
        Integer[] integers = toArr(result);
        System.out.println(Arrays.toString(integers));
    }

    public static ListNode create(int[] arr) {
        ListNode headNode = null;
        ListNode preNode = null;
        for (int i : arr) {
            if (headNode == null) {
                headNode = new ListNode();
                headNode.val = i;
                preNode = headNode;
            } else {
                ListNode nextNode = new ListNode();
                nextNode.val = i;
                preNode.next = nextNode;
                preNode = nextNode;
            }
        }
        return headNode;
    }

    public static Integer[] toArr(ListNode node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list.toArray(new Integer[0]);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode headNode = null;
        ListNode preNode = null;
        ListNode node;
        ListNode nextNode;
        int num2;
        do {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            if (headNode == null) {
                node = new ListNode();
                headNode = node;
                num2 = (node.val + val1 + val2) / 10;
                node.val = (node.val + val1 + val2) % 10;
                if (num2 != 0) {
                    nextNode = new ListNode();
                    nextNode.val = num2;
                    node.next = nextNode;
                }
                preNode = node;
            } else {
                node = preNode.next;
                if (node == null) {
                    node = new ListNode();
                    preNode.next = node;
                }
                preNode = node;
                num2 = (node.val + val1 + val2) / 10;
                node.val = (node.val + val1 + val2) % 10;
                if (num2 != 0) {
                    nextNode = new ListNode();
                    nextNode.val = num2;
                    node.next = nextNode;
                }
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        } while (l1 != null || l2 != null);
        return headNode;
    }
}
