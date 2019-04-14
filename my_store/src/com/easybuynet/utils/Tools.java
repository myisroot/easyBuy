package com.easybuynet.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Tools {
    public static String format(Date data) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(data);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String toFuzzyParam(String param) {
        return "$" + param;
    }

    public static String getCode(int n) {
        String string = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";//保存数字0-9 和 大小写字母
        StringBuffer sb = new StringBuffer(); //声明一个StringBuffer对象sb 保存 验证码
        for (int i = 0; i < n; i++) {
            Random random = new Random();//创建一个新的随机数生成器
            int index = random.nextInt(string.length());//返回[0,string.length)范围的int值    作用：保存下标
            char ch = string.charAt(index);//charAt() : 返回指定索引处的 char 值   ==》赋值给char字符对象ch
            sb.append(ch);// append(char c) :将 char 参数的字符串表示形式追加到此序列  ==》即将每次获取的ch值作拼接
        }
        return sb.toString();//toString() : 返回此序列中数据的字符串表示形式   ==》即返回一个String类型的数据
    }

    public static String toUtf_8(String str) {
        try {
            return new String(str.getBytes("ISO8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }
}
