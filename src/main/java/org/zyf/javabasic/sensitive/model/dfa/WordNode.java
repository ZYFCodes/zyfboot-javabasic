package org.zyf.javabasic.sensitive.model.dfa;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 敏感词节点结构
 * @date 2023/5/25  20:06
 */
@Data
public class WordNode {
    /**
     * 节点名称
     */
    private int value;
    /**
     * 子节点
     */
    private List<WordNode> subNodes;
    /**
     * 默认false
     */
    private boolean isLast;
    /**
     * 当isLast=true时，存储当前敏感词的信息
     */
    private String wordContent;

    public WordNode(int value) {
        this.value = value;
    }

    public WordNode(int value, boolean isLast, String wordContent) {
        this.value = value;
        this.isLast = isLast;
        if (true) {
            this.wordContent = wordContent;
        }
    }

    /**
     * 增加子节点
     *
     * @param subNode 子节点
     * @return 就是传入的subNode
     */
    private WordNode addSubNode(final WordNode subNode) {
        if (subNodes == null) {
            subNodes = new LinkedList<WordNode>();
        }
        subNodes.add(subNode);
        return subNode;
    }

    /**
     * 有就直接返回该子节点， 没有就创建添加并返回该子节点
     *
     * @param value
     * @return
     */
    public WordNode addIfNoExist(final int value, final boolean isLast, String wordContent) {
        if (subNodes == null) {
            return addSubNode(new WordNode(value, isLast, wordContent));
        }
        for (WordNode subNode : subNodes) {
            if (subNode.value == value) {
                if (!subNode.isLast && isLast) {
                    subNode.isLast = true;
                    subNode.setWordContent(wordContent);
                }
                return subNode;
            }
        }
        return addSubNode(new WordNode(value, isLast, wordContent));
    }

    public WordNode querySub(final int value) {
        if (subNodes == null) {
            return null;
        }
        for (WordNode subNode : subNodes) {
            if (subNode.value == value) {
                return subNode;
            }
        }
        return null;
    }

    public boolean isLast() {
        return isLast;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WordNode wordNode = (WordNode) o;
        return value == wordNode.value &&
                isLast == wordNode.isLast &&
                subNodes.equals(wordNode.subNodes) &&
                wordContent.equals(wordNode.wordContent);
    }
}
