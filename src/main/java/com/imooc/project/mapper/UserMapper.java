package com.imooc.project.mapper;

import com.imooc.project.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author MI
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-08-26 09:21:57
* @Entity com.imooc.project.entity.User
*/
@Mapper
public interface UserMapper extends MyMapper<User> {


}




