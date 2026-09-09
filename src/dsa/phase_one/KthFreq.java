package dsa.phase_one;

import java.util.*;
import java.util.stream.Collectors;

public class KthFreq {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 2, 2, 2, 2, 3};

        //[[1,1,1,1], [2,2,2,2], [3]]

        System.out.println("========================");
        System.out.println(Arrays.toString(getKthMostFreqElements(nums, 2)));
        System.out.println(Arrays.toString(optimizedGetTopKFreqElements(nums, 2)));
        System.out.println("==== final result =====");
        System.out.println(Arrays.toString(optimizedWithoutSecondMapGetTopKFreqElements(nums,2)));
    }

    public static int[] getKthMostFreqElements(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        List<Integer> integerList = entries.stream().map(Map.Entry::getValue).sorted(Comparator.reverseOrder()).limit(k).collect(Collectors.toList());
        int[] kFreValues = new int[k];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int x = integerList.get(index);
            if (entry.getValue().equals(x)) {
                kFreValues[index] = entry.getKey();
                index++;
            }
            if (index == 2)
                break;
        }

        return kFreValues;
    }

    public static int[] optimizedGetTopKFreqElements(int[] nums, int k) {

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }
        Map<Integer, List<Integer>> groupElemsByFreqMap = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (groupElemsByFreqMap.containsKey(entry.getValue())) {
                List<Integer> list = groupElemsByFreqMap.get(entry.getValue());
                list.add(entry.getKey());
            } else {
                List<Integer> groupList = new ArrayList<>();
                groupList.add(entry.getKey());
                groupElemsByFreqMap.put(entry.getValue(), groupList);
            }
        }
        System.out.println("*************");
        System.out.println(groupElemsByFreqMap.keySet());

        int maxFreq = Integer.MAX_VALUE;
        System.out.println("---------");
        int[] result = new int[k];
        int index = 0;
        for (int freq = nums.length; freq >= 1; freq--) {
            //System.out.println(freq);
            if (groupElemsByFreqMap.containsKey(freq)) {
                List<Integer> group = groupElemsByFreqMap.get(freq);
                for (int i = 0; i < group.toArray().length; i++) {
                    result[index] = group.get(i);
                    index++;
                    if (index == k)
                        return result;
                }
            }
        }


        return result;
    }

    public static int[] optimizedWithoutSecondMapGetTopKFreqElements(int[] nums, int k) {

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }
        //[[1,1,1,1], [2,2,2,2], [3]]
        List<List<Integer>> groupArrList = new ArrayList<>();
        /*
        groupArrList[4] = 1,2
        groupArrList[1] = 3

         */
        for (int i = 0; i <= nums.length; i++) {
            groupArrList.add(new ArrayList<>());
        }

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            groupArrList.get(entry.getValue()).add(entry.getKey());
        }

        int index = 0;
        int[] result = new int[k];

        for(int freq = nums.length; freq>=1;freq--) {
            List<Integer> list = groupArrList.get(freq);
            for (int i=0;i<list.size();i++) {
                result[index] = list.get(i);
                index++;
                if (index == 2)
                    return result;
            }
        }
        return result;
    }
}
