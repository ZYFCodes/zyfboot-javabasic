package org.zyf.javabasic.letcode.list;

/**
 * @author yanfengzhang
 * @description 链表结构
 * @date 2022/8/2  20:49
 */
public class ListNode {
    /**
     * 链表长度
     */
    int size;
    /**
     * 链表头节点
     */
    Node head;

    /**
     * Initialize your data structure here.
     */
    public ListNode() {
        this.size = 0;
        this.head = null;
    }

    /**
     * Get the value of the index-th node in the linked list.
     * If the index is invalid, return -1.
     */
    public int get(int index) {
        /*1.边界条件考虑：插入点非法和本身喂空链表情况*/
        if (index < 0 || index >= size || head == null) {
            return -1;
        }
        /*2.遍历处理*/
        Node temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    /**
     * Add a node of value val before the first element of the linked list.
     * After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        /*头部直接插入即可*/
        Node node = new Node(val);
        node.next = this.head;
        this.head = node;
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        /*如果是空链表则进行初始化head即可*/
        if (size == 0) {
            this.head = new Node(val);
            head.next = null;
            size++;
            return;
        }
        /*非空链表进行遍历插入*/
        Node temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        Node tail = new Node(val);
        tail.next = null;
        temp.next = tail;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        /*1.边界条件考虑：大于链表长度的直接返回即可*/
        if (index > this.size) {
            return;
        }
        /*2.边界条件考虑：插入点直接小于等于0视为在头部插入*/
        if (index <= 0) {
            addAtHead(val);
            return;
        }
        /*3.边界条件考虑：插入点大于链表长度的视为在尾部插入*/
        if (index == this.size) {
            addAtTail(val);
            return;
        }
        /*4.非边界条件：需要遍历插入点后进行插入操作*/
        Node temp = this.head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        Node insertNode = new Node(val);
        insertNode.next = temp.next;
        temp.next = insertNode;
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        /*1.边界条件考虑：删除点不合法直接返回即可*/
        if (index < 0 || index >= this.size) {
            return;
        }
        /*2.删除点为0，则需要进行分析链表情况*/
        if (index == 0) {
            /*2.1 链表只有一个节点时，直接置空链表*/
            if (size == 1) {
                this.head = null;
                size--;
                return;
            }
            /*2.2 链表多节点时，相当于删除头节点*/
            Node temp = this.head.next;
            this.head = temp;
            size--;
            return;
        }
        /*3.遍历删除指定位置节点*/
        Node temp = this.head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        Node deleteNode = temp.next;
        temp.next = deleteNode.next;
        size--;
    }
}
