package org.zyf.javabasic.dd;

/**
 * @author yanfengzhang
 * @description  给你提供一个无序的正整数数组，请你判断是否存在这样的元素，
 * 他出现的次数超过了数组容量的一半
 * @date 2023/7/15  11:47
 */
public class FindMajorNumber {

    public int findMajor(int[] nums){
        if(nums==null || nums.length<0){
            return 0;
        }

        int cadi = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(count == 0){
                cadi=nums[i];
            }
            count += (nums[i] ==cadi)?1:-1;
        }
        return cadi;
    }

    public static void main(String[] args) {
        int[] num={1,2,1,2,3};
        int res = new FindMajorNumber().findMajor(num);
        System.out.println(res);



    }
}
