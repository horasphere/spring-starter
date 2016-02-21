package com.horasphere.springstarter.infrastructure.repository.mybatis.mapper;

import com.horasphere.springstarter.domain.User;
import com.horasphere.springstarter.infrastructure.repository.mybatis.dbo.DBUser;
import com.horasphere.springstarter.infrastructure.repository.mybatis.dbo.DBUserRole;
import com.horasphere.springstarter.utils.Converter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserMapper
{
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public void insert(DBUser dbUser) {
        this.sqlSessionTemplate.insert("insertUser", dbUser);

        for(String role: dbUser.getRoles())
        {
            insertRole(new DBUserRole(dbUser.getEmail(), role));
        }
    }

    private void insertRole(DBUserRole dbUserRole) {
        this.sqlSessionTemplate.insert("insertRole", dbUserRole);
    }

    public List<DBUser> selectAll() {
        return this.sqlSessionTemplate.selectList("selectAll");
    }

    public DBUser selectByEmail(String email) {
        return this.sqlSessionTemplate.selectOne("selectByEmail", email);
    }
}
