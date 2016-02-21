package com.horasphere.springstarter.infrastructure.repository.mybatis;


import com.horasphere.springstarter.domain.User;
import com.horasphere.springstarter.domain.UserRepository;
import com.horasphere.springstarter.infrastructure.repository.mybatis.dbo.DBUser;
import com.horasphere.springstarter.infrastructure.repository.mybatis.mapper.UserMapper;
import com.horasphere.springstarter.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyBatisUserRepository implements UserRepository
{
    @Autowired
    UserMapper userMapper;

    public void create(User user) {
        userMapper.insert(new UserConverter().convert(user));
    }

    public List<User> findAll()
    {
        return new DBConverter().convert(userMapper.selectAll());
    }

    public User findByEmail(String email)
    {
        return new DBConverter().convert(userMapper.selectByEmail(email));
    }

    private class UserConverter extends Converter<User, DBUser>
    {
        @Override
        public DBUser convert(User user)
        {
            return new DBUser(user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles(),
                user.isEnabled());
        }
    }

    private class DBConverter extends Converter<DBUser, User> {
        @Override
        public User convert(DBUser dbUser)
        {
            return new User(dbUser.getEmail(),
                dbUser.getPassword(),
                dbUser.getFirst_name(),
                dbUser.getLast_name(),
                dbUser.getRoles(),
                dbUser.isEnabled());
        }
    }
}
