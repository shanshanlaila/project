package com.imooc.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.project.entity.User;
import com.imooc.project.service.UserService;
import com.imooc.project.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author MI
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-08-26 09:21:57
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




