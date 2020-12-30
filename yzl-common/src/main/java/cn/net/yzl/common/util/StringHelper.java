package cn.net.yzl.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangruisong on 2019/3/14.
 */
public class StringHelper {

    public static final String EmptyStr = "";

    public static boolean checkStr(String str) {
        if (str == null || str.equals("") || str.length() == 0) {
            return false;
        }
        return true;
    }


    public static boolean isEmpty(Object obj) {
        boolean b = true;
        if (obj == null) {
            return b;
        }
        if (obj instanceof String) {
            b = "".equals(obj) || ((String) obj).length() == 0;
        } else if (obj instanceof Map) {
            b = ((Map) obj).size() == 0;
        } else if (obj instanceof List) {
            b = ((List) obj).size() == 0;
        }
        if (obj.getClass().isArray()) {
            b = Array.getLength(obj) == 0;
        }
        return b;
    }

    /**
     * 获取字符串拼音的第一个字母
     *
     * @param chinese
     * @return
     */
    public static String ToFirstChar(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
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
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    /**
     * 汉字转为拼音
     *
     * @param chinese
     * @return
     */
    public static String ToPinyin(String chinese) {

        if (chinese.contains("厦")) { //解决多音字问题
            chinese = chinese.replace("厦", "夏");
        }

        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }

        pinyinStr = pinyinStr.replace("lu:", "lv");//汉字旅的拼音转换


        return pinyinStr;
    }

    /**
     * for all jdk version
     *
     * @param
     * @return
     */
    public static String listToString(List list, char separator) {
        if(list==null || list.size()==0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 获取两个list中相同的元素
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<Integer> getXiangTongItem(List<Integer> list1, List<Integer> list2) {
        List<Integer> list = new ArrayList<>();
        if (list1 != null && list2 != null) {
            for (int userId1 : list1) {
                for (int userId2 : list2) {
                    if (userId1 == userId2) {
                        list.add(userId2);
                        break;
                    }
                }
            }
        }
        return list;
    }

    /**
     * 从list1中移除和list2中相同的元素
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<Integer> list1RemoveList2(List<Integer> list1, List<Integer> list2) {
        if (list1 == null || list1.size() == 0) return list1;
        if (list2 == null || list2.size() == 0) return list1;

        List<Integer> removeList=new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {

                if (list1.get(i).equals(list2.get(j)))
                    removeList.add(list1.get(i));
            }
        }

        if(removeList.size()>0){
            for (int i=0;i<removeList.size();i++ ) {
                list1.remove(removeList.get((i)));
            }
        }

        return list1;
    }
}
