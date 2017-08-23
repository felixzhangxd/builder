package com.firebugsoft.builder.jdbc.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.firebugsoft.builder.jdbc.def.ASCII;

/**
 * @author felix
 */
public final class StringUtils {
    private StringUtils() {}

    /**
     * 首字母 改 大写
     * 如:user => User
     */
    public static String toUpperFirst(String s) {
        byte[] bs = s.getBytes();
        ByteUtils.toUpperFirst(bs);
        return new String(bs);
    }

    /**
     * 首字母 改 小写
     * 如:User => user
     */
    public static String toLowerFirst(String s) {
        byte[] bs = s.getBytes();
        ByteUtils.toLowerFirst(bs);
        return new String(bs);
    }

    /**
     * 下划线命名 转 大驼峰命名
     * 如: user_name => UserName
     */
    public static String toUpperCamelCase(String underScoreCase) {
        byte[] bs = underScoreCase.getBytes();
        ByteUtils.toUpperFirst(bs);
        int index = 0;
        for (int i = 0; i < bs.length; i++) {
            bs[index++] = bs[i] == ASCII.UNDER_SCORE ? ByteUtils.toUpperCase(bs[++i]) : bs[i];
        }
        return new String(bs, 0, index);
    }

    /**
     * 下划线命名 转 小驼峰命名
     * 如: user_name => userName
     */
    public static String toLowerCamelCase(String underScoreCase) {
        byte[] bs = underScoreCase.getBytes();
        int index = 0;
        for (int i = 0; i < bs.length; i++) {
            bs[index++] = bs[i] == ASCII.UNDER_SCORE ? ByteUtils.toUpperCase(bs[++i]) : bs[i];
        }
        return new String(bs, 0, index);
    }

    /**
     * 驼峰命名 转 下划线命名
     * 如: UserName => user_name
     * 如: userName => user_name
     */
    public static String toUnderScoreCase(String camelCase) {
        byte[] bs = camelCase.getBytes();
        ByteUtils.toLowerFirst(bs);
        byte[] underScoreCases = new byte[bs.length * 2];
        int index = 0;
        for (byte b : bs) {
            if (ByteUtils.isUpperCase(b)) {
                underScoreCases[index++] = ASCII.UNDER_SCORE;
            }
            underScoreCases[index++] = ByteUtils.toLowerCase(b);
        }
        return new String(underScoreCases, 0, index);
    }

    /**
     * 属性名称 转 get方法名
     * 如: userName => getUserName
     */
    public static String toGetMethod(String fieldName) {
        return "get" + StringUtils.toUpperFirst(fieldName);
    }

    /**
     * 属性名称 转 set方法名
     * 如: userName => setUserName
     */
    public static String toSetMethod(String fieldName) {
        return "set" + StringUtils.toUpperFirst(fieldName);
    }

    /**
     * pojo对象 转 String
     * 如: id:1, name:felix, pwd:pwd
     */
    public static String toString(Object pojo) {
        StringBuilder s = new StringBuilder();
        Class<?> cls = pojo.getClass();
        Field[] fs = cls.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            if (i > 0) {
                s.append(", ");
            }
            String name = fs[i].getName();
            s.append(name).append(":");
            try {
                s.append(cls.getDeclaredMethod(StringUtils.toGetMethod(name)).invoke(pojo));
            } catch (Exception e) {}
        }
        return s.toString();
    }
    
    /**
     * 清除空的、重复的字符串
     * 
     * eg：张三,李四,张三,,王五, ==> 张三,李四,王五
     * @param value
     * @param split
     * @return
     */
	public static String wipeRepeat(String value, String split) {
		if (value == null || split == null || "".equals(value.trim()) || "".equals(split.trim())) {
			return "";
		}
		String s = "";
		value = value.trim();
		split = split.trim();
		String[] values = value.split(split);
		List<String> list = new ArrayList<String>();
		for (String val : values) {
			if (val == null || "".equals(val.trim()) 
					|| "null".equals(val.trim())) {
				continue;
			}
			if (!list.contains(val.trim())) {
				s += val.trim() + ",";
				list.add(val.trim());
			}
		}
		if (!"".equals(s.trim())) {
			s = s.substring(0, (s.length() - 1));
		}
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtils.wipeRepeat(",  张三,李 四,张三 ,, 王五,", ","));
	}
}
