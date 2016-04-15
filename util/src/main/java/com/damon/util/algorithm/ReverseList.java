package com.damon.util.algorithm;

/**
 * Created by dongjun.wei on 16/4/5.
 */
public class ReverseList {

    public static Node reverseList(Node head) {

        if (head == null) {
            return null;
        }

        Node p1 = head;
        Node p2 = head.next;
        Node p3;

        while (p2 != null) {
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }

        head.next = null;


        return p1;
    }

    public static void printList(Node head) {
        Node p = head;
        while (p != null) {
            System.out.println(p.data);
            p = p.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        node1.data = 1;
        node1.next = node2;
        node2.data = 2;
        node2.next = node3;
        node3.data = 3;
        node3.next = node4;
        node4.data = 4;
        node4.next = null;

        head.next = node1;

        printList(head);

        Node reverseList = reverseList(head);

        printList(reverseList);



    }
}

class Node {
    int data;
    Node next;
}