package com.baizhi;

import com.baizhi.util.PinYinUpperCase;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

public class PinYinTest {

    @Test
    public void test() throws BadHanyuPinyinOutputFormatCombination {
        String n = PinYinUpperCase.getPinYinUpperCase("/v好");
        System.err.print(n);
        System.err.print(n.toLowerCase());
    }

    @Test
    public void testUpperCase(){
        String s = "abCdeFw";
        char[] chars = s.toCharArray();

        for (int i=0;i<s.length();i++){
            // System.err.println(s.charAt(i));
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                System.out.println(s.charAt(i));
            }
        }
        System.err.println("-----------------------");
        for (int i=0;i<chars.length;i++){
            if (chars[i]>=65 && chars[i] <=90){
                System.err.println(chars[i]);
            }
        }
    }
}
