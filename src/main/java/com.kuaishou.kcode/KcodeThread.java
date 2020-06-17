package com.kuaishou.kcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class KcodeThread {

    public static ThreadPoolExecutor thread_pool_executor = new ThreadPoolExecutor(
            16,
            16,
            1,
            TimeUnit.DAYS,
            // 阻塞队列
            new ArrayBlockingQueue<>(16),
            // 使它变得阻塞
            (r, executor) -> {
                if (!executor.isShutdown()) {
                    try {
//                        System.out.println(executor.getQueue().size() + "size");
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
    );

    // min_map 时间戳 method_name
    public static void new_task(String[][] main_map, ArrayList<String> array_list) {
        if (array_list.size() == 0) return;
        // 更新 tmp map
        HashMap<String, ArrayList<Integer>> tmp_map = new HashMap<>(100);
        int index = -1;
        for (String item : array_list) {

            String[] split = KcodeTools.split(item, ",");

            //
            if (index == -1) {
                index = (int) (Long.parseLong(split[0].substring(0, 10)) - KcodeQuestion.BASE_TIMESTAMP);
            }

            String method_name = split[1];
            int time_used = Integer.parseInt(split[2]);

            if (!tmp_map.containsKey(method_name)) {
                tmp_map.put(method_name, new ArrayList<>(KcodeQuestion.LEN_MAX_SORT));
            }
            ArrayList<Integer> tmp_list = tmp_map.get(method_name);
            tmp_list.add(time_used);
        }
        // 通过 tmp map 更新 main map
//        System.out.println(tmp_map.keySet());
//        for (String str : tmp_map.keySet()) {
//            System.out.print('"' + str + '"' + ',');
//        }
//        System.out.println("\n");
        for (String key : tmp_map.keySet()) {

            // 没有这个 key 就添加
//            if (!main_map.containsKey(key)) {
//                main_map.put(key, new String[KcodeQuestion.LEN_TIMESTAMP]);
//            }

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
            main_map[index][KcodeTools.get_hashcode(key)] = String.format("%d,%d,%d,%d,%d", size, p99_result, p50_result, avg, max);
        }
    }
}
