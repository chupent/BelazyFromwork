package com.belazy.library.util.compute;

import java.math.BigDecimal;

/**
 * @author tangcp
 */
public class NumberFormatUtils {
    /**
     * double类型的数据保留num位小数
     *
     * @param value
     * @param num
     * @return
     */
    public static double format(Double value, int num) {
        if (value == Double.valueOf (value).intValue ()) {
            return Double.valueOf (value).intValue ();
        } else {
            BigDecimal bigDecimal = new BigDecimal (Double.toString (value));
            return bigDecimal.setScale (num, BigDecimal.ROUND_HALF_UP).doubleValue ();
        }
    }

}
