package com.imooc.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Account;
import com.imooc.project.entity.Role;
import com.imooc.project.service.AccountService;
import com.imooc.project.service.ResourceService;
import com.imooc.project.service.RoleService;
import com.imooc.project.util.MyQuery;
import com.imooc.project.util.QueryUtil;
import com.imooc.project.util.ResultUtil;
import com.imooc.project.vo.TreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AccountService accountService;

    /**
     * 进入列表页
     *
     * @return
     */
    @GetMapping("/toList")
    public String toList() {
        return "role/roleList";
    }


    /**
     * 查询方法
     *
     * @param roleName
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(@RequestParam Map<String, String> param) {
        //条件构造器
        //分页
        MyQuery<Role> myQuery = QueryUtil.<Role>buildMyQuery(param);
        Page<Role> myPage = roleService.page(myQuery.getPage(), myQuery.getWrapper().orderByDesc("role_id"));
        return ResultUtil.buildPageR(myPage);
    }

    /**
     * 进入新增页
     *
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd() {
        return "role/roleAdd";
    }

    /**
     * 显示树形资源列表
     */
    @GetMapping({"listResource", "listResource/{roleId}", "listResource/{roleId}/{flag}"})
    @ResponseBody
    public R<List<TreeVO>> listResource(@PathVariable(required = false) Long roleId, @PathVariable(required = false) Integer flag) {
        return R.ok(resourceService.listResource(roleId, flag));
    }

    /**
     * 增加操作
     *
     * @param role
     * @return
     */
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Role role) {
        return ResultUtil.buildR(roleService.savaRole(role));
    }


    /**
     * 进入修改页
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "role/roleUpdate";
    }

    /**
     * 修改操作
     *
     * @param role
     * @return
     */
    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Role role) {
        return ResultUtil.buildR(roleService.updateRole(role));
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id) {
        Integer count = accountService.lambdaQuery().eq(Account::getRoleId, id).count();
        if (count > 0) {
            return R.failed("有账号正拥有改角色");
        }
        return ResultUtil.buildR(roleService.removeById(id));
    }

    /**
     * 详情
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "role/roleDetail";
    }


}
