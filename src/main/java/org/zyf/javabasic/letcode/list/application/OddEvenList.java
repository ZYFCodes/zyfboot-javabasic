package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * 例如，给定链表 1->2->3->4->5->NULL，将其重新排列后得到 1->3->5->2->4->NULL。
 * 要求空间复杂度为 O(1)，时间复杂度为 O(n)。
 * 示例 1：输入: 1->2->3->4->5->NULL.        输出: 1->3->5->2->4->NULL
 * 示例 2：输入: 2->1->3->5->6->4->7->NULL   输出: 2->3->6->7->1->5->4->NULL
 * <p>
 * 说明：
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的长度不超过 2000。
 * 来源：力扣
 * 请你编写函数实现这个功能，并提供一组测试数据进行验证。
 * @date 2023/4/5  23:27
 */
public class OddEvenList {

    /**
     * 最优解法是创建两个链表，分别存储原始链表的奇数节点和偶数节点，然后将这两个链表连接起来。
     * <p>
     * 具体而言，可以维护两个指针odd和even，分别指向奇数节点和偶数节点链表的末尾。
     * 遍历原始链表，对于每个节点，如果其为奇数节点，则将其插入到odd链表的末尾；
     * 否则，将其插入到even链表的末尾。最后，将odd链表的末尾指向even链表的开头即可。
     * <p>
     * 时间复杂度为O(n)，空间复杂度为O(1)。
     */
    public ListNode oddEvenList(ListNode head) {
        /*如果链表为空或者只有一个节点，直接返回*/
        if (head == null || head.next == null) {
            return head;
        }

        /*定义两个指针，一个指向奇数节点，一个指向偶数节点*/
        ListNode odd = head;
        ListNode even = head.next;
        /*记录偶数链表的头节点*/
        ListNode evenHead = even;

        /*遍历链表，将奇数节点和偶数节点分别连接起来*/
        while (even != null && even.next != null) {
            /*将奇数节点连接到下一个奇数节点*/
            odd.next = even.next;
            odd = odd.next;
            /*将偶数节点连接到下一个偶数节点*/
            even.next = odd.next;
            even = even.next;
        }

        /*将奇数链表的末尾连接到偶数链表的头部，形成新的链表*/
        odd.next = evenHead;

        /*返回链表头部*/
        return head;
    }

    public static void main(String[] args) {
        /*构造链表 1->2->3->4->5*/
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        /*调用方法*/
        ListNode result = new OddEvenList().oddEvenList(head);

        /*输出结果 1->3->5->2->4*/
        while (result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
        System.out.print("null");
    }


}
