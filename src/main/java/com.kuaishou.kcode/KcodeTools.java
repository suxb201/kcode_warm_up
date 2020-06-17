package com.kuaishou.kcode;

import java.util.ArrayList;
import java.util.Collections;

public class KcodeTools {
    public static int list_max(ArrayList<Integer> the_list) {
        int ans = 0;
        for (int item : the_list) {
            ans = Math.max(ans, item);
        }
        return ans;
    }

    public static int list_min(ArrayList<Integer> the_list) {
        int ans = 0;
        for (int item : the_list) {
            ans = Math.min(ans, item);
        }
        return ans;
    }

    public static int list_sum(ArrayList<Integer> the_list) {
        int ans = 0;
        for (int item : the_list) {
            ans += item;
        }
        return ans;
    }

    public static void bucket_sort(ArrayList<Integer> the_list) {
        int max_number = list_max(the_list);
        int min_number = list_min(the_list);
        int list_size = the_list.size();
        double bucket_range = (max_number - min_number + 1.0) / list_size;

        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(list_size);
        for (int i = 0; i < list_size; ++i) {
            buckets.add(new ArrayList<>(10));
        }

        for (int item : the_list) {
            int bucket_index = (int) ((item - min_number) / bucket_range) % list_size;
            buckets.get(bucket_index).add(item);
        }

        int pointer = 0;
        for (int i = 0; i < list_size; ++i) {
            Collections.sort(buckets.get(i));
            for (int j = 0; j < buckets.get(i).size(); ++j) {
                the_list.set(pointer, buckets.get(i).get(j));
                ++pointer;
            }
        }
    }

    public static String[] split(String src, String delimeter) {
        int i = src.indexOf(delimeter);
        int j = i;
        int n = 0;
        int lastIndex = src.length() - delimeter.length();
        boolean lastStringIsDelimeter = false;
        while (i >= 0) {
            n++;
            i = src.indexOf(delimeter, i + delimeter.length());
            if (i == lastIndex) { // delimeter is the last string of the src, should not be counted
                lastStringIsDelimeter = true;
                break;
            }
        }
        String[] array = new String[n + 1];
        n = i = 0;
        while (j >= 0) {
            if (j - i > 0) {
                array[n++] = src.substring(i, j);
            } else if (j - i == 0) { // two delimeter is neighbour
                array[n++] = "";
            }
            i = j + delimeter.length();
            j = src.indexOf(delimeter, i);
        }
        if (!lastStringIsDelimeter) {
            array[n] = src.substring(i);
        }
        return array;
    }

//    static int[] index_map = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 12, 14, 15, 27, 28, 29, 30, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 26, 25, 24, 23, 22, 21, 20, 19, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 68, 63, 61, 60, 57, 54, 52, 48, 43, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 37, 36, 39, 38, 33, 32, 35, 34, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 41, 11, 13, 16, 1, 2, 3, 4, 6, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 46, 44, 45, 55, 58, 49, 51, 64, 67, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 42, 53, 56, 47, 50, 65, 66, 59, 62, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static int get_hashcode(String s) {
//        if (s.charAt(2) == 't') return index_map[(s.charAt(4) - 'a') * 10 + s.charAt(s.length() - 1) - '0'];
//        return index_map[(s.charAt(2) - 'a') * 10 + s.charAt(s.length() - 1) - '0'];
        if (s.charAt(2) == 't') return (s.charAt(4) - 'a') * 10 + s.charAt(s.length() - 1) - '0';
        return (s.charAt(2) - 'a') * 10 + s.charAt(s.length() - 1) - '0';
    }
}
