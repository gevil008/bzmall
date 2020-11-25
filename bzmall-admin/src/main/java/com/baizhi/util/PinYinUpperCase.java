package com.baizhi.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUpperCase {
    public static String getPinYinUpperCase(String pinyin){
        /**
         * 去掉字符串中的非字母字符内容，将首个英文或文字拼音转换为大写
         *
         */
        char[] charArray = pinyin.toCharArray();
        StringBuilder pinyin2 = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        // 设置声调格式：
        // defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        // 如果有英文则取第一个直接返回
        for (int i=0;i<charArray.length;i++){
            if (charArray[i]>=65 && charArray[i] <=90 || charArray[i]>=97 && charArray[i] <=122){
                String DX = charArray[i]+"";
                return DX.toUpperCase();
            }
        }
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = new String[0];
                try {
                    hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
                if (hanyuPinyinStringArray != null) {
                    pinyin2.append(hanyuPinyinStringArray[0].charAt(0));
                }
            }
        }
        return pinyin2.toString().charAt(0)+"";


        /**
         * 只是将首个字符转换为返回，是字母或文字则将其转换为大写返回，
         * 不是字母或文字也将第一个返回
         */
        /*String pinyinStr = "";
        char[] newChar = pinyin.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr.toUpperCase().charAt(0)+"";*/
    }
}
