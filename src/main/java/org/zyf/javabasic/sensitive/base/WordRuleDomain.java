package org.zyf.javabasic.sensitive.base;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/24  20:33
 */
@Data
public class WordRuleDomain {
    /**
     * 主键id
     */
    public long id;
    /**
     * 敏感词主键id
     */
    public long wordId;
    /**
     * 规则类型，0 or null 商品，1药品标品
     */
    public int type;
    /**
     * 门店主营品类Id，多个以逗号分隔
     */
    public String poiTagId;
    /**
     * 过滤场景  0-【敏感词拦截】1-【清真商家识别】
     */
    private int scene;
    /**
     * 商家类型  0-【全部商家】1-【清真商家】2-【非清真商家】
     */
    private int merchantType;
    /**
     * 门店地区id，多个以逗号分隔
     */
    public String poiRegionId;
    /**
     * 过滤区域(1：商品名称 2：商品描述 3：分类名称 4：规格名称 5：属性 6：商品单位 7：属性值  8：分类描述)
     */
    @NotEmpty
    @Length(max = 50)
    public String field;
    /**
     * 0.非泛化、1.泛化
     */
    public int nlp;
    /**
     * 备注
     */
    public String remark;

    public void from(WordRule wordRule) {
        this.id = wordRule.getId();
        this.wordId = wordRule.getWordId();
        this.type = wordRule.getType();
        this.poiTagId = wordRule.getPoiTagId();
        this.scene = wordRule.getScene();
        this.merchantType = wordRule.getMerchantType();
        this.poiRegionId = wordRule.getPoiRegionId();
        this.field = wordRule.getField();
        this.nlp = wordRule.getNlp();
        this.remark = wordRule.getRemark();
    }

    public WordRule to() {
        WordRule wordRule = new WordRule();
        wordRule.setId(this.id);
        wordRule.setWordId(this.wordId);
        wordRule.setType(this.type);
        wordRule.setPoiTagId(this.poiTagId);
        wordRule.setScene(this.scene);
        wordRule.setMerchantType(this.merchantType);
        wordRule.setPoiRegionId(this.poiRegionId);
        wordRule.setField(this.field);
        wordRule.setNlp(this.nlp);
        wordRule.setRemark(this.remark);
        return wordRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WordRuleDomain)) {
            return false;
        }
        WordRuleDomain that = (WordRuleDomain) o;
        return getId() == that.getId() && getWordId() == that.getWordId()
                && getType() == that.getType()
                && getNlp() == that.getNlp()
                && Objects.equals(getPoiTagId(), that.getPoiTagId())
                && getScene() == that.getScene()
                && getMerchantType() == that.getMerchantType()
                && Objects.equals(getPoiRegionId(), that.getPoiRegionId())
                && Objects.equals(getField(), that.getField())
                && Objects.equals(getRemark(), that.getRemark());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWordId(), getType(), getPoiTagId(), getScene(), getMerchantType(),
                getPoiRegionId(), getField(), getNlp(), getRemark());
    }
}
