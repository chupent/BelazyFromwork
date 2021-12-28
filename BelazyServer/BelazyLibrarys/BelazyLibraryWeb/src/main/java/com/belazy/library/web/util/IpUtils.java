package com.belazy.library.web.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * 获取IP方法
 *
 * @author tangcp
 */
@Slf4j
public class IpUtils {
    /**
     * 获取发起请求的浏览器名称
     */
    public static String getBrowserName(HttpServletRequest request) {
        String header = request.getHeader ("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString (header);
        Browser browser = userAgent.getBrowser ();
        return browser.getName ();
    }

    /**
     * 获取发起请求的浏览器版本号
     */
    public static String getBrowserVersion(HttpServletRequest request) {
        String header = request.getHeader ("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString (header);
        // 获取浏览器信息
        Browser browser = userAgent.getBrowser ();
        // 获取浏览器版本号
        Version version = browser.getVersion (header);
        return version.getVersion ();
    }

    /**
     * 获取发起请求的操作系统名称
     */
    public static String getOsName(HttpServletRequest request) {
        String header = request.getHeader ("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString (header);
        OperatingSystem operatingSystem = userAgent.getOperatingSystem ();
        return operatingSystem.getName ();
    }

    public static String getMac(String ipAddress) {
        String str = "";
        String macAddress = "";
        final String LOOPBACK_ADDRESS = "127.0.0.1";
        // 如果为127.0.0.1,则获取本地MAC地址。
        if (LOOPBACK_ADDRESS.equals (ipAddress)) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost ();
                // 貌似此方法需要JDK1.6。
                byte[] mac = NetworkInterface.getByInetAddress (inetAddress).getHardwareAddress ();
                // 下面代码是把mac地址拼装成String
                StringBuilder sb = new StringBuilder ();
                for (int i = 0; i < mac.length; i++) {
                    if (i != 0) {
                        sb.append ("-");
                    }
                    // mac[i] & 0xFF 是为了把byte转化为正整数
                    String s = Integer.toHexString (mac[i] & 0xFF);
                    sb.append (s.length () == 1 ? 0 + s : s);
                }
                // 把字符串所有小写字母改为大写成为正规的mac地址并返回
                macAddress = sb.toString ().trim ().toUpperCase ();
                return macAddress;
            }catch (SocketException se){
                log.error ("SocketException");
            }catch (UnknownHostException se){
                log.error ("UnknownHostException");
            }catch (Exception e){
                log.error ("Exception");
            }
        } else {
            try {  // 获取非本地IP的MAC地址
                System.out.println (ipAddress);
                log.info ("获取非本地IP:{};的MAC地址",ipAddress);
                Process p = Runtime.getRuntime ().exec ("nbtstat -A " + ipAddress);
                log.info ("===process=={}", p);
                InputStreamReader ir = new InputStreamReader (p.getInputStream ());
                BufferedReader br = new BufferedReader (ir);
                while ((str = br.readLine ()) != null) {
                    if (str.indexOf ("MAC") > 1) {
                        macAddress = str.substring (str.indexOf ("MAC") + 9, str.length ());
                        macAddress = macAddress.trim ();
                        log.info ("===macAddress=={}", macAddress);
                        break;
                    }
                }
                p.destroy ();
                br.close ();
                ir.close ();
            } catch (IOException ex) {
                log.error ("UnknownHostException");
            } catch (Exception e){
                log.error ("Exception");
            }
            return macAddress;
        }
        return null;
    }

    /**
     * 获取请求IP
     **/
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader ("x-forwarded-for");
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("X-Forwarded-For");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("X-Real-IP");
        }

        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getRemoteAddr ();
        }

        return "0:0:0:0:0:0:0:1".equals (ip) ? "127.0.0.1" : ip;
    }

    public static boolean internalIp(String ip) {
        byte[] addr = textToNumericFormatV4 (ip);
        return internalIp (addr) || "127.0.0.1".equals (ip);
    }

    private static boolean internalIp(byte[] addr) {
        if (Objects.isNull (addr) || addr.length < 2) {
            return true;
        }
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;
        }
    }

    /**
     * 将IPv4地址转换成字节
     *
     * @param text IPv4地址
     * @return byte 字节
     */
    public static byte[] textToNumericFormatV4(String text) {
        if (text.length () == 0) {
            return null;
        }

        byte[] bytes = new byte[4];
        String[] elements = text.split ("\\.", -1);
        try {
            long l;
            int i;
            switch (elements.length) {
                case 1:
                    l = Long.parseLong (elements[0]);
                    if ((l < 0L) || (l > 4294967295L))
                        return null;
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 2:
                    l = Integer.parseInt (elements[0]);
                    if ((l < 0L) || (l > 255L))
                        return null;
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt (elements[1]);
                    if ((l < 0L) || (l > 16777215L))
                        return null;
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 3:
                    for (i = 0; i < 2; ++i) {
                        l = Integer.parseInt (elements[i]);
                        if ((l < 0L) || (l > 255L))
                            return null;
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    l = Integer.parseInt (elements[2]);
                    if ((l < 0L) || (l > 65535L))
                        return null;
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 4:
                    for (i = 0; i < 4; ++i) {
                        l = Integer.parseInt (elements[i]);
                        if ((l < 0L) || (l > 255L))
                            return null;
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    break;
                default:
                    return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return bytes;
    }

    public static String getHostIp() {
        try {
            return InetAddress.getLocalHost ().getHostAddress ();
        } catch (UnknownHostException e) {
        }
        return "127.0.0.1";
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost ().getHostName ();
        } catch (UnknownHostException e) {
        }
        return "未知";
    }
}