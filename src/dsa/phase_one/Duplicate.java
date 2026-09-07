package dsa.phase_one;

import java.util.*;

public class Duplicate {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 4, 1, 6, 5, 2, 4};
    /*    boolean result = containsDuplicate(nums);
        System.out.println(result);
        System.out.println("------");
        System.out.println("Dups" + showDups(nums));

        System.out.println("------");
        System.out.println("Dups Occurences" + showDupsCount(nums));

        System.out.println("------");
        showDupsArr(nums);*/

        System.out.println("------");
        showDupsSortArr(nums);
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num))
                return true;
            seen.add(num);
        }
        return false;
    }

    public static Set<Integer> showDups(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num))
                dups.add(num);

            seen.add(num);
        }
        return dups;
    }

    public static Map<Integer, Integer> showDupsCount(int[] nums) {
        Map<Integer, Integer> numCountMap = new HashMap<>();

        for (int num : nums)
            numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);

        return numCountMap;
    }

    public static void showDupsArr(int[] nums) {

        int[] frequency = new int[100];
        // System.out.println(Arrays.toString(frequency));

        for (int num : nums) {
            frequency[num]++;
        }

        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] >= 1) {
                System.out.println(i + " occured " + frequency[i] + "times");
            }
        }

    }

    public static void showDupsSortArr(int nums[]) {

        Arrays.sort(nums);
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
              //  if (count > 1) {
                    System.out.println(nums[i-1] + " occured " + count + " times");
             //   }
                count = 1;
            }

        }
      //  if (count > 1) {
            System.out.println(nums[nums.length-1] + " occured " + count + " times");
      //  }

    }
}
