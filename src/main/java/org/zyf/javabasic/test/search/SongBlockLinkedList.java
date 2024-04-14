package org.zyf.javabasic.test.search;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 歌曲块链表
 * @author: zhangyanfeng
 * @create: 2024-04-14 11:56
 **/
public class SongBlockLinkedList {
    private SongBlockNode head;  // 头节点

    // 构造函数
    public SongBlockLinkedList() {
        head = null;
    }

    // 在链表尾部添加一个节点
    public void addNode(SongBlockNode newNode) {
        if (head == null) {
            head = newNode;
        } else {
            SongBlockNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    // 二分查找指定歌曲，返回所在节点的索引
    public int binarySearchSong(String song) {
        SongBlockNode current = head;
        int index = 0;

        // 遍历链表
        while (current != null) {
            List<String> songs = current.getSongs();
            if (songs.contains(song)) {
                return index + songs.indexOf(song);
            }
            current = current.getNext();
            index += songs.size();
        }

        // 歌曲不存在于链表中
        return -1;
    }
}
