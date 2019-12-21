package com.example.javaweb.service;

import com.example.javaweb.entity.User;
import com.example.javaweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserLoginService {

    /**
     * 注入dao
     */
    @Autowired
    private UserMapper usermapper;

    //用户登录
    private JdbcTemplate jdbcTemplate;  /*通过jdbcTemplate的方法对数据库操作*/

    public UserLoginService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() { /*查询语句将表的很多个对象放入List集合*/
        return jdbcTemplate.query("select * from main_table", new Object[]{}, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findAll1() { /*查询语句将表的很多个对象放入List集合*/
        return jdbcTemplate.query("select * from pro_table", new Object[]{}, new BeanPropertyRowMapper<>(User.class));
    }

    public int update(User user) {/*进行数据库的update操作*/
        String sql = "update main_table set pro_num=?,pro_kind=?,pro_factory=?,pro_price=? where pro_name=?";
        return jdbcTemplate.update(sql, user.getPro_num(), user.getPro_kind(), user.getPro_factory(),
                user.getPro_price(), user.getPro_name());
    }


    public int deletebyemp(String pro_name) {/*通过id找到该对象实现删除操作*/
        BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        String sql = "delete from main_table where pro_name=? ";
        int a = jdbcTemplate.update(sql, pro_name);
        return a;
    }
    public int deletebyemp1(float pro_money) {/*通过id找到该对象实现删除操作*/
        BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        String sql = "delete from pro_table where pro_money=? ";
        int b = jdbcTemplate.update(sql, pro_money);
        return b;
    }
    public User finduserbyemp(String pro_name) {/*在进行修改操作之前先通过id回显这个id对应对象的每个属性*/
        BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        String sql = "select * from main_table where pro_name=? ";
        return jdbcTemplate.queryForObject(sql, rowMapper, pro_name);
    }

    public List<User> empfind(String pro_name) {/*进行数据库的update操作*/
        String sql = "select * from main_table where pro_name=? ";
        return jdbcTemplate.query(sql, new Object[]{pro_name}, new BeanPropertyRowMapper<>(User.class));
    }

    public Integer keyfind(String username) {
        String sql = "select key1 from logintable where username=? ";
        return jdbcTemplate.queryForObject(sql, Integer.class, username);
    }

    public User userLogin(String username, String password) {
        return usermapper.userlogin(username, password);
    }

    //注册新用户
    public int adduser(String username, String password, int age, int key1) {

        return usermapper.adduser(username, password, age, key1);
    }

    public int addemp(int pro_num, String pro_name, String pro_kind, String pro_factory, float pro_price) {

        return usermapper.addemp(pro_num,pro_name,pro_kind,pro_factory,pro_price);
    }
    public User probuy(String pro_name) {
        BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        String sql = "select * from main_table where pro_name=? ";
        return jdbcTemplate.queryForObject(sql, rowMapper, pro_name);
    }
    public int probuy1(int pro_num,String pro_name,int pro_amount,float pro_price,float pro_money) {
        return usermapper.probuy1(pro_num,pro_name,pro_amount,pro_price,pro_money);
    }

    //查询用户列表
    public List<Map<String, Object>> queryAllUser() {
        return usermapper.queryAllUser();
    }
}
