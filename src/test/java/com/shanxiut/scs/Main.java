package com.shanxiut.scs;

import org.junit.Test;

import java.util.Arrays;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午5:50
 **/
public class Main {
    @Test
    public void test(){

        String str="eq_name";
        String[] strings=str.split("_");
        System.out.println(Arrays.asList(strings).toString());
    }
}
