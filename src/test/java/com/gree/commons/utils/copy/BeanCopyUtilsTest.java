package com.gree.commons.utils.copy;

import org.junit.Assert;
import org.junit.Test;

public class BeanCopyUtilsTest {

    @Test
    public void copy() {
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(1);
        userDTO.setJob("manager");
        userDTO.setName("lisi");
        User user = new User();
        BeanCopyUtils.copy(userDTO, user);
        Assert.assertTrue(user.getAge() == 1);
        Assert.assertTrue(user.getJob().equals("manager"));
        Assert.assertTrue(user.getName().equals("lisi"));
    }
}