package com.belazy.library.util.compute;

public class MathUtil {

    /**
     * 比较value1和value2
     *
     * @param value1
     * @param value2
     * @param compare 比较类型
     *                <br>eq当value1等于value2是返回true
     *                <br>neq当value1不等于value2是返回true
     *                <br>lt当value1小于value2是返回true
     *                <br>gt当value1大于value2是返回true
     *                <br>lte当value1小于或者等于value2是返回true
     *                <br>gte当value1大于或者等于value2是返回true
     * @return
     */
    public static boolean compare(int value1, int value2, String compare) {

        switch (compare) {
            case "eq":
                return value1 == value2;
            case "neq":
                return value1 != value2;
            case "lt":
                return value1 < value2;
            case "gt":
                return value1 > value2;
            case "lte":
                return value1 <= value2;
            case "gte":
                return value1 >= value2;
            default:
        }
        return false;
    }

}
