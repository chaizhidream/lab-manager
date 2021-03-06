package org.lab_manager.controller;
/**
 * Created by xiaofeige on 2016/5/22.
 */

import com.alibaba.fastjson.JSON;
import org.lab_manager.entity.Role;
import org.lab_manager.service.ILoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/log")
public class LoginController {
    @Resource
    private ILoginService loginService;
    /**
     *
     * params: username password
    {
    "status": "密码错误",
    "role": "teacher"
    }
     */
    @ResponseBody
    @RequestMapping(value="/in",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username,@RequestParam("password")String pwd) {
//        loginService.login()
        System.out.println("收到用户登录请求");
        Map<String,Object> result=new HashMap<String, Object>();

        Role role=loginService.login(username,pwd);
        if(role==null){
            result.put("status","密码错误");
            result.put("role","xxx");
        }else{
            result.put("status","success");
            result.put("role",role.getRole_name());
        }

//        if(username.equals("001")){
//            result.put("status","success");
//            result.put("role","manager");
//        }else if(username.equals("002")){
//            result.put("status","success");
//            result.put("role","teacher");
//        }else{
//            result.put("status","success");
//            result.put("role","student");
//        }

        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value="/out",method = RequestMethod.POST)
    public String logout() {

        return "hello";
    }
}