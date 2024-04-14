package org.zyf.javabasic.test.search;

/**
 * @program: zyfboot-javabasic
 * @description: 简单验证
 * @author: zhangyanfeng
 * @create: 2024-04-14 11:57
 **/
public class SongBlockListTest {
    public static void main(String[] args) {
        // 创建歌曲块链表
        SongBlockLinkedList songList = new SongBlockLinkedList();

        // 添加节点
        SongBlockNode node1 = new SongBlockNode();
        node1.addSong("Song1");
        node1.addSong("Song2");
        songList.addNode(node1);

        SongBlockNode node2 = new SongBlockNode();
        node2.addSong("Song3");
        node2.addSong("Song4");
        songList.addNode(node2);

        // 查找歌曲
        String targetSong = "Song3";
        int index = songList.binarySearchSong(targetSong);
        if (index != -1) {
            System.out.println("歌曲 " + targetSong + " 的索引为: " + index);
        } else {
            System.out.println("歌曲 " + targetSong + " 不存在于歌曲库中。");
        }
    }
}
