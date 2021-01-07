package com.zxj.singleton.hungry_singleton;

import java.util.*;

/**
 * @author littleboy
 * @description TODO
 * @date 2020/2/29 00:07
 */
public class Demo {
    public static void main(String[] args) {
        // str();
        str2();
    }

    public static void str() {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        String A = scanner.next();
        String B = scanner.next();
        int count = 0;
        StringBuilder stringBuffer = new StringBuilder(a);
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == A.charAt(0)) {
                stringBuffer.setCharAt(i, B.charAt(0));
                count ++;
            }
        }
        System.out.println("Result String:" + stringBuffer.toString());
        System.out.println("A count:" + count);
    }

    public static void str2() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        StringBuilder stringBuffer = new StringBuilder(String.valueOf(a));
        stringBuffer.reverse();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        Map<Integer, Character> map2 = new HashMap<Integer, Character>();
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (!map.containsKey(stringBuffer.charAt(i))) {
                map.put(stringBuffer.charAt(i), i);
                map2.put(i, stringBuffer.charAt(i));
            }
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 20; i++) {
            if (map.containsValue(i)) {
                buffer.append(map2.get(i));
            }
        }
        System.out.println(buffer.toString());
    }
}
