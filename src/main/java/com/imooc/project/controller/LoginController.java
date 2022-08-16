/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/4 11:05
 * @version
 */
package com.imooc.project.controller;

import com.imooc.project.dto.LoginDTO;
import com.imooc.project.service.AccountService;
import com.imooc.project.service.ResourceService;
import com.imooc.project.vo.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MI
 * @ClassName: LoginController
 * @Description: 登录前端控制器
 * @date 2022/8/4 11:05
 */

@Controller
@RequestMapping("auth")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ResourceService resourceService;

    @PostMapping("login")
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes, Model model) {
        LoginDTO loginDTO = accountService.login(username, password);
        String error = loginDTO.getError();
        if (error == null) {
            // 没有错误信息，登录成功
            session.setAttribute("account", loginDTO.getAccount());
            List<ResourceVO> resourceVOS = resourceService.listResourceByRoleId(loginDTO.getAccount().getRoleId());
            model.addAttribute("resource", resourceVOS);
        } else {
            attributes.addFlashAttribute("error", error);
        }
        return loginDTO.getPath();
    }

    /**
     * 登出的方法
     *
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        //使session无效
        session.invalidate();
        return "redirect:/";
    }
}
