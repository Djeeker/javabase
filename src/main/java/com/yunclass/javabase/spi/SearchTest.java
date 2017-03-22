package com.yunclass.javabase.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: <a href="http://www.panaihua.com">panaihua</a>
 * @date: 2017年03月22日 17:26
 * @descript:
 * @version: 1.0
 */
public class SearchTest {

    public static void main(String args[]){

        ServiceLoader<Search> search = ServiceLoader.load(Search.class);
        Iterator<Search> searchIterator = search.iterator();
        while (searchIterator.hasNext()){
            Search search1 = searchIterator.next();
            System.out.println(search1.search("sss"));
        }
    }
}
