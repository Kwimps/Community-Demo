package com.kwimps.community;

import com.kwimps.community.dao.DiscussPostMapper;
import com.kwimps.community.dao.UserMapper;
import com.kwimps.community.entity.DiscussPost;
import com.kwimps.community.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@SpringBootTest
class CommunityApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DiscussPostMapper discussPostMapper;

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
    public void logTest(){

    }

}
