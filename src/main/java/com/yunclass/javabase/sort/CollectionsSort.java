package com.yunclass.javabase.sort;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * 项目名称：yunclass-javabase
 *
 * @Description：
 * @Date：2016年08月17日 09:35
 * @Author kongjun
 * @Version 1.0
 */
public class CollectionsSort {

    public static void main(String args[]) {

        List<Map<String, Object>> result = new ArrayList();

        Map<String, Object> seriesCount0 = new HashMap();
        seriesCount0.put("seriesCount", "30%");
        result.add(seriesCount0);

        Map<String, Object> seriesCount1 = new HashMap();
        seriesCount1.put("seriesCount", "20%");
        result.add(seriesCount1);

        Map<String, Object> seriesCount2 = new HashMap();
        seriesCount2.put("seriesCount", "40%");
        result.add(seriesCount2);

        Map<String, Object> seriesCount3 = new HashMap();
        seriesCount3.put("seriesCount", "0.00%");
        result.add(seriesCount3);

        //降序
        Collections.sort(result, new Comparator<Map<String, Object>>() {

            NumberFormat numberFormat = NumberFormat.getPercentInstance();

            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {

                Number o1Num = null;
                Number o2Num = null;

                try {
                    o1Num = numberFormat.parse(String.valueOf(o1.get("seriesCount")));
                    o2Num = numberFormat.parse(String.valueOf(o2.get("seriesCount")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return Double.compare(Double.parseDouble(String.valueOf(o2Num)), Double.parseDouble(String.valueOf(o1Num)));
            }
        });

        for (Map<String, Object> seriesCount : result) {
            System.out.print(seriesCount.get("seriesCount"));
        }

    }

}
