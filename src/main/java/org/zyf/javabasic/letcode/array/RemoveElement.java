package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 给你一个数组 nums和一个值 val，你需要原地移除所有数值等于val的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 例如： 给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * <p>
 * 说明： 注意这五个细节：
 * 不要考虑数组超出新长度后面的元素；
 * 数组长度可为 0；
 * 从前往后遍历数组；
 * 当遇到元素值等于 val 时，将当前元素与最后一个元素进行交换，并将数组长度减 1；
 * 交换后继续比较当前元素，直到当前元素不等于 val。
 * 需要注意的是，这个问题和「27. 移除元素」的主要区别是：数据范围从给定数组的长度扩展到了 10^4。
 * @date 2023/4/2  19:21
 */
public class RemoveElement {

    /**
     * 移除元素 (Remove Element)的最优解法是双指针法。
     * “双指针”技巧包括使用两个指针，分别从数组的两端开始遍历，直到它们相遇为止。
     * 在这种情况下，我们可以将一个指针用于遍历数组，并将另一个指针用于指向新数组中的最后一个元素。
     * <p>
     * 在本问题中，我们需要移除等于给定值的元素。
     * 因此，我们可以使用两个指针i和j，其中i是慢指针，而j是快指针。
     * 当nums[j]等于给定值时，我们递增j以跳过此元素。
     * 当nums[j]不等于给定值时，我们将nums[j]复制到nums[i]，然后递增i以继续遍历。
     * <p>
     * 具体步骤如下：
     * 初始化两个指针i和j，其中i指向数组的开头，而j指向数组的结尾。
     * 循环遍历数组nums，直到i和j相遇为止。
     * 如果nums[i]等于给定值，那么我们就将nums[i]覆盖为nums[j]，然后递增j。
     * 如果nums[i]不等于给定值，那么我们就递增i以继续遍历。
     * 返回新数组的长度，即为i的值。
     * 通过上述步骤，我们可以实现对数组的原地修改，并返回新数组的长度。
     */
    public static int removeElement(int[] nums, int val) {
        /*慢指针 i 指向当前数组中需要保留的元素的下标*/
        int i = 0;
        /*快指针 j 遍历整个数组*/
        for (int j = 0; j < nums.length; j++) {
            /*如果 nums[j] 不等于 val，说明该元素需要保留*/
            if (nums[j] != val) {
                /*将该元素移到 nums[i] 的位置上*/
                nums[i] = nums[j];
                /*i 向右移动一位*/
                i++;
            }
        }
        /*返回保留的元素的数量，即为新数组的长度*/
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        int len = removeElement(nums, val);
        System.out.println("New length: " + len);
        System.out.print("New array: ");
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }

}
