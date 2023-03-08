package com.longyao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyao.mapper.UserMapper;
import com.longyao.pojo.User;
import com.longyao.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
