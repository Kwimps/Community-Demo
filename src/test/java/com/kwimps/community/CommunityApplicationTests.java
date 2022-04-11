package com.kwimps.community;

import com.kwimps.community.dao.DiscussPostMapper;
import com.kwimps.community.dao.LoginTicketMapper;
import com.kwimps.community.dao.UserMapper;
import com.kwimps.community.entity.DiscussPost;
import com.kwimps.community.entity.LoginTicket;
import com.kwimps.community.entity.User;
import com.kwimps.community.service.impl.testService;
import com.kwimps.community.util.MailClient;
import com.kwimps.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailClient mailClient;

    @Test
    void contextLoads() {
    }

    @Test
    public void userTest(){

    }

    @Test
    public void discussTest(){

    }

    @Test
    public void testHtmlMail() {
    }

    @Test
    public void testInsertLoginTicket(){

    }

    @Test
    public void logTest(){

    }

    @Test
    public void filterTest(){
        System.out.println();

    }

    @Test
    public void tttest(){
        System.out.println("test");
    }

}
