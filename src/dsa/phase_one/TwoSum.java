package dsa.phase_one;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        /*
        Input:
        nums = [2, 7, 11, 15]
        target = 9

        Expected Output:
        [0, 1]
         */
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(returnIndexesTwoSum(nums, target)));

        int[] nums2 = {-5, -2, 7, 10};
        int target2 = 5;
        System.out.println(Arrays.toString(returnIndexesTwoSum(nums2, target2)));

        int[] nums3 = {-10, 5, 2, 8, -3};
        int target3 = -5;
        System.out.println(Arrays.toString(returnIndexesTwoSum(nums3, target3)));

    }

    public static int[] returnIndexesTwoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int needed;
        for (int i=0;i<nums.length;i++) {
            needed = target - nums[i];
            if(map.containsKey(needed)) {
                return new int[] {map.get(needed), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }


}
