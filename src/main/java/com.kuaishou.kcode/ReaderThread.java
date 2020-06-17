package com.kuaishou.kcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ReaderThread extends Thread {

    HashMap<String, String> main_map;
    ArrayList<String> array_list;

    public ReaderThread(HashMap<String, String> main_map_, ArrayList<String> array_list_) {
        main_map = main_map_;
        array_list = array_list_;
    }


    public void run() {
        if (array_list.size() == 0) return;
        // 更新 tmp map
        HashMap<String, ArrayList<Integer>> tmp_map = new HashMap<>(100);

        for (String item : array_list) {

            String[] split = item.split(",");
            String method_name = split[1];
            int time_used = Integer.parseInt(split[2]);

            if (!tmp_map.containsKey(method_name)) {
                tmp_map.put(method_name, new ArrayList<>(3120));
            }
            ArrayList<Integer> tmp_list = tmp_map.get(method_name);
            tmp_list.add(time_used);
        }
        // 通过 tmp map 更新 main map
        for (String key : tmp_map.keySet()) {
            ArrayList<Integer> tmp_list = tmp_map.get(key);
            int size = tmp_list.size();
            int sum = KcodeTools.list_sum(tmp_list);

            KcodeTools.bucket_sort(tmp_list);

            int p99 = (int) Math.ceil(size * 0.99);
            int p50 = (int) Math.ceil(size * 0.5);

            int p99_result = tmp_list.get(p99 - 1);
            int p50_result = tmp_list.get(p50 - 1);
            int max = tmp_list.get(size - 1);
            int avg = (int) Math.ceil((double) sum / size);
            main_map.put(key, String.format("%d,%d,%d,%d,%d", size, p99_result, p50_result, avg, max));
        }
//        System.out.println("inner key set: " + main_map.keySet());
    }
}