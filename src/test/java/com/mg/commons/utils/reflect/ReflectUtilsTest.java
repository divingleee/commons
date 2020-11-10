package com.mg.commons.utils.reflect;

import org.junit.Assert;
import org.junit.Test;

public class ReflectUtilsTest {

    @Test
    public void setFieldValue() {
        User user = new User("123", 1, "worker");
        ReflectUtils.setFieldValue(user, "name", "456");
        Assert.assertTrue(user.name.equals("456"));
    }

    @Test
    public void invokeMethodByName() {
        User user = new User("123", 1, "worker");
        ReflectUtils.invokeMethodByName(user, "printName", null);
    }


    public class User {
        private String name;
        private Integer age;
        private String job;

        public User(String name, Integer age, String job) {
            this.name = name;
            this.age = age;
            this.job = job;
        }

        private void printName() {
            System.out.println(name);
        }
    }
}