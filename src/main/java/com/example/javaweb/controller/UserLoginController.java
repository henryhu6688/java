package com.example.javaweb.controller;

import com.example.javaweb.entity.User;
import com.example.javaweb.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(tags = "界面管理")
@Controller

@ApiOperation(value = "用户")
@RequestMapping(value = {"/user"})
public class UserLoginController {

    /**
     * 最开始希望用Map的形式接参数，后来不用了，将请求对应的接受方式记录一下
     *
     * @RequestBody Map<String, Object> map      post请求
     * @RequestParam Map<String, Object> map     get请求
     */

    /**
     * 注入service
     */
    @Autowired
    private UserLoginService userLoginService;


    /**
     * 获取用户名与密码，用户登录
     *
     * @return 登录成功页面
     */
    @ApiOperation(value = "登录注册界面")
    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @ApiOperation(value = "管理员注册界面")
    @RequestMapping(value = {"/admregister"}, method = RequestMethod.GET)
    public String admregister() {
        return "admregister";
    }



    @ApiOperation(value = "用户登录")
    @RequestMapping(value = {"/userLogin"}, method = RequestMethod.POST)
    public String userLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletRequest request) {

        if (StringUtils.isEmpty(username)) {
            return "用户名不能为空";
        }

        if (StringUtils.isEmpty(password)) {
            return "密码不能为空";
        }

        User user = userLoginService.userLogin(username, password);
        int user1 = userLoginService.keyfind(username);
        if (user != null) {                                                  //登录成功
            request.getSession().setAttribute("session_user", user);     //将用户信息放入session  用于后续的拦截器
            if (user1 == 1) {
                return "redirect:/user/mainindex";
            } else {
                return "redirect:/user/emplook";
            }

        }
        return "loginError";
    }

    /**
     * 注册新用户
     *
     * @return 注册结果
     */
    @ResponseBody
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = {"/uregister"}, method = RequestMethod.POST)
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("password2") String password2,
                          @RequestParam("phone") int phone) {

        int key1 = 2;
        if (StringUtils.isEmpty(username)) {
            return "用户名不能为空";
        }

        if (StringUtils.isEmpty(password)) {
            return "密码不能为空";
        }

        if (StringUtils.isEmpty(password2)) {
            return "确认密码不能为空";
        }

        if (!password.equals(password2)) {
            return "两次密码不相同，注册失败！！" + "<a href='/user/index'>返回登录界面</a>";
        } else {
            int res = userLoginService.adduser(username, password, phone, key1);
            if (res == 0) {
                return "注册失败" + "<a href='/user/index'>返回登录界面</a>";
            } else {
                return "注册成功" + "<a href='/user/index'>返回登录界面</a>";
            }
        }

    }

    @ResponseBody
    @ApiOperation(value = "管理员注册")
    @RequestMapping(value = {"/adm"}, method = RequestMethod.POST)
    public String adm(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam("password2") String password2,
                      @RequestParam("phone") int phone) {
        int key1 = 1;
        if (StringUtils.isEmpty(username)) {
            return "用户名不能为空";
        }

        if (StringUtils.isEmpty(password)) {
            return "密码不能为空";
        }

        if (StringUtils.isEmpty(password2)) {
            return "确认密码不能为空";
        }

        if (!password.equals(password2)) {
            return "两次密码不相同，注册失败！！" + "<a href='/user/index'>返回登录界面</a>";
        } else {
            int res = userLoginService.adduser(username, password, phone, key1);
            if (res == 0) {
                return "注册失败" + "<a href='/user/index'>返回登录界面</a>";
            } else {
                return "注册成功" + "<a href='/user/index'>返回登录界面</a>";
            }
        }

    }

    @ResponseBody
    @ApiOperation(value = "添加信息")
    @RequestMapping(value = {"/main_index1"}, method = RequestMethod.POST)
    public String addemp(@RequestParam("pro_num") int pro_num,
                         @RequestParam("pro_name") String pro_name,
                         @RequestParam("pro_kind") String pro_kind,
                         @RequestParam("pro_factory") String pro_factory,
                         @RequestParam("pro_price") float pro_price) {
        if (StringUtils.isEmpty(pro_num)) {
            return "商品编号不能为空";
        }

        if (StringUtils.isEmpty(pro_name)) {
            return "商品名称不能为空";
        }

        if (StringUtils.isEmpty(pro_kind)) {
            return "商品类别不能为空";
        }
        if (StringUtils.isEmpty(pro_factory)) {
            return "生产商不能为空";
        }
        if (StringUtils.isEmpty(pro_price)) {
            return "商品价格不能为空";
        } else {
            int re = userLoginService.addemp(pro_num,pro_name,pro_kind,pro_factory,pro_price);
            if (re == 0) {
                return "录入失败！" + "<a href='/user/mainindex'>返回主页</a>";
            } else {
                return "录入成功！" + "<a href='/user/mainindex'>返回主页</a>";
            }
        }
    }

    @ApiOperation(value = "自动更新显示（管理员界面）")
    @RequestMapping(value = "/mainindex", method = RequestMethod.GET)/* 网页的localhost:8080/home跳转到这*/
    public String home(Model model) {

        model.addAttribute("users", userLoginService.findAll());

        return "mainindex";

    }
    @ApiOperation(value = "自动更新显示（用户界面）")
    @RequestMapping(value = "/emplook", method = RequestMethod.GET)/* 网页的localhost:8080/home跳转到这*/
    public String home1(Model model) {

        model.addAttribute("users1", userLoginService.findAll());

        return "emplook";

    }

    @ApiOperation(value = "自动更新显示")
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String home2(Model model) {

        model.addAttribute("users", userLoginService.findAll1());

        return "cart";

    }

    @ResponseBody/*这注解表明操作完直接在该页面显示删除成功或失败*/
    @ApiOperation(value = "删除界面")
    @RequestMapping(value = "/delete", method = RequestMethod.GET) /*点删除按钮跳转到这*/
    public String delete(@RequestParam String pro_name) {
        int a = userLoginService.deletebyemp(pro_name);/*调用userDao的deletebyid方法根据id进行删除操作*/
        if (a > 0) {
            return "删除成功" + "<a href='/user/mainindex'>返回主页</a>";
        } else {
            return "删除失败" + "<a href='/user/mainindex'>返回主页</a>";
        }
    }
    @ResponseBody/*这注解表明操作完直接在该页面显示删除成功或失败*/
    @ApiOperation(value = "购物车删除界面")
    @RequestMapping(value = "/delete1", method = RequestMethod.GET) /*点删除按钮跳转到这*/
    public String delete1(@RequestParam float pro_money) {
        int a = userLoginService.deletebyemp1(pro_money);/*调用userDao的deletebyid方法根据id进行删除操作*/
        if (a > 0) {
            return "删除成功" + "<a href='/user/cart'>返回</a>";
        } else {
            return "删除失败" + "<a href='/user/cart'>返回</a>";
        }
    }
    @ApiOperation(value = "更新传递")
    @RequestMapping(value = "/update", method = RequestMethod.GET)/*点修改跳转到这*/
    public String update(@RequestParam String pro_name, Model model) {  /*映射前端的参数*/
        User user = userLoginService.finduserbyemp(pro_name); /*调用userDao的finduserbyemp通过emp找到该对象*/
        model.addAttribute("user", user);/*将得到的对象通过model的方法映射到"user"*/
        return "update";/*将该对象送到网页  跳转看update网页（只是回显  现在还没修改）*/
    }

    @ApiOperation(value = "管理员查询")
    @RequestMapping(value = "/empfind", method = RequestMethod.GET)/*点修改跳转到这*/
    public String emp_find(@RequestParam(value = "pro_name", required = false) String pro_name,
                           Model model) {  /*映射前端的参数*/
        model.addAttribute("users1", userLoginService.empfind(pro_name));
        return "empfind";
    }

    @ApiOperation(value = "职工查询")
    @RequestMapping(value = "/emplook1", method = RequestMethod.GET)
    public String emplook(@RequestParam(value = "proname", required = false) String pro_name,
                         Model model) {  /*映射前端的参数*/
        model.addAttribute("users1", userLoginService.empfind(pro_name));
        return "emplook";
    }

    @ResponseBody
    @ApiOperation(value = "购买")
    @RequestMapping(value = "/probuy", method = RequestMethod.POST)
    public String probuy(@RequestParam("pro_amount") int pro_amount,
                         @RequestParam("pro_name") String pro_name,
                         @RequestParam("pro_price") float pro_price) {  /*映射前端的参数*/
        float pro_money=pro_amount*pro_price;
        User user=userLoginService.probuy(pro_name);
        int num2=user.getPro_num();
        String name2=user.getPro_name();
        int res=userLoginService.probuy1(num2,name2,pro_amount,pro_price,pro_money);
        if (res == 0) {
            return "添加失败" + "<a href='/user/emplook'>返回</a>";
        } else {
            return "添加成功" + "<a href='/user/emplook'>返回</a>";
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新界面")
    @RequestMapping(value = "/updateAction", method = RequestMethod.POST)  /*通过表单的action="/updateAction" 跳转到这*/
    public String updateAction(User user) {
        int row = userLoginService.update(user); /*这里通过调用userDao的update进行对数据库的内容的修改*/
        if (row > 0) {
            return "修改成功" + "<a href='/user/mainindex'>返回主页</a>";
        } else {
            return "修改失败" + "<a href='/user/mainindex'>返回主页</a>";
        }
    }


    @ResponseBody
    @RequestMapping(value = {"/queryAllUser"}, method = RequestMethod.GET)
    public List<Map<String, Object>> queryAllUser() {

        return userLoginService.queryAllUser();
    }

}
