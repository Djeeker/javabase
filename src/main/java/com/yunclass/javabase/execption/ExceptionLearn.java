package com.yunclass.javabase.execption;

import com.alibaba.fastjson.JSON;

/**
 * @author: panaihua
 * @date: 2017年02月17日 14:09
 * @descript:
 * @version: 1.0
 */
public class ExceptionLearn {

    public static void main(String args[]){

        String json = "{'id':1,'name':'sss','age':12}";
        Person person = JSON.parseObject(json,Person.class);
        System.out.println(person.getName());

    }

}

class Person {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
