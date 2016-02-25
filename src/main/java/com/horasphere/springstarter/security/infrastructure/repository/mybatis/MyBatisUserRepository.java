package com.horasphere.springstarter.security.infrastructure.repository.mybatis;


import com.horasphere.shared.ddd.SequenceGenerator;
import com.horasphere.springstarter.security.domain.User;
import com.horasphere.springstarter.security.domain.UserRepository;
import com.horasphere.springstarter.security.infrastructure.repository.mybatis.dbo.DBUser;
import com.horasphere.springstarter.security.infrastructure.repository.mybatis.mapper.UserMapper;
import com.horasphere.shared.utils.Converter;

import java.util.List;

public class MyBatisUserRepository implements UserRepository
{
    UserMapper userMapper;
    SequenceGenerator sequenceGenerator;

    public MyBatisUserRepository(
            UserMapper userMapper, SequenceGenerator sequenceGenerator)
    {
        this.userMapper = userMapper;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public String nextId()
    {
        return sequenceGenerator.generateNextValue();
    }

    public User findById(String id)
    {
        return null;
    }

    public void create(User user) {
        DBUser dbUser = new UserConverter().convert(user);

        userMapper.insert(dbUser);
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
            return new DBUser(user.id(),
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
