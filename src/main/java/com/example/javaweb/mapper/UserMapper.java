package com.example.javaweb.mapper;

import com.example.javaweb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface UserMapper {

    //用户登录
    User userlogin(@Param("username") String username,
                   @Param("password") String password);

    //注册新用户(方式1)
    int adduser(@Param("username") String username,
                @Param("password") String password,
                @Param("phone") int phone,
                @Param("key1") int key1);

    int addemp(@Param("pro_num") int pro_num,
               @Param("pro_name") String pro_name,
               @Param("pro_kind") String pro_kind,
               @Param("pro_factory") String pro_factory,
               @Param("pro_price") float pro_price);

    int probuy1(@Param("pro_num") int pro_num,
                @Param("pro_name") String pro_name,
                @Param("pro_amount") int pro_amount,
                @Param("pro_price") float pro_price,
                @Param("pro_money") float pro_money);

    List<Map<String, Object>> queryAllUser();
}