package org.zyf.javabasic.workflowpipline.enums;

/**
 * @program: zyfboot-javabasic
 * @description: 内容基本类型
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:32
 **/
public enum ItemType {
    NEWS(1, "news", "境内新闻"),
    EXTRA_NEWS(2, "extra_news", "境外新闻"),
    BAIKE(3, "baike", "百科内容"),
    NEWS_CONTENT_SUG(4, "news", "境内新闻正文内容拆条"),
    ;

    private int code;
    private String name;
    private String desc;

    ItemType(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static ItemType codeOf(int code) {
        for (ItemType p : values()) {
            if (p.getCode() == code) {
                return p;
            }
        }
        return null;
    }

    public static ItemType nameOf(String name) {
        for (ItemType p : values()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}