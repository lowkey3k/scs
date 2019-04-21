package com.shanxiut.scs;

import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.common.util.UpdateTool;
import com.shanxiut.scs.entity.Student;
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
        Student student=new Student();
        student.setAge(18);
        student.setBirthday("1994-12-31");
        student.setEmail("984695425@qq.com");
        User user=new User();
        user.setNumber("1418064017");
        user.setId(1L);
        user.setNumber("222");
        student.setUser(user);

        Student student1=new Student();
        student1.setEmail("1111111@qq.com");
        User user1=new User();

        user1.setId(1L);
        student1.setUser(user1);

        UpdateTool.copyNullProperties(student,student1);
        System.out.println(student1);

    }
}
