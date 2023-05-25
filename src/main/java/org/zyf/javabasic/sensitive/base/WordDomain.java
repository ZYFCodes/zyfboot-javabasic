package org.zyf.javabasic.sensitive.base;

import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.zyf.javabasic.common.utils.PinyinUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/24  20:24
 */
@Data
public class WordDomain {
    private long id; // 主键id
    private int type; // 校验方式(0：关键字 1：简单正则 2：与 正则 3：与非 正则)
    private long firstCategoryId;// 敏感词一级分类id
    private long secondCategoryId;// 敏感词二级分类id
    @NotEmpty
    @Length(max = 2000)
    private String words; // 敏感词词条
    private String wordsPinyin; // 敏感词拼音
    public List<WordRuleDomain> wordRules;
    /**
     * 审核场景
     * { com.sankuai.meituan.waimai.sensitive.thrift.enums.SensitiveMatchSceneEnum}
     */
    private int scene;
    private int cuid; // 创建人id
    private int muid; // 修改人id
    @Length(max = 64)
    private String cuname; // 创建人姓名
    @Length(max = 64)
    private String muname; // 修改人姓名
    private int ctime; // 创建时间
    private int utime; // 修改时间

    private static Base64 base64 = new Base64();

    public void from(Word word) {
        this.id = word.getId();
        this.type = word.getType();
        this.words = word.getWords();
        this.cuname = word.getCuname();
        this.cuid = word.getCuid();
        this.muname = word.getMuname();
        this.muid = word.getMuid();
        this.ctime = word.getCtime();
        this.scene = word.getScene().getValue();

        initWordPinYinFromBase64();

        if (CollectionUtils.isNotEmpty(word.getWordRules())) {
            this.wordRules = word.getWordRules().stream().map(wordRule -> {
                WordRuleDomain wordRuleDomain = new WordRuleDomain();
                wordRuleDomain.from(wordRule);
                return wordRuleDomain;
            }).collect(Collectors.toList());
        }

        String categoryId = word.getCategoryIds();
        if (StringUtils.isNotBlank(word.getCategoryIds())) {
            String[] split = categoryId.split(",");
            this.firstCategoryId = string2Long(split[0]);
            if (split.length == 2) {
                Long secCategoryId = string2Long(split[1]);
                if (secCategoryId != -1) {
                    this.secondCategoryId = secCategoryId;
                }
            }
        }

    }

    /**
     * 如果不是关键字，那么就是正则。
     * 普通正则没有逗号
     * 与正则 + 与非正则。逗号隔开的这种： 54Gr6bih,54Gr6bihLirpuKHohb/loKE= 火鸡,火鸡.*鸡腿堡
     * base64的目的是处理特殊符号。 逗号的两边分开两个不同的词
     */
    public void initWordPinYinFromBase64() {
        String pinyin;
        if (type == SensitiveValidateTypeEnum.SIAMPLE_REGULAR.getCode()) {
            /*简单正则*/
            String decodeWord = new String(base64.decode(this.words));
            pinyin = PinyinUtils.getPingYin(decodeWord);
        } else if (type == SensitiveValidateTypeEnum.AND_REGULAR.getCode() || type == SensitiveValidateTypeEnum.N_AND_REGULAR.getCode()) {
            /*与正则与与非正则*/
            String[] wordArr = this.words.split(",");
            String decodeWord1 = new String(base64.decode(wordArr[0]));
            String decodeWord2 = new String(base64.decode(wordArr[1]));
            String word1Pinyin = PinyinUtils.getPingYin(decodeWord1);
            String word2Pinyin = PinyinUtils.getPingYin(decodeWord2);
            pinyin = word1Pinyin + "," + word2Pinyin;
        } else if (type == SensitiveValidateTypeEnum.HOMONYM.getCode()) {
            /*同音词*/
            String[] wordArr = this.words.split(",");
            String decodeWord1 = new String(base64.decode(wordArr[0]));
            String word1Pinyin = PinyinHelper.toPinyin(decodeWord1, PinyinStyleEnum.NORMAL).replaceAll(" ", "");
            pinyin = word1Pinyin;
            if (wordArr.length == 2) {
                String decodeWord2 = new String(base64.decode(wordArr[1]));
                String word2Pinyin = PinyinHelper.toPinyin(decodeWord2, PinyinStyleEnum.NORMAL).replaceAll(" ", "");
                pinyin = word1Pinyin + "," + word2Pinyin;
            }
        } else {
            /*关键字*/
            pinyin = PinyinUtils.getPingYin(this.words);
        }
        if (StringUtils.isNotEmpty(pinyin)) {
            this.wordsPinyin = pinyin;
        }
    }

    private static Long string2Long(String data) {
        if (StringUtils.isBlank(data)) {
            return -1L;
        }
        try {
            return Long.parseLong(data);
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    public Word to() {
        Word word = new Word();
        word.setId(this.id);
        word.setType(this.type);
        word.setWords(this.words);
        word.setWordsPinyin(this.wordsPinyin);
        word.setScene(SensitiveMatchSceneEnum.findByValue(this.scene));
        if (CollectionUtils.isNotEmpty(this.wordRules)) {
            word.setWordRules(this.wordRules.stream().map(WordRuleDomain::to).collect(Collectors.toList()));
        }
        long secondCategoryId = this.getSecondCategoryId();
        long firstCategoryId = this.getFirstCategoryId();
        if (secondCategoryId > 0) {
            word.setCategoryIds(firstCategoryId + "," + secondCategoryId);
        } else {
            word.setCategoryIds(String.valueOf(firstCategoryId));
        }

        word.setCtime(this.getCtime());
        word.setUtime(this.getUtime());
        word.setCuname(this.getCuname());
        word.setMuname(this.getMuname());
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WordDomain)) {
            return false;
        }
        WordDomain that = (WordDomain) o;
        return getId() == that.getId()
                && getCuid() == that.getCuid()
                && Objects.equals(getWords(), that.getWords());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCuname(), getWords());
    }
}
