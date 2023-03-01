package com.imooc.project.controller;


import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Account;
import com.imooc.project.entity.Account;
import com.imooc.project.entity.Role;
import com.imooc.project.query.AccountQuery;
import com.imooc.project.service.AccountService;
import com.imooc.project.service.AccountService;
import com.imooc.project.service.RoleService;
import com.imooc.project.util.ResultUtil;
import org.apache.catalina.valves.RemoteIpValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    /**
     * 进入列表页
     *
     */
    @GetMapping("toList")
    public String toList() {
        return "account/accountList";
    }


    /**
     * 查询方法
     *
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(AccountQuery query) {//page和limit是layui组件传过来的
        QueryWrapper<Account> wrapper = Wrappers.<Account>query();
        wrapper.like(StringUtils.isNotBlank(query.getRealName()), "a.real_name", query.getRealName())
                .like(StringUtils.isNotBlank(query.getEmail()), "a.email", query.getEmail());
        String createTimeRange = query.getCreateTimeRange();
        if (StringUtils.isNotBlank(createTimeRange)) {
            String[] timeArray = createTimeRange.split(" - ");
            //让在时间范围内
            wrapper.ge("a.create_time", timeArray[0]).le("a.create_time", timeArray[1]);
        }
        //手动添加逻辑未删除标识
        wrapper.eq("a.deleted", 0).orderByDesc("a.account_id");
        IPage<Account> myPage = accountService.accountPage(new Page<>(query.getPage(), query.getLimit()), wrapper);
        return ResultUtil.buildPageR(myPage);
    }

    /**
     * 进入新增页
     *
     * @return 跳转页面
     */
    @GetMapping("toAdd")
    public String toAdd(Model model) {
        List<Role> roles = roleService.list(Wrappers.<Role>lambdaQuery().orderByAsc(Role::getRoleId));
        model.addAttribute("roles", roles);
        return "account/accountAdd";
    }

    /**
     * 新增账号
     *
     * @param account
     * @return
     */
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Account account) {//@RequestBody解析处理前端传来的json字符串数据
        setPasswordAndSalt(account);
        return ResultUtil.buildR(accountService.save(account));
    }

    /**
     * 设置加密密码和加密盐
     *
     * @param account
     */
    public void setPasswordAndSalt(Account account) {
        String password = account.getPassword();
        String salt = UUID.fastUUID().toString().replaceAll("-", "");
        MD5 md5 = new MD5(salt.getBytes());
        String s = md5.digestHex(password);
        account.setPassword(s);
        account.setSalt(salt);
    }

    /**
     * 进入修改页
     *
     * @return
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        Account account = accountService.getById(id);
        // 用于回显修改的客户数据
        model.addAttribute("account", account);

        List<Role> roles = roleService.list(Wrappers.<Role>lambdaQuery().orderByAsc(Role::getRoleId));
        model.addAttribute("roles", roles);
        return "account/accountUpdate";
    }

    /**
     * 修改操作
     *
     * @param account
     * @return
     */
    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Account account) {//@RequestBody解析处理前端传来的json字符串数据
        if (StringUtils.isNotBlank(account.getPassword())) {
            setPasswordAndSalt(account);
        } else {
            account.setPassword(null);
        }
        return ResultUtil.buildR(accountService.updateById(account));
    }

    /**
     * 删除客户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable long id, HttpSession session) {//@RequestBody解析处理前端传来的json字符串数据

        Account account = (Account) session.getAttribute("account");
        if (account.getAccountId() == id) {
            return R.failed("不可以删除自己的呀");
        }
        return ResultUtil.buildR(accountService.removeById(id));
    }

    /**
     * 进入详情页
     *
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        Account account = accountService.getAccountById(id);
        // 用于回显修改的客户数据
        model.addAttribute("account", account);
        return "account/accountDetail";
    }

    /**
     * 用户名判重
     *
     * @param username
     * @return
     */
    @GetMapping({"/{username}", "/{username}/{accountId}"})
    @ResponseBody
    public R<Object> checkUsername(@PathVariable String username, @PathVariable(required = false) Long accountId) {
        //新增的判重需要与所有的id进行比较，修改的判重不要与自身比较
        Integer count = accountService.lambdaQuery()
                .eq(Account::getUsername, username)
                .ne(accountId != null, Account::getAccountId, accountId)
                .count();
        return R.ok(count);
    }
}
