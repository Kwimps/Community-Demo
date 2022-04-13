package com.kwimps.community;

import com.kwimps.community.service.LikeService;
import com.kwimps.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.thymeleaf.TemplateEngine;


@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private LikeService likeService;

    @Autowired
    private RedisTemplate redisTemplate;
    

    @Test
    void contextLoads() {
    }

    @Test
    public void userTest(){
    }

}



