package com.kuaishou.kcode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kcode
 * Created on 2020-05-20
 */
public class KcodeMain {

    public static void main(String[] args) throws Exception {
        // "demo.data" 是你从网盘上下载的测试数据，这里直接填你的本地绝对路径

        String path_input = "./data/warmup-test.data";
        String path_output = "./data/result-test.data";

        Class<?> clazz = Class.forName("com.kuaishou.kcode.KcodeQuestion");
        Object instance = clazz.newInstance();
        Method prepareMethod = clazz.getMethod("prepare", InputStream.class);
        Method getResultMethod = clazz.getMethod("getResult", Long.class, String.class);
        // 调用prepare()方法准备数据

        long s1_start = System.currentTimeMillis();
        prepareMethod.invoke(instance, new FileInputStream(path_input));
        long s1_end = System.currentTimeMillis();
        long s1 = s1_end - s1_start;

        long s2 = 0;
        for (int i = 0; i < 10; i++) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path_output)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\|");
                String[] keys = split[0].split(",");
                // 调用getResult()方法
                long s2_start = System.currentTimeMillis();
                Object result = getResultMethod.invoke(instance, Long.valueOf(keys[0]), keys[1]);
                long s2_end = System.currentTimeMillis();
                s2 += s2_end - s2_start;
                if (!split[1].equals(result)) {
                    System.out.println("data  result: " + split[1]);
                    System.out.println("my result: " + result);
                    System.out.println("fail");
                    break;
                }
            }

        }

        System.out.println("s1 time used: " + s1);
        System.out.println("s2 time used: " + s2);
        System.out.println("total time used: " + (s1 + s2));
    }
}