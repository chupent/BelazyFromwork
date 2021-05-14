package com.belazy.library.util.compute;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTranCnDateUtils {

    public DateTranCnDateUtils() {

    }
    /**
     * 字符串日期转换成中文格式日期
     * @param date  字符串日期 yyyy-MM-dd
     * @return  yyyy年MM月dd日
     * @throws Exception
     */
    public static  String dateToCnDate(String date) {
        String result = "";
        String[]  cnDate = new String[]{"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        String ten = "拾";
        String[] dateStr = date.split("-");
        for (int i=0; i<dateStr.length; i++) {
            for (int j=0; j<dateStr[i].length(); j++) {

                String charStr = dateStr[i];
                String str = String.valueOf(charStr.charAt(j));
                if (charStr.length() == 2) {
                    if((i==1)&&(charStr.equals("01")))
                    {
                        result += cnDate[0]+cnDate[1];
                        break;
                    }
                    if((i==1)&&(charStr.equals("02")))
                    {
                        result += cnDate[0]+cnDate[2];
                        break;
                    }
                    if((i==1)&&(charStr.equals("10")))
                    {
                        result += cnDate[0]+cnDate[1]+ten;
                        break;
                    }
                    if((i==2)&&(charStr.equals("01")))
                    {
                        result += cnDate[0]+cnDate[1];
                        break;
                    }
                    if((i==2)&&(charStr.equals("02")))
                    {
                        result += cnDate[0]+cnDate[2];
                        break;
                    }
                    if((i==2)&&(charStr.equals("03")))
                    {
                        result += cnDate[0]+cnDate[3];
                        break;
                    }
                    if((i==2)&&(charStr.equals("04")))
                    {
                        result += cnDate[0]+cnDate[4];
                        break;
                    }
                    if((i==2)&&(charStr.equals("05")))
                    {
                        result += cnDate[0]+cnDate[5];
                        break;
                    }
                    if((i==2)&&(charStr.equals("06")))
                    {
                        result += cnDate[0]+cnDate[6];
                        break;
                    }
                    if((i==2)&&(charStr.equals("07")))
                    {
                        result += cnDate[0]+cnDate[7];
                        break;
                    }
                    if((i==2)&&(charStr.equals("08")))
                    {
                        result += cnDate[0]+cnDate[8];
                        break;
                    }
                    if((i==2)&&(charStr.equals("09")))
                    {
                        result += cnDate[0]+cnDate[9];
                        break;
                    }
                    if((i==2)&&(charStr.equals("10")))
                    {
                        result += cnDate[0]+cnDate[1]+ten;
                        break;
                    }
                    if((i==2)&&(charStr.equals("20")))
                    {
                        result += cnDate[0]+cnDate[2]+ten;
                        break;
                    }
                    if((i==2)&&(charStr.equals("30")))
                    {
                        result += cnDate[0]+cnDate[3]+ten;
                        break;
                    }
                    if((i==2)&&(charStr.equals("11")))
                    {
                        result += cnDate[1]+ten+cnDate[1];
                        break;
                    }
                    if((i==2)&&(charStr.equals("12")))
                    {
                        result += cnDate[1]+ten+cnDate[2];
                        break;
                    }
                    if((i==2)&&(charStr.equals("13")))
                    {
                        result += cnDate[1]+ten+cnDate[3];
                        break;
                    }
                    if((i==2)&&(charStr.equals("14")))
                    {
                        result += cnDate[1]+ten+cnDate[4];
                        break;
                    }
                    if((i==2)&&(charStr.equals("15")))
                    {
                        result += cnDate[1]+ten+cnDate[5];
                        break;
                    }
                    if((i==2)&&(charStr.equals("16")))
                    {
                        result += cnDate[1]+ten+cnDate[6];
                        break;
                    }
                    if((i==2)&&(charStr.equals("17")))
                    {
                        result += cnDate[1]+ten+cnDate[7];
                        break;
                    }
                    if((i==2)&&(charStr.equals("18")))
                    {
                        result += cnDate[1]+ten+cnDate[8];
                        break;
                    }
                    if((i==2)&&(charStr.equals("19")))
                    {
                        result += cnDate[1]+ten+cnDate[9];
                        break;
                    }
                    if (charStr.equals("10")) {
                        result += ten;
                        break;
                    } else {
                        if (j == 0) {
                            if (charStr.charAt(j) == '1')
                                result += ten;
                            else if (charStr.charAt(j) == '0')
                                result += "";
                            else
                                result += cnDate[Integer.parseInt(str)] + ten;
                        }
                        if (j == 1) {
                            if (charStr.charAt(j) == '0')
                                result += "";
                            else
                                result += cnDate[Integer.parseInt(str)];
                        }
                    }
                } else {
                    result += cnDate[Integer.parseInt(str)];
                }
            }
            if (i == 0) {
                result += "年";
                continue;
            }
            if (i == 1) {
                result += "月";
                continue;
            }
            if (i == 2) {
                result += "日";
                continue;
            }
        }
        return result;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd");
        String date = dateToCnDate(sdf1.format(sdf.parse("20150702")));
        System.out.println(date);
    }
}
