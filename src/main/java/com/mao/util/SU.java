package com.mao.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 字符串工具类
 * @author mao by 10:55 2019/9/18
 */
public class SU {

    /**
     * 判断字符串是否全是字母
     * @param str 字符串
     * @return boolean
     */
    public static boolean isZM(String str){
        for (char c : str.toCharArray()) {
            if (!((c >= 'a' && c <= 'z')||(c >= 'A' && c <= 'Z')))
                return false;
        }
        return true;
    }

    /**
     * 判断字符串是否为空
     * 仿写commons-lang3
     * @param str str
     * @return true/false
     */
    public static boolean isEmpty(String str){
        // " " is false
        return null == str || str.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     * 仿写commons-lang3
     * @param str str
     * @return true/false
     */
    public static boolean isNotEmpty(String str){
        // " " is true
        return null != str && str.length() > 0;
    }

    /**
     * 判断字符串是否为空白
     * 只有空格判定为空白
     * @param str 字符串
     * @return boolean
     */
    public static boolean isBlank(String str){
        // " " is false
        return isEmpty(str) || isEmpty(str.trim());
    }

    /**
     * 字符串转换成float
     * 转换失败返回null
     * @param str String
     * @return Float
     */
    public static float getFloat(String str){
        if (isEmpty(str)) return 0F;
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return 0F;
        }
    }

    public static void split(String str){
        char[] chars = str.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for (char c : chars)
            if (!(c >= 8 && c <= 13) && c != 32)
                map.put(c,map.containsKey(c) ? map.get(c) + 1 : 1);
        map.forEach((key,value) -> System.out.println(key+":"+value));
    }

    public static String uuid(){
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {

    }

}