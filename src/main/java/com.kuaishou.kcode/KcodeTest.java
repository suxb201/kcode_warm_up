package com.kuaishou.kcode;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class KcodeTest {
    public static void main(String[] args) throws Exception {
        read_test();
//        HashMap<String, String> map = new HashMap<>();
//        String[] array = new String[1024];
//        String s = "sdfgv";
//        array[KcodeTools.get_hashcode((s))] = "1234";
//        map.put(s, "1234");
//
//        long s1 = 0, s2 = 0;
//        for (int i = 0; i < 190000000; i++) {
//            s = "1dfg" + (i & 127);
//            long s1_start = System.currentTimeMillis();
//            String tmp_s = map.get(s);
//            long s1_end = System.currentTimeMillis();
//            s1 += s1_end - s1_start;
//
//            long s2_start = System.currentTimeMillis();
//            String tmp_s2 = array[KcodeTools.get_hashcode(s)];
//            long s2_end = System.currentTimeMillis();
//            s2 += s2_end - s2_start;
//        }
//        System.out.println(s1 + " " + s2);
    }

    private static void read_test() throws IOException {
        String path_input = "./data/warmup-test.data";
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(new FileInputStream(path_input))));
        in.nextToken();
        System.out.println(in.sval);

    }
}
//test//test//test