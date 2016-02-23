package com.horasphere.springstarter.security.infrastructure.repository.mybatis;


import com.horasphere.springstarter.security.domain.User;
import com.horasphere.springstarter.security.domain.UserRepository;
import com.horasphere.springstarter.security.infrastructure.repository.mybatis.dbo.DBUser;
import com.horasphere.springstarter.security.infrastructure.repository.mybatis.mapper.UserMapper;
import com.horasphere.springstarter.shared.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyBatisUserRepository implements UserRepository
{
    @Autowired
    UserMapper userMapper;

    public int create(User user) {
        DBUser dbUser = new UserConverter().convert(user);

        userMapper.insert(dbUser);

        return dbUser.getId();
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
            return new DBUser(user.getId(),
                user.getEmail(),
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
            return new User(dbUser.getId(),
                dbUser.getEmail(),
                dbUser.getPassword(),
                dbUser.getFirst_name(),
                dbUser.getLast_name(),
                dbUser.getRoles(),
                dbUser.isEnabled());
        }
    }
}
