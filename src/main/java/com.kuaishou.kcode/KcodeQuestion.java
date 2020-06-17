package com.kuaishou.kcode;

import java.io.*;
import java.util.*;


public class KcodeQuestion {
    public static final int LEN_TIMESTAMP = 4201;
    public static final int LEN_MAX_SORT = 3500;
    public static long BASE_TIMESTAMP = 1587987930;
    static ArrayList<String> METHOD_NAME = new ArrayList<>(Arrays.asList("mockUser10", "getInfo4", "getInfo5", "getInfo6", "getInfo7", "cpuMonitor10", "getInfo8", "getInfo9", "main9", "main10", "mockUser1", "getInfo1", "mockUser2", "getInfo2", "mockUser3", "mockUser4", "getInfo3", "getUserName10", "checkPass9", "checkPass8", "checkPass7", "checkPass6", "checkPass5", "checkPass4", "checkPass3", "checkPass2", "checkPass1", "mockUser5", "mockUser6", "mockUser7", "mockUser8", "mockUser9", "main6", "main5", "main8", "main7", "main2", "main1", "main4", "main3", "checkPass10", "getInfo10", "cpuMonitor1", "login9", "getUserName2", "getUserName3", "getUserName1", "cpuMonitor4", "login8", "getUserName6", "cpuMonitor5", "getUserName7", "login7", "cpuMonitor2", "login6", "getUserName4", "cpuMonitor3", "login5", "getUserName5", "cpuMonitor8", "login4", "login3", "cpuMonitor9", "login2", "getUserName8", "cpuMonitor6", "cpuMonitor7", "getUserName9", "login1"));
    //    HashMap<String, String[]> main_map = new HashMap<>();
    //    static String[] cache_keys = new String[1024];

    static String[][] main_map = new String[LEN_TIMESTAMP][1024];  // 时间戳 hashcode


    public void prepare(InputStream inputStream) throws InterruptedException, IOException {
//        int[] tmp = new int[1024];
//        for (int i = 0; i < METHOD_NAME.size(); i++) {
//            tmp[KcodeTools.get_hashcode(METHOD_NAME.get(i))] = i;
//        }
//        for (int i = 0; i < 1024; i++) {
//            System.out.print(tmp[i] + ",");
//        }
//        System.out.println();

        BufferedReader buffered_reader = new BufferedReader(new InputStreamReader(inputStream));
        char now_key = ' ';
        ArrayList<String> now_array = new ArrayList<>(30000);

        String input_string;
        // 预读一行
        input_string = buffered_reader.readLine();
        now_array.add(input_string);
        now_key = input_string.charAt(9);
        BASE_TIMESTAMP = Long.parseLong(input_string.substring(0, 10));
        // 循环读剩下的行
        while ((input_string = buffered_reader.readLine()) != null) {
            // 读取一行
            char key = input_string.charAt(9);

            if (key == now_key) {
                now_array.add(input_string);
            } else {
//                long s1_start = System.currentTimeMillis();
//                System.out.println(now_array.size());

                ArrayList<String> finalNow_array = now_array;
                KcodeThread.thread_pool_executor.execute(() -> KcodeThread.new_task(main_map, finalNow_array));
                now_array = new ArrayList<>(30000);
                now_array.add(input_string);
                now_key = key;
//                long s1_end = System.currentTimeMillis();
//                time += s1_end - s1_start;
            }

        }
//        System.out.println(time);
        KcodeThread.thread_pool_executor.shutdown();
        while (!KcodeThread.thread_pool_executor.isTerminated()) ;

    }


    static String[] now_raw;
    long now_index = 0;

    public String getResult(Long timestamp, String method_name) {
        if (timestamp != now_index) {
            now_index = timestamp;
            now_raw = main_map[(int) (timestamp - BASE_TIMESTAMP)];
        }
        return now_raw[KcodeTools.get_hashcode(method_name)];

//        return main_map[(int) (timestamp - BASE_TIMESTAMP)][KcodeTools.get_hashcode(method_name)];
    }


}