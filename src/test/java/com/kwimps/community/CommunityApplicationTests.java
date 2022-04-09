package com.kwimps.community;

import com.kwimps.community.dao.DiscussPostMapper;
import com.kwimps.community.dao.LoginTicketMapper;
import com.kwimps.community.dao.UserMapper;
import com.kwimps.community.entity.DiscussPost;
import com.kwimps.community.entity.LoginTicket;
import com.kwimps.community.entity.User;
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

    @Resource
    private LoginTicketMapper loginTicketMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DiscussPostMapper discussPostMapper;

    @Resource
    private MailClient mailClient;

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private SensitiveFilter sensitiveFilter;

    @Test
    void contextLoads() {
    }

    @Test
    public void userTest(){
        User t = new User();
        t.setUsername("test");
        t.setPassword("123");
        t.setSalt("asd");
        t.setEmail("qweqwe@qq.com");
        t.setHeaderUrl("asdasd");
        t.setCreateTime(new Date());
        int rows = userMapper.insertUser(t);
        System.out.println(rows);
        System.out.println(t.getId());
    }

    @Test
    public void discussTest(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 10);
        for(DiscussPost post : list) {
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

    @Test
    public void mailTest(){
        Context context = new Context();
        context.setVariable("username","晴路卡");
        String tmp = templateEngine.process("/mail/demo",context);
        System.out.println(tmp);
        mailClient.sendMail("2683511546@qq.com","你好",tmp);
    }

    @Test
    public void testInsertLoginTicket(){
        loginTicketMapper.updateStatus("asd",1);
    }

    @Test
    public void logTest(){

    }

    @Test
    public void filterTest(){
        System.out.println(sensitiveFilter.filter("我要去赌博，我要去嫖娼"));

    }

}
