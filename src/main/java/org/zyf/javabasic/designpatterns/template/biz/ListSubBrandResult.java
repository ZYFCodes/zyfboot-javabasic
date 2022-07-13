package org.zyf.javabasic.designpatterns.template.biz;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/24  21:54
 */
public class ListSubBrandResult extends BaseResult {

    private static final long serialVersionUID = -3095024211312754143L;

    private List<SubBrand> list = Lists.newArrayList();

    public void convert(List<WmProductLibBrand> dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        for (WmProductLibBrand brand : dataList) {
            list.add(new SubBrand(brand.getId(), brand.getZhName() + brand.getEnName()));
        }
    }

    public void convert2CruxBrand(List<CRUXStandardProductLibBrand> dataList) {

        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        for (CRUXStandardProductLibBrand brand : dataList) {
            list.add(new SubBrand(brand.getId(), brand.getZhName() + brand.getEnName()));
        }
    }

    @Data
    static class SubBrand {
        private int id;
        private String name;

        public SubBrand() {
        }

        public SubBrand(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public List<SubBrand> getList() {
        return list;
    }

    public void setList(List<SubBrand> list) {
        this.list = list;
    }

    @Data
    static class WmProductLibBrand {
        public int id;
        public int parentId;
        public String idPath;
        public String namePath;
        public String enName;
        public String zhName;
        public int brandType;
        public int brandStatus;
        public int brandLevel;
        public String logoPic;
        public int cmid;
        public int ctime;
        public int umid;
        public int utime;
        public int valid;
    }

    @Data
    static class CRUXStandardProductLibBrand {
        public int id;
        public int parentId;
        public String idPath;
        public String namePath;
        public String enName;
        public String zhName;
        public int brandType;
        public int brandStatus;
        public int brandLevel;
        public String logoPic;
        public long cuid;
        public int ctime;
        public long muid;
        public int utime;
        public int valid;
        public String brandStory;
        public String brandRegLicence;
        public long brandRegDate;
        public String brandRegister;
        public String brandRegNumber;
        public String link;
        public String manufacturer;
        public int classification;
        public List<Long> categoryIdList;
        public boolean relAllCategory;
    }
}

