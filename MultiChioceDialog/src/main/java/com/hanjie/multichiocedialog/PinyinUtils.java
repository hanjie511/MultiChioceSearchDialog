package com.hanjie.multichiocedialog;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PinyinUtils {
    /*
    该方法将字符串的首个汉字字符转化为拼音
     */
    public static String toHanyuPinyin(String str) {
        String pinyin = "";
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        char c = str.trim().charAt(0);
        if (c >= 0x4E00 && c <= 0x9FA5) {//判断是否属于汉字字符
            try {
                pinyin = pinyin + PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat)[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pinyin = pinyin + c;
        }
        return pinyin;
    }
    /*
       将传过来的字符串列表按A-Z的顺序排序后返回
        */
    public  ArrayList<SortModel> getSortedListByAlpha(ArrayList<SortModel> list) {
        long start=System.currentTimeMillis();
        ArrayList<SortModel> total_list = new ArrayList<SortModel>();
        String py_str = "";
        SortModel sortModel=null;
        Iterator<SortModel> iterator = list.iterator();
        Set<Character> set=new HashSet();
        while (iterator.hasNext()) {
            sortModel = iterator.next();
            py_str=PinyinUtils.toHanyuPinyin(sortModel.getName());
            char alpha = py_str.charAt(0);
            if(alpha>=65&&alpha<=90){
                set.add(alpha);
                sortModel.setSortStr(py_str);
                sortModel.setTag(false);
            }else{
                set.add('#');
                sortModel.setSortStr("#");
                sortModel.setTag(false);
            }
            total_list.add(sortModel);
        }
        Iterator it=set.iterator();
        while(it.hasNext()){
            String c=it.next().toString();
            sortModel=new SortModel();
            sortModel.setName(c);
            sortModel.setTag(true);
            sortModel.setSortStr(c);
            total_list.add(sortModel);
        }
        Collections.sort(total_list,new PinYinComparator());
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        return total_list;
    }
    private class PinYinComparator implements Comparator<SortModel>{
        @Override
        public int compare(SortModel o1, SortModel o2) {
            if(o1.getSortStr().equals("#")&&!o2.getSortStr().equals("#")){
                return -1;
            }else if(!o1.getSortStr().equals("#")&&o2.equals("#")){
                return 1;
            }else{
                return o1.getSortStr().compareTo(o2.getSortStr());
            }
        }
    }
}
