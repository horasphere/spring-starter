package com.horasphere.springstarter.security.infrastructure.reporistory.mybatis;

import com.horasphere.springstarter.security.domain.UserRepository;
import com.horasphere.springstarter.web.config.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MyBatisUserRepositoryTest
{
    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldHaveOneUser() {
        Assert.assertEquals(1, userRepository.findAll().size());
    }
}
