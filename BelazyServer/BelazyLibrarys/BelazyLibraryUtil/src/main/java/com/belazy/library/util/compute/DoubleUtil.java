package com.belazy.library.util.compute;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * double计算
 * <p>
 * double直接计算往往会出现精度丢失情况
 *
 * @author tangcp
 */
public class DoubleUtil {

    /**
     * 四舍五入
     *
     * @param value 值
     * @param ponit 保留小数位数
     * @return
     */
    public static double round(double value, int ponit) {
        BigDecimal bigDecimal = new BigDecimal (value);
        return bigDecimal.setScale (ponit, BigDecimal.ROUND_HALF_UP).doubleValue ();
    }

    /**
     * 非四舍五入，舍入模式舍入零
     *
     * @param value 值
     * @param ponit 保留小数位数
     * @return
     */
    public static double roundDown(double value, int ponit) {
        BigDecimal bigDecimal = new BigDecimal (value);
        return bigDecimal.setScale (ponit, BigDecimal.ROUND_DOWN).doubleValue ();
    }

    /**
     * 四舍五入,返回long类型
     *
     * @param value
     * @return
     */
    public static long round(double value) {
        return (long) round (value, 0);
    }

    /**
     * 加法运算，返回value1+value2的值
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double add(double value1, double value2) {
        return new BigDecimal (Double.toString (value1)).add (new BigDecimal (Double.toString (value2))).doubleValue ();
    }

    /**
     * 减法运算，返回value1-value2的值
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double subtract(double value1, double value2) {
        return new BigDecimal (Double.toString (value1)).subtract (new BigDecimal (Double.toString (value2))).doubleValue ();
    }

    /**
     * 乘法运算，返回value1*value2的值
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double multiply(double value1, double value2) {
        return new BigDecimal (Double.toString (value1)).multiply (new BigDecimal (Double.toString (value2))).doubleValue ();
    }

    /**
     * 除法运算，返回value1/value2的值
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double divide(double value1, double value2) {
        return new BigDecimal (Double.toString (value1)).divide (new BigDecimal (Double.toString (value2)), 10, RoundingMode.HALF_DOWN).doubleValue ();
    }

}
