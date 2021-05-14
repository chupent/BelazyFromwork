package com.belazy.library.util.compute;

/**
 * @author tangcp
 */
public class ArrayUtil {

    /**
     * 分割数组 value1,value2,value3...
     *
     * @param arr
     * @return
     */
    public static String split(Object[] arr) {
        return split (arr, ",");

    }

    /**
     * 分割数组 value1+split+value2+split+value3...
     *
     * @param arr
     * @param split 分隔符
     * @return
     */
    public static String split(Object[] arr, String split) {

        if (arr == null) {
            return "null";
        }

        StringBuilder builder = new StringBuilder ();

        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                builder.append (split);
            }
            builder.append (arr[i]);
        }

        return builder.toString ();

    }

}
