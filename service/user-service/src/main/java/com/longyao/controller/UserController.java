package com.longyao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longyao.pojo.User;
import com.longyao.res.Result;
import com.longyao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/labelImg/user")
@CrossOrigin
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @ApiOperation(value = "注册")
    @PostMapping("save")
    public Result save(@RequestBody Map<String,Object> map){
        if (null == map)
            return Result.fail("参数错误");
        String name =(String) map.get("name");
        if (name == null || name.isEmpty()){
            return Result.fail("参数错误，无name");
        }
        String password =(String) map.get("password");
        if (password == null || password.isEmpty()){
            return Result.fail("参数错误，无password");
        }
        Integer id = (Integer) map.get("id");
        User user = new User();
        user.setName(name);
        user.setBagElves("");
        user.setCurrentElve("");
        user.setElves("");
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userService.save(user);
        return Result.ok("保存成功");
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("delete/{id}/{rid}")
    public Result delete(@PathVariable Integer id){
        if (null == id){
            return Result.fail("参数错误");
        }
        final QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",id);
        userService.removeById(id);
        return Result.ok("删除成功");
    }

    @ApiOperation(value = "修改用户名 只有超级管理员和管理员能做")
    @PostMapping("updatename")
    public Result updatename(@RequestBody Map<String,Object> map){
        if (null == map)
            return Result.fail("参数错误");
        Integer rid = (Integer) map.get("rid");
        Integer id = (Integer) map.get("id");
        if (id == null){
            return Result.fail("参数错误");
        }
        User user = userService.getById(id);
        user.setName((String) map.get("name"));
        userService.saveOrUpdate(user);
        return Result.ok("修改成功");
    }


    @ApiOperation(value = "修改自己的密码")
    @PostMapping("updatepassword")
    public Result updatepassword(@RequestBody Map<String,Object> map){
        if (null == map)
            return Result.fail("参数错误");
        Integer id = (Integer) map.get("id");
        if (id == null){
            return Result.fail("参数错误");
        }
        User user = userService.getById(id);
        user.setPassword(bCryptPasswordEncoder.encode((String) map.get("password")));
        userService.saveOrUpdate(user);
        return Result.ok("修改成功");
    }
    @ApiOperation(value = "修改自己的密码")
    @PostMapping("update")
    public Result update(@RequestBody Map<String,Object> map){
        if (null == map)
            return Result.fail("参数错误");
        Integer id = (Integer) map.get("id");
        if (id == null){
            return Result.fail("参数错误");
        }
        User user = userService.getById(id);
        user.setElves((String) map.get("elves"));
        user.setBagElves((String) map.get("bagElves"));
        user.setCurrentElve((String) map.get("currentElve"));
        userService.saveOrUpdate(user);
        return Result.ok("修改成功");
    }

}
