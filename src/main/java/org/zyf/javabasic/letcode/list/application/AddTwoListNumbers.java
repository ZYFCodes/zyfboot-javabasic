package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定两个非空链表 A 和 B，链表中的每个节点表示一个数字的一位，
 * 将两个链表表示的数字相加，并以链表形式返回相加后的结果。
 * 链表相加
 * A: 3->8->6
 * B: 6->3->3->9
 * 输出
 * 9->1->0->0->1
 * @date 2023/6/14  00:48
 */
public class AddTwoListNumbers {
    /**
     * 为了实现链表的相加，可以使用迭代的方式从头到尾遍历两个链表，并按照相应位置的节点值相加，同时考虑进位的情况。具体步骤如下：
     *
     * 	1.	创建一个新的链表 result 用于保存相加后的结果。
     * 	2.	初始化两个指针 p 和 q 分别指向链表 A 和 B 的头节点。
     * 	3.	初始化进位值 carry 为 0。
     * 	4.	遍历链表 A 和 B，直到两个链表都遍历完：
     * 	•	获取当前节点的值 x 和 y，如果某个链表已经遍历完，则将对应节点的值设为 0。
     * 	•	计算当前位置的和 sum = x + y + carry，以及进位值 carry = sum / 10。
     * 	•	创建一个新的节点，值为 sum % 10，将该节点添加到 result 链表的末尾。
     * 	•	将指针 p 和 q 向后移动一位。
     * 	5.	如果遍历结束后，进位值 carry 不为 0，则创建一个新节点，值为 carry，将该节点添加到 result 链表的末尾。
     * 	6.	返回 result 链表。
     *
     * 通过上述步骤，我们可以得到两个链表相加的结果。该算法的时间复杂度为 O(max(m, n))，其中 m 和 n 分别是链表 A 和 B 的长度。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 哑节点，简化操作
        ListNode dummy = new ListNode(0);
        ListNode p = l1;
        ListNode q = l2;
        // 当前节点
        ListNode curr = dummy;
        // 进位值
        int carry = 0;

        while (p != null || q != null) {
            //查看当前节点p对应的数值
            int x = 0;
            if(p != null){
                x= p.val;
            }
            //查看当前节点q对应的数值
            int y=0;
            if(q != null){
                y=q.val;
            }
            //对应链表加和计算
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(8);
        l1.next.next = new ListNode(6);

        ListNode l2 = new ListNode(6);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(9);

        AddTwoListNumbers solution = new AddTwoListNumbers();
        ListNode result = solution.addTwoNumbers(l1, l2);

        // 打印结果链表
        while (result != null) {
            System.out.print(result.val + " -> ");
            result = result.next;
        }
        System.out.println("null");
    }

}
