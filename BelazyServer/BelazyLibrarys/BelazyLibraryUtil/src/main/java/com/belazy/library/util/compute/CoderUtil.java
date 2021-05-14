package com.belazy.library.util.compute;

import java.io.UnsupportedEncodingException;

/**
 * @author tangcp
 */
public class CoderUtil
{
  public static final String DEFAULT_CHARSET = "UTF-8";
  
  public static byte[] hexToBytes(char[] hex)
  {
    int length = hex.length / 2;
    byte[] raw = new byte[length];
    for (int i = 0; i < length; i++) {
      int high = Character.digit(hex[(i * 2)], 16);
      int low = Character.digit(hex[(i * 2 + 1)], 16);
      int value = high << 4 | low;
      if (value > 127) {
        value -= 256;
      }
      raw[i] = ((byte)value);
    }
    return raw;
  }
  
  public static byte[] hexToBytes(String hex) {
    return hexToBytes(hex.toCharArray());
  }
  

  public static String str2HexStr(String str)
  {
    char[] chars = "0123456789ABCDEF".toCharArray();
    StringBuilder sb = new StringBuilder("");
    try
    {
      byte[] bs = str.getBytes("utf-8");
      
      for (int i = 0; i < bs.length; i++) {
        int bit = (bs[i] & 0xF0) >> 4;
        sb.append(chars[bit]);
        bit = bs[i] & 0xF;
        sb.append(chars[bit]);
      }
      return sb.toString();
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }
  
  public static String getHexString(byte[] b) {
    String result = "";
    if (b == null) {
      return result;
    }
    for (int i = 0; i < b.length; i++) {
      result = result + Integer.toString((b[i] & 0xFF) + 256, 16).substring(1);
    }
    return result;
  }
  
  public static String hexStr2Str(String hexStr)
  {
    String str = "0123456789ABCDEF";
    char[] hexs = hexStr.toCharArray();
    byte[] bytes = new byte[hexStr.length() / 2];
    

    for (int i = 0; i < bytes.length; i++) {
      int n = str.indexOf(hexs[(2 * i)]) * 16;
      n += str.indexOf(hexs[(2 * i + 1)]);
      bytes[i] = ((byte)(n & 0xFF));
    }
    try {
      return new String(bytes, "UTF-8");
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }
}

