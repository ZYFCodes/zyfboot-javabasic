package org.zyf.javabasic.test.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 歌曲块节点
 * @author: zhangyanfeng
 * @create: 2024-04-14 11:56
 **/
public class SongBlockNode {
    private List<String> songs;  // 存储歌曲的小数组
    private SongBlockNode next;  // 指向下一个节点的指针

    // 构造函数
    public SongBlockNode() {
        this.songs = new ArrayList<>();
        this.next = null;
    }

    // 添加歌曲到当前节点的小数组中
    public void addSong(String song) {
        songs.add(song);
    }

    // 获取当前节点的小数组
    public List<String> getSongs() {
        return songs;
    }

    // 获取下一个节点
    public SongBlockNode getNext() {
        return next;
    }

    // 设置下一个节点
    public void setNext(SongBlockNode next) {
        this.next = next;
    }
}
