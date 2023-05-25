package org.zyf.javabasic.sensitive.base;

import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  19:37
 */
@Data
public class Word {
    private long id;
    private int type;
    private String categoryIds;
    private String words;
    private String wordsPinyin;
    private int cuid;
    private int muid;
    private String cuname;
    private String muname;
    private int ctime;
    private int utime;
    private List<WordRule> wordRules;
    private List<Long> deleteRuleIds;
    private SensitiveMatchSceneEnum scene;
}
