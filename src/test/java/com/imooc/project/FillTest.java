/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/25 16:10
 * @version
 */
package com.imooc.project;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.project.entity.Customer;
import com.imooc.project.entity.User;
import com.imooc.project.enums.SexEnum;
import com.imooc.project.mapper.CustomerMapper;
import com.imooc.project.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author MI
 * @ClassName: FillTest
 * @Description: TODO
 * @date 2022/8/25 16:10
 */
@SpringBootTest
public class FillTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void test() {
        Customer customer = new Customer();
        customer.setCustomerId(6L);
        customer.setRealName("快点下班");
        System.out.println(customerMapper.updateById(customer));
    }


    @Test
    void selectByRealName() {
        System.out.println(customerMapper.selectByRealName("快点下班"));
    }

    @Test
    void testEnum() {
        User user = new User();
        user.setSex(SexEnum.MALE);
        System.out.println(userMapper.insert(user));
    }

    @Test
    void test3() {
        System.out.println(SexEnum.FEMALE);
    }

    @Test
    void testSelect() {
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        //选择要查询的字段
        wrapper.select(Customer::getCustomerId, Customer::getRealName);
        System.out.println(customerMapper.selectList(wrapper));
    }

    @Test
    void testSelect2() {
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        //排除字段
        wrapper.select(Customer.class, info -> !info.getColumn().equals("real_name") && !info.getColumn().equals("sex"));
        System.out.println(customerMapper.selectList(wrapper));
    }

    @Test
    void testCondition(String email) {
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(email), Customer::getEmail, email);
    }

    @Test
    void testEntityCondition() {
        User user = new User();
        user.setName("xixi");
        user.setSex(SexEnum.MALE);
        LambdaQueryWrapper<User> wra = new LambdaQueryWrapper<>(user);
        List<User> users = userMapper.selectList(wra);
        users.forEach(System.out::println);
    }

    @Test
    void sqlInjectorTest() {

        User user = new User();
        user.setId(1);
        user.setName("gg");

        System.out.println(userMapper.alwaysUpdateSomeColumnById(user));
    }


}
