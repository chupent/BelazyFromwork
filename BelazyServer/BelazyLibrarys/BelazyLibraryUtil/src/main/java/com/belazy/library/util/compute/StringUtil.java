package com.belazy.library.util.compute;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author tangcp
 */
public class StringUtil {

    private static String dictionaryChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+-?$%#!^";
    private static String defaultChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final char SEPARATOR = '_';
    public static final String EMPTY = "";
    private static final String CHARSET_NAME = "UTF-8";
    private static final Pattern PATTERN = Pattern.compile ("^-?[0-9]+(.[0-9]+)$");


    public static String getRandomKey32() {
        StringBuilder sb = new StringBuilder (32);
        SecureRandom random = new SecureRandom ();
        for (int i = 0; i < 32; i++) {
            sb.append (dictionaryChar.charAt (random.nextInt (dictionaryChar.length ())));
        }
        return sb.toString ();
    }

    public static String getRandomKey(int length) {
        StringBuilder sb = new StringBuilder (length);
        SecureRandom random = new SecureRandom ();
        for (int i = 0; i < length; i++) {
            sb.append (defaultChar.charAt (random.nextInt (defaultChar.length ())));
        }
        return sb.toString ();
    }

    public static boolean isValidDouble(String str, boolean isNegative) {
        if (str == null) {
            return false;
        }
        double dblString;
        try {
            dblString = Double.valueOf (str.trim ()).doubleValue ();
        } catch (Exception exc) {
            return false;
        }

        return (isNegative) || (dblString >= 0.0D);
    }

    public static boolean isEmpty(String str) {
        return (str == null) || ("".equals (str.trim ()));
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int strLen = str.length ();

        if (strLen < 1) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isDigit (str.charAt (i))) && (!Character.isWhitespace (str.charAt (i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphabet(String str) {
        if (str == null) {
            return false;
        }
        int strLen = str.length ();

        if (strLen < 1) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isLetter (str.charAt (i))) && (!Character.isWhitespace (str.charAt (i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkStringFormat(String str, String format) {
        boolean result = true;

        if (str.length () != format.length ()) {
            return false;
        }
        for (int i = 0; i < str.length (); i++) {
            char formatChar = format.charAt (i);
            char thisChar = str.charAt (i);
            switch (formatChar) {
                case 'A':
                    if (((thisChar >= 'A') && (thisChar <= 'Z')) || ((thisChar >= 'a') && (thisChar <= 'z'))) {
                        continue;
                    }
                    result = false;
                    break;
                case '9':
                    if ((thisChar >= '0') && (thisChar <= '9')) {
                        continue;
                    }
                    result = false;
                    break;
                case '$':
                    if (((thisChar < 'A') || (thisChar > 'Z')) && ((thisChar < 'a') || (thisChar > 'z')) && ((thisChar < '0') || (thisChar > '9'))) {
                        continue;
                    }
                    result = false;
                    break;
                default:
                    result = false;
            }
        }
        return result;
    }

    public static boolean isAlphabet(char c) {
        if ((getASCIICode (c) >= 97) && (getASCIICode (c) <= 122)) {
            return true;
        }
        return (getASCIICode (c) >= 65) && (getASCIICode (c) <= 90);
    }

    public static boolean isNumeric(char c) {
        return (getASCIICode (c) >= 48) && (getASCIICode (c) <= 57);
    }

    public static int getASCIICode(char c) {
        return c;
    }

    public static int getIntValue(char c) {
        String s = String.valueOf (c);
        return Integer.parseInt (s);
    }

    public static boolean checkEmail(String strMailAddress) {
        boolean result = true;
        if (strMailAddress == null) {
            return false;
        }
        if ("".equals (strMailAddress)) {
            return true;
        }
        if ((strMailAddress.indexOf ("@") < 0) || (strMailAddress.indexOf (".") < 0)) {
            return false;
        }
        for (int i = 0; i < strMailAddress.length (); i++) {
            if ((Character.isWhitespace (strMailAddress.charAt (i))) || (Character.isLetterOrDigit (strMailAddress.charAt (i)))
                    || (strMailAddress.charAt (i) == '@') || (strMailAddress.charAt (i) == '.') || (strMailAddress.charAt (i) == '_')) {
                continue;
            }
            result = false;
        }
        return result;
    }

    public static boolean checkTelNumber(String strTelNumber) {
        boolean result = true;
        if (strTelNumber == null) {
            return false;
        }
        if ("".equals (strTelNumber)) {
            return true;
        }
        for (int i = 0; i < strTelNumber.length (); i++) {
            if ((Character.isDigit (strTelNumber.charAt (i))) || (strTelNumber.charAt (i) == '-') || (strTelNumber.charAt (i) == '(')
                    || (strTelNumber.charAt (i) == ')')) {
                continue;
            }
            result = false;
        }
        return result;
    }

    public static String replaceQuotationMarks(String str) {
        if (str == null) {
            return str;
        }
        StringBuffer strBuf = new StringBuffer ("");
        for (int i = 0; i < str.length (); i++) {
            char c = str.charAt (i);
            if (c == '\'') {
                strBuf.append ('\'');
                strBuf.append ('\'');
            } else {
                strBuf.append (c);
            }
        }
        return strBuf.toString ();
    }

    public static String parseHtml(String str) {
        if ((str == null) || (str.length () == 0)) {
            return str;
        }
        char[] aryToParse = str.toCharArray ();
        StringBuffer strBf = new StringBuffer ();

        for (int i = 0; i < aryToParse.length; i++) {
            char c = aryToParse[i];
            switch (c) {
                case '<':
                    strBf.append ("&lt;");
                    break;
                case '>':
                    strBf.append ("&gt;");
                    break;
                case '&':
                    strBf.append ("&amp;");
                    break;
                case '"':
                    strBf.append ("&quot;");
                    break;
                case ' ':
                    strBf.append ("&nbsp;");
                    break;
                default:
                    strBf.append (c);
            }
        }
        String tmp = strBf.toString ();
        tmp = tmp.replaceAll ("\n", "<br>");
        tmp = tmp.replaceAll ("\t", "   ");
        return tmp;
    }

    public static String parseQuoter(String str) {
        return str.replaceAll ("'", "''");
    }

    public static String getIdxValue(String srcStr, String dividStr, int idx) {
        int i = 0;
        String result = "";
        while (i <= idx) {
            int idx1 = srcStr.indexOf (dividStr);
            if (idx1 == -1) {
                result = srcStr;
                break;
            }
            result = srcStr.substring (0, idx1);
            srcStr = srcStr.substring (idx1 + 1, srcStr.length ());
            i++;
        }
        return result;
    }

    /**
     * str 转换编码 iso8859-1 -> UTF-8
     *
     * @param str
     * @return
     */
    // reqeust.setCharacterEncoding()
    public static String parseEncoding(String str) {
        String string = str;
        try {
            string = new String (str.getBytes ("iso8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        return string;
    }

    /**
     * 获取yyyyMMddHHmmss 时间戳
     *
     * @return
     */
    public static String getTimestamp() {
        SimpleDateFormat fmt = new SimpleDateFormat ("yyyyMMddHHmmss");
        return fmt.format (new Date ());
    }

    public static String getRandomNum(int size) {

        String random = (int) (Math.random () * Math.pow (10, size)) + "";
        int length = random.length ();
        if (length < size) {
            random = "0" + random;
            length++;
        }
        return random;

    }

    /**
     * 验证是否是数字，数字可以带负号和小数点
     *
     * @param str
     * @return
     */
    public static boolean isNumericWithSign(String str) {
        if (isEmpty (str)) {
            return false;
        }
        Matcher isNum = PATTERN.matcher (str);
        if (!isNum.matches ()) {
            return false;
        }
        return true;
    }

    /**
     * 32位
     *
     * @param plainText
     * @return
     */
    public static String md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance ("MD5");
            md.update (plainText.getBytes ());
            byte[] b = md.digest ();
            int i;
            StringBuffer buf = new StringBuffer ("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append ("0");
                }
                buf.append (Integer.toHexString (i));
            }
            return buf.toString ();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace ();
        }
        return "null";
    }

    /**
     * 32位
     *
     * @param plainText
     * @return
     */
    public static String md5ByEncoding(String plainText, String encoding) {
        try {
            MessageDigest md = MessageDigest.getInstance ("MD5");
            md.update (plainText.getBytes (encoding));
            byte[] b = md.digest ();
            int i;
            StringBuffer buf = new StringBuffer ("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append ("0");
                }
                buf.append (Integer.toHexString (i));
            }
            // System.out.println("result: " + buf.toString());//32位的加密
            // System.out.println("result: " +
            // buf.toString().substring(8,24));//16位的加密
            return buf.toString ();
        } catch (NoSuchAlgorithmException e) {
            // block
            e.printStackTrace ();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        return "null";
    }

    /**
     * 16位
     *
     * @param plainText
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String md5With16(String plainText, String charset) throws UnsupportedEncodingException {
        return md5 (plainText, charset).substring (8, 24);
    }

    /**
     * 32位
     *
     * @param plainText
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String md5(String plainText, String charset) throws UnsupportedEncodingException {
        try {
            MessageDigest md = MessageDigest.getInstance ("MD5");
            md.update (plainText.getBytes (charset));
            byte[] b = md.digest ();
            int i;
            StringBuffer buf = new StringBuffer ("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append ("0");
                }
                buf.append (Integer.toHexString (i));
            }
            // System.out.println("result: " + buf.toString());//32位的加密
            // System.out.println("result: " +
            // buf.toString().substring(8,24));//16位的加密
            return buf.toString ();
        } catch (NoSuchAlgorithmException e) {
            // block
            e.printStackTrace ();

        }
        return "null";
    }

    /**
     * 16位
     *
     * @param plainText
     * @return
     */
    public static String md5With16(String plainText) {
        return md5 (plainText).substring (8, 24);
    }

    /**
     * @param in
     * @return
     * @throws IOException
     */
    public static String getFromInputSream(InputStream in) throws IOException {
        return getFromInputSream (in, "UTF-8");

    }

    /**
     * @param in
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String getFromInputSream(InputStream in, String encoding) throws IOException {

        BufferedReader reader = new BufferedReader (new InputStreamReader (in, encoding));

        StringBuffer buff = new StringBuffer ();

        String line = null;
        while ((line = reader.readLine ()) != null) {
            buff.append (line).append ("\n");
        }
        if (buff.toString ().endsWith ("\n")) {
            buff.deleteCharAt (buff.length () - 1);
        }
        return buff.toString ();

    }

    public static List<Integer> toIntegerList(String str) {
        List<Integer> idList = new ArrayList ();
        if (!isEmpty (str)) {
            String realStr = str.replace ("[", "").replace ("]", "");
            if (!isEmpty (realStr)) {
                String[] idsArray = realStr.split (",");
                for (int i = 0; i < idsArray.length; i++) {
                    if (!"null".equals (idsArray[i])) {
                        idList.add (Integer.valueOf (Integer.parseInt (idsArray[i])));
                    }
                }
            }
        }
        return idList;
    }

    public static List<String> toStringList(String str) {
        List<String> idList = new ArrayList ();
        if (!isEmpty (str)) {
            String[] idsArray = str.split (",");
            for (int i = 0; i < idsArray.length; i++) {
                idList.add (idsArray[i]);
            }
        }
        return idList;
    }

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes (CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换为字节数组
     *
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String (bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 序列化对象
     *
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            if (object != null) {
                baos = new ByteArrayOutputStream ();
                oos = new ObjectOutputStream (baos);
                oos.writeObject (object);
                return baos.toByteArray ();
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }


    /**
     * 反序列化对象
     *
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            if (bytes != null && bytes.length > 0) {
                bais = new ByteArrayInputStream (bytes);
                ObjectInputStream ois = new ObjectInputStream (bais);
                return ois.readObject ();
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }
}