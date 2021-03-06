package com.horasphere.springstarter.security.infrastructure.repository.mybatis.mapper;

import com.horasphere.springstarter.security.infrastructure.repository.mybatis.dbo.DBUser;
import com.horasphere.springstarter.security.infrastructure.repository.mybatis.dbo.DBUserRole;
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
            insertRole(new DBUserRole(dbUser.getId(), role));
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

    public DBUser selectById(String id) {
        return this.sqlSessionTemplate.selectOne("selectById", id);
    }
}
