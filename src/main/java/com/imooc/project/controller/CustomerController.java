package com.imooc.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Customer;
import com.imooc.project.service.CustomerService;
import com.imooc.project.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    /**
     * 进入列表页
     *
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "customer/customerList";
    }


    /**
     * 查询方法
     *
     * @param realName
     * @param phone
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(String realName, String phone, Long page, Long limit) {//page和limit是layui组件传过来的
        LambdaQueryWrapper<Customer> queryWrapper = Wrappers.<Customer>lambdaQuery()
                .like(StringUtils.isNotBlank(realName), Customer::getRealName, realName)
                .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
                .orderByDesc(Customer::getCustomerId);

        Page<Customer> myPage = customerService.page(new Page<>(page, limit), queryWrapper);

        return ResultUtil.buildPageR(myPage);
    }

    /**
     * 进入新增页
     *
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd() {
        return "customer/customerAdd";
    }

    /**
     * 新增客户
     *
     * @param customer
     * @return
     */
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Customer customer) {//@RequestBody解析处理前端传来的json字符串数据
        return ResultUtil.buildR(customerService.save(customer));
    }

    /**
     * 进入修改页
     *
     * @return
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        // 用于回显修改的客户数据
        model.addAttribute("customer", customer);
        return "customer/customerUpdate";
    }

    /**
     * 修改客户
     *
     * @param customer
     * @return
     */
    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Customer customer) {//@RequestBody解析处理前端传来的json字符串数据
        return ResultUtil.buildR(customerService.updateById(customer));
    }

    /**
     * 删除客户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable long id) {//@RequestBody解析处理前端传来的json字符串数据
        return ResultUtil.buildR(customerService.removeById(id));
    }

    /**
     * 进入详情页
     *
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        // 用于回显修改的客户数据
        model.addAttribute("customer", customer);
        return "customer/customerDetail";
    }
}
