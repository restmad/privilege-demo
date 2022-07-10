package com.example.privilegedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.privilegedemo.aop.PrivAOP;
import com.example.privilegedemo.data.User;
import com.example.privilegedemo.mapper.UserMapper;
import com.example.privilegedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findUser(String userId) {
        log.info("user id: {}", userId);
        List<String> userOUList = PrivAOP.getUserOUList(userId);
        log.info("user org ou list: {}", userOUList);
        if (userOUList == null) {
            throw new RuntimeException("user " + userId + " dont have any privilege to visit data");
        }
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getId, 1L);
        queryWrapper.in(!CollectionUtils.isEmpty(userOUList), User::getOrgId, userOUList);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public List<User> findUserList(String userId) {
        log.info("user id: {}", userId);
        List<String> userOUList = PrivAOP.getUserOUList(userId);
        log.info("user org ou list: {}", userOUList);
        if (userOUList == null) {
            throw new RuntimeException("user " + userId + " dont have any privilege to visit data");
        }
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(!CollectionUtils.isEmpty(userOUList), User::getOrgId, userOUList);
        return userMapper.selectList(queryWrapper);
    }
}
