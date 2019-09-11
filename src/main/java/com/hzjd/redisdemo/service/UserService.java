package com.hzjd.redisdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hzjd.redisdemo.domain.User;
import com.hzjd.redisdemo.mapper.UserMapper;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @CacheEvict(allEntries = true)
    public void update(User user) {
        userMapper.update(user);
    }

    @CacheEvict(allEntries = true)
    public void addUser(User user) {
        userMapper.insertByUser(user);
    }

    @Cacheable(key = "#p0")
    public User findUserById(Integer id) {
        User user = userMapper.findUserById(id);
        return user;
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Cacheable(keyGenerator = "myKeyGenerator")
    public List<User> getUserList() {
        List<User> userList = userMapper.findAll();
        return userList;
    }
}
