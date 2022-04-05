package org.zyf.javabasic.test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/8/17  14:32
 */
public class VerifyResultTest {

    @Data
    public static class VerifyResult {
        public long categoryId;
        public long wmPoiId;
        public State qualificationState;
        public State wmPoiTagState;
        public State state;
        public List<Deficient> deficientAndQualificationList;
        public List<Deficient> deficientOrQualificationList;
        public List<Deficient> wmPoiTagDeficientList;
    }

    public enum State {
        LEGAL(0),
        ILLEGAL(1),
        IN_GRACE_PERIOD(2);

        private final int value;

        private State(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static State findByValue(int value) {
            switch (value) {
                case 0:
                    return LEGAL;
                case 1:
                    return ILLEGAL;
                case 2:
                    return IN_GRACE_PERIOD;
                default:
                    return null;
            }
        }
    }

    @Data
    public static class Deficient {
        public String message;
        public long wmPoiTagId;
        public String qualificationKey;
        public long quaPackageId;
        public long quaItemId;
        public String quaItemName;
    }

    public static class ShangouMerchantAdmissionUtils {
        public static boolean isMedicalQuaKey(String qualificationKey) {
            String shangouMedicalQuaKeyStr = "1@3,2@3,3@2";
            if (StringUtils.isBlank(shangouMedicalQuaKeyStr)) {
                /*如果对应未配置医疗器械指定的资质内容，则直接返回false，认为不是指定医疗资质*/
                return false;
            }

            List<String> shangouMedicalQuaKeyList = Splitter.on(",").splitToList(shangouMedicalQuaKeyStr);
            return shangouMedicalQuaKeyList.contains(String.valueOf(qualificationKey));
        }
    }

    public static void main(String[] args) {
        VerifyResult verifyResult = new VerifyResult();
        List<Deficient> deficientAndQualificationList1 = Lists.newArrayList();
        Deficient deficient1 = new Deficient();
        deficient1.setQualificationKey("1@3");
        Deficient deficient2 = new Deficient();
        deficient2.setQualificationKey("2@3");
        Deficient deficient3 = new Deficient();
        deficient3.setQualificationKey("3@2");
        deficientAndQualificationList1.add(deficient1);
        deficientAndQualificationList1.add(deficient2);
        verifyResult.setDeficientAndQualificationList(deficientAndQualificationList1);
        verifyResult.setDeficientAndQualificationList(deficientAndQualificationList1);
        System.out.println("" + verifyResult);
        List<Deficient> refreshDeficientAndQualificationList;
        List<Deficient> deficientAndQualificationList = verifyResult.getDeficientAndQualificationList();
        if (CollectionUtils.isNotEmpty(deficientAndQualificationList)) {
            /*进行过滤：只保留非指定医疗器械资质的内容*/
            refreshDeficientAndQualificationList = deficientAndQualificationList.stream()
                    .filter(deficient -> !ShangouMerchantAdmissionUtils.isMedicalQuaKey(deficient.getQualificationKey())).collect(Collectors.toList());
            verifyResult.setDeficientAndQualificationList(refreshDeficientAndQualificationList);
        }

        System.out.println("" + verifyResult);

        List<Deficient> deficientOrQualificationList = verifyResult.getDeficientAndQualificationList();
        if (CollectionUtils.isNotEmpty(deficientOrQualificationList)) {
            /*进行过滤：只保留非指定医疗器械资质的内容*/
            for (Deficient deficient : deficientOrQualificationList) {
                if (ShangouMerchantAdmissionUtils.isMedicalQuaKey(deficient.getQualificationKey())) {
                    deficientOrQualificationList.remove(deficient);
                }
            }
            verifyResult.setDeficientOrQualificationList(deficientOrQualificationList);
        }

        System.out.println("" + verifyResult);

        List<String> list = Lists.newArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        for (int i = list.size() - 1; i > -1; i--) {
            list.remove(i);
        }
        System.out.println("-----------------" + list);
    }

}
