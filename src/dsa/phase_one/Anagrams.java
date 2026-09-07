package dsa.phase_one;

import java.util.*;

public class Anagrams {
    /*
    str1 = "anagram"
    str2 = "nagaram"

    //group anagrams
    ["eat", "tea", "tan", "ate", "nat", "bat"]
     */
    public static void main(String[] args) {
        String[] strArr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(Arrays.deepToString(groupAnagrams(strArr)));
        System.out.println("--------------");
        System.out.println(Arrays.deepToString(optimizedGroupAnagrams(strArr)));
    }

    public static boolean validAnagram(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;

        Map<Character, Integer> charMap = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            Character ch = str1.charAt(i);
            charMap.put(ch, charMap.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < str2.length(); i++) {
            Character ch = str2.charAt(i);
            if (charMap.containsKey(ch)) {
                charMap.put(ch, charMap.get(ch) - 1);
            }
        }

        for (Map.Entry<Character, Integer> map : charMap.entrySet()) {
            if (map.getValue() != 0)
                return false;
        }
        return true;
    }

    public static String[][] groupAnagrams(String[] strArr) {
        //["eat", "tea", "tan", "ate", "nat", "bat"]
        String[][] groupedAnagrams = {{strArr[0]}};

        for (int i = 1; i < strArr.length; i++) {
            boolean foundGroup = false;
            for (int j = 0; j < groupedAnagrams.length; j++) {
                if (validAnagram(strArr[i], groupedAnagrams[j][0])) {
                    foundGroup = true;
                    groupedAnagrams[j] = addToGroup(strArr[i], groupedAnagrams[j]);
                    break;
                }
            }
            if (!foundGroup)
                groupedAnagrams = addNewGroup(strArr[i], groupedAnagrams);
        }

        return groupedAnagrams;
    }

    public static String[] addToGroup(String elemToAdd, String[] oldGroup) {
        String[] newGroup = new String[oldGroup.length + 1];
        for (int i = 0; i < oldGroup.length; i++) {
            newGroup[i] = oldGroup[i];
        }
        newGroup[oldGroup.length] = elemToAdd;
        return newGroup;
    }

    public static String[][] addNewGroup(String elemToAdd, String[][] oldGroup) {
        String[][] newGroup = new String[oldGroup.length + 1][];
        for (int i = 0; i < oldGroup.length; i++) {
            newGroup[i] = oldGroup[i];
        }
        String[] arr = {elemToAdd};
        newGroup[oldGroup.length] = arr;
        return newGroup;
    }

    public static String[][] optimizedGroupAnagrams(String[] strArr) {
        //["eat", "tea", "tan", "ate", "nat", "bat"]
        Map<List<Integer>, List<String>> map = new HashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            List<Integer> list = getEncodedKey(strArr[i]);
            if (map.containsKey(list)) {
                List<String> group = map.get(list);
                group.add(strArr[i]);
                map.put(list, group);
            } else {
                List<String> newGroup = new ArrayList<>();
                newGroup.add(strArr[i]);
                map.put(list, newGroup);
            }
        }
        System.out.println(map.values());
        return new String[0][];
    }

    public static List<Integer> getEncodedKey(String str) {
        int[] freq = new int[26];
        List<Integer> key = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            freq[ch - 'a'] = freq[ch - 'a'] + 1;
        }
        for (int i = 0; i < freq.length; i++) {
            key.add(freq[i]);
        }
        return key;
    }
}
