package com.belazy.library.util.compute;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @author tangcp
 * 校验数据格式是否正确
 */
public class ValidateUtils {

    private static final String EMAIL = "^[a-z0-9]([a-z0-9]*[-_\\.\\+]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\\.][a-z]{2,3}([\\.][a-z]{2,4})?$";
    private static final String CARD_ID = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(\\d|X|x)$";
    private static final String CHINA_TEXT = "[\\u4e00-\\u9fa5]+";
    private static final String PHONE_CHINA_TELECOM = "^1[3578][01379]\\d{8}$";
    private static final String PHONE_CHINA_UNION = "^1[34578][01256]\\d{8}$";
    private static final String PHONE_CHINA_MOBILE = "^(134[012345678]\\d{7}|1[34578][012356789]\\d{8})$";
    private static final Pattern PATTERN = Pattern.compile ("^[0-9]*$");
    private static final Pattern PATTERN_SENCOND = Pattern.compile ("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$");

    /**
     * validate Email
     *
     * @param email
     * @return
     */
    public static boolean validateEmail(String email) {
        if (StringUtils.isEmpty (email)) {
            return false;
        }
        return Pattern.matches (EMAIL, email);
    }

    /**
     * @param phone
     * @return
     */
    public static boolean validatePhone(String phone) {
        if (StringUtils.isEmpty (phone)) {
            return false;
        }
        return Pattern.matches (PHONE_CHINA_TELECOM, phone) || Pattern.matches (PHONE_CHINA_UNION, phone) || Pattern.matches (PHONE_CHINA_MOBILE, phone);
    }

    /**
     * 检测身份证格式是否正确
     *
     * @param cardId
     * @return
     */
    public static boolean validateCardId(String cardId) {
        if (StringUtils.isEmpty (cardId)) {
            return false;
        }
        return Pattern.matches (CARD_ID, cardId);
    }

    /**
     * 验证字符串是否是中文
     *
     * @param chinaText
     * @return
     */
    public static boolean validateChineseText(String chinaText) {
        if (StringUtils.isEmpty (chinaText)) {
            return false;
        }
        return Pattern.matches (CHINA_TEXT, chinaText);
    }


    /**
     * 正则表达式：验证非零的正整数
     *
     * @param str
     * @return
     */
    public static boolean validateNonZeroPositiveInteger(String str) {
        java.util.regex.Matcher match = PATTERN.matcher (str);
        if (match.matches () == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 正则表达式：验证是否为所要求的金额类型
     * 正浮点数
     *
     * @param str
     * @return
     */
    public static boolean validateCurrency(String str) {
        java.util.regex.Matcher match = PATTERN_SENCOND.matcher (str);
        if (match.matches () == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 密码格式不能是纯字母或纯数字
     *
     * @param str
     * @return
     */
    public static boolean validatePassword(String str) {
        if (str.matches ("[a-zA-Z]+") || str.matches ("[0-9]+")) {
            return false;
        }
        return true;
    }
}
