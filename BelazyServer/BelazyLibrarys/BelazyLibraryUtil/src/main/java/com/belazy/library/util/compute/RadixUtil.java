package com.belazy.library.util.compute;

/**
 * @author tangcp
 */
public class RadixUtil {

    private static final String DICTIONARY_CHAR26 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DICTIONARY_CHAR26_LOWER = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 将10进制转换为26进制
     *
     * @param num
     * @return
     */
    public static String to26By10(long num) {
        if (num < 0) {
            throw new RuntimeException ("Number must not less than 0,but value is " + num);
        }
        String s = "";
        while (num >= 26) {
            long t1 = num % 26;
            num = (num - t1) / 26;
            s = DICTIONARY_CHAR26_LOWER.charAt ((int) t1) + s;
        }
        s = DICTIONARY_CHAR26_LOWER.charAt ((int) num) + s;
        return s;
    }

    /**
     * 将26进制转换为10进制
     *
     * @param str
     * @return
     */
    public static long to10By26(String str) {

        if (isUpper (str)) {
            return to10By26Upper (str);
        }

        long num = 0;

        for (String s : str.split ("")) {
            if ("".equals (s)) {
                continue;
            }
            num = num * 26;
            int i = DICTIONARY_CHAR26_LOWER.indexOf (s);

            if (i == -1) {
                throw new RuntimeException ("Unkown char '" + s + "'");
            }
            num += i;
        }

        return num;
    }

    /**
     * 将10进制转换为26进制
     *
     * @param num
     * @return
     */
    public static String to26By10Upper(long num) {
        if (num < 0) {
            throw new RuntimeException ("Number must not less than 0,but value is " + num);
        }
        String s = "";
        while (num >= 26) {
            long t1 = num % 26;
            num = (num - t1) / 26;
            s = DICTIONARY_CHAR26.charAt ((int) t1) + s;
        }
        s = DICTIONARY_CHAR26.charAt ((int) num) + s;
        return s;
    }

    /**
     * 将26进制转换为10进制
     *
     * @param str
     * @return
     */
    public static long to10By26Upper(String str) {
        long num = 0;

        for (String s : str.split ("")) {
            if ("".equals (s)) {
                continue;
            }
            num = num * 26;
            int i = DICTIONARY_CHAR26.indexOf (s);

            if (i == -1) {
                throw new RuntimeException ("Unkown char '" + s + "'");
            }
            num += i;
        }

        return num;
    }

    /**
     * @param str
     * @return
     */
    public static boolean isLower(String str) {
        if (str == null || "".equals (str.trim ())) {
            return true;
        }
        String a = str.substring (0, 1);
        return DICTIONARY_CHAR26_LOWER.contains (a);
    }

    /**
     * @param str
     * @return
     */
    public static boolean isUpper(String str) {
        if (str == null || "".equals (str.trim ())) {
            return true;
        }
        String a = str.substring (0, 1);
        return DICTIONARY_CHAR26.contains (a);
    }


//	public static void main(String[] args) {
//		System.out.println(to10By26("cqq"));
//	}
}
