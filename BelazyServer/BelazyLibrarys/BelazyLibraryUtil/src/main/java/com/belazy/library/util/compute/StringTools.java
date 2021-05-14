package com.belazy.library.util.compute;


import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author tangcp
 */
public class StringTools {
  public static final String ENCODING_UTF_8 = "UTF-8";
  public static final String ENCODING_GB18030 = "GB18030";
  private static final char CHAR_BLANK = ' ';
  private static final char CHAR_ZERROR = '0';
  private static final String STR_COLON = ": ";
  private static final char CHAR_SEMICOLON = ';';
  private static final char CHAR_DOT = '.';
  private static final String STR_EMPTY = "";
  private static final char CHAR_NEWLINE = '\n';
  public static final String CHARSET_GBK = "GBK";
  public static final String CHARSET_GB2312 = "GB2312";
  public static final String CHARSET_GB18030 = "GB18030";
  public static final String CHARSET_UTF_8 = "UTF-8";
  public static final String CHARSET_UTF8 = "UTF8";
  private static final String VARIABLE_LABEL_BEGIN = "${";
  private static final String VARIABLE_LABEL_END = "}";
  
  public static byte[] string2bytes(String str, String encoding)
    throws Exception
  {
    if ((str.startsWith("0x")) || (str.startsWith("0X"))) {
      return hexString2bytes(str.substring(2));
    }
    if(StringUtil.isEmpty(encoding)){
      return str.getBytes();
    }
    return str.getBytes(encoding);
  }
  

  public static byte[] hexString2bytes(String str)
    throws Exception
  {
    if (str.length() % 2 != 0) {
      throw new Exception("HexString to byte[], length is not even number");
    }
    int len = str.length() / 2;
    byte[] newData = new byte[len];
    for (int i = 0; i < len; i++)
    {
      newData[i] = ((byte)(Character.digit(str.charAt(i * 2), 16) << 4 ^ Character.digit(str.charAt(i * 2 + 1), 16)));
    }
    
    return newData;
  }
  
  public static int searchDelimiterPosition(int index, byte[] data, byte[] delimiter, String encoding)
  {
    boolean isChar = false;
    if (("UTF-8".equalsIgnoreCase(encoding)) || ("GB18030".equalsIgnoreCase(encoding)))
    {


      int i = index; for (int n = data.length; i < n; i++)
      {
        if (contrastBytes(data, i, delimiter)) {
          return i;
        }
      }
    }
    else {
      int i = index; for (int n = data.length; i < n; i++)
      {

        if ((!isChar) && (data[i] < -1))
        {
          isChar = true;
        }
        else if ((isChar) || (data[i] < -1))
        {
          isChar = false;

        }
        else if (contrastBytes(data, i, delimiter)) {
          return i;
        }
      }
    }
    return data.length;
  }
  

  public static boolean contrastBytes(byte[] source, int index, byte[] target)
  {
    int len = target.length;
    if ((source.length - index < len) || (len == 0)) {
      return false;
    }
    for (int i = 0; i < len; i++)
    {
      if (source[(index + i)] != target[i]) {
        return false;
      }
    }
    return true;
  }
  
  public static String object2String(Object obj, String encoding)
  {
    if (obj == null) {
      return null;
    }
    if ((obj instanceof String)) {
      return (String) obj;
    }
    if ((obj instanceof byte[])) {
      try {
        return new String((byte[])obj, encoding);
      } catch (UnsupportedEncodingException e) {
        throw new RuntimeException("UnsupportedEncodingException", e);
      }
    }
    return obj.toString();
  }
  




  private static String hex2String(byte[] hex, String charset)
  {
    int length = hex.length;
    StringBuilder sb = new StringBuilder(32);
    try
    {
      for (int j = 0; j < length; j++)
      {
        char ch1 = (char)hex[j];
        if (ch1 <= ' ')
        {
          ch1 = '.';
        } else if (ch1 > '?')
        { if ("GB2312".equalsIgnoreCase(charset))
          {
            if (j + 1 <= length - 1)
            {
              char ch2 = (char)hex[(j + 1)];
              if (((byte)ch1 > -80) && ((byte)ch1 < -9) && ((byte)ch2 > -96) && ((byte)ch2 < -16))
              {



                sb.append(new String(hex, j, 2, charset));
                j++;
                continue;
              }
            }
          }
          if (("GBK".equalsIgnoreCase(charset)) || ("GB18030".equalsIgnoreCase(charset))){
            if (j + 1 <= length - 1)
            {
              char ch2 = (char)hex[(j + 1)];
              if (((byte)ch1 >= -127) && ((byte)ch1 <= -2) && (((byte)ch2 & 0xFF) >= 64) && ((byte)ch2 <= -2))
              {



                sb.append(new String(hex, j, 2, charset));
                j++;
                continue;
              }
            }
          } else if (("UTF8".equalsIgnoreCase(charset)) || ("UTF-8".equalsIgnoreCase(charset)))
          {

            if (j + 2 <= length - 1)
            {
              char ch2 = (char)hex[(j + 1)];
              char ch3 = (char)hex[(j + 2)];
              if (((byte)ch1 >= -32) && ((byte)ch2 >= Byte.MIN_VALUE))
              {

                String tmpStr = "";
                if ((byte)ch3 >= Byte.MIN_VALUE)
                {
                  tmpStr = new String(hex, j, 3, charset);
                  j += 2;
                }
                else {
                  tmpStr = new String(hex, j, 2, charset);
                  j++;
                }
                sb.append(tmpStr);
                continue;
              }
            }
          }
        }
        
        sb.append(ch1);
      }
    }
    catch (UnsupportedEncodingException e) {}
    


    return sb.toString();
  }
  










  public static String build(Map<String, String> context, String src)
  {
    return replaceVariables(context, src, "${", "}");
  }
  
  public static String buildRuntime(Map<String, String> context, String src) {
    return replaceVariables(context, src, "$(", ")");
  }
  
  public static String replaceVariables(Map context, String src, String begin, String end) {
    if ((src == null) || (context == null) || (begin == null) || (end == null)) {
      return src;
    }
    StringBuilder sb = new StringBuilder();
    
    int waitCopyBegin = 0;
    int vi = src.indexOf(begin, 0);
    while (vi >= 0) {
      int nvi = src.indexOf(begin, vi + begin.length());
      int vb = src.indexOf(end, vi + begin.length());
      if (vb <= 0){
        break;
      }
      if ((vb <= nvi) || (nvi < 0))
      {
        String variableKey = src.substring(vi + begin.length(), vb).trim();
        Object object = context.get(variableKey);
        String value = null;
        if (null != object) {
          value = object.toString();
        }
        if (value != null)
        {
          sb.append(src.substring(waitCopyBegin, vi));
          sb.append(value);
        }
        else {
          sb.append(src.substring(waitCopyBegin, vb + end.length()));
        }
        waitCopyBegin = vb + end.length();
        vi = src.indexOf(begin, vb + end.length());
      }
      else {
        sb.append(src.substring(waitCopyBegin, nvi));
        vi = nvi;
        waitCopyBegin = nvi;
      }
    }
    



    if (waitCopyBegin < src.length()) {
      sb.append(src.substring(waitCopyBegin, src.length()));
    }
    
    return sb.toString();
  }

}

