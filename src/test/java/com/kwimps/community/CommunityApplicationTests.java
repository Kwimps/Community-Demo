package com.kwimps.community;

import com.alibaba.fastjson.JSONObject;
import com.kwimps.community.dao.DiscussPostMapper;
import com.kwimps.community.dao.elasticSearch.DiscussPostRepository;
import com.kwimps.community.entity.DiscussPost;
import com.kwimps.community.service.ElasticSearchService;
import com.kwimps.community.service.LikeService;
import com.kwimps.community.service.UserService;
import com.kwimps.community.util.MailClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


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

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private DiscussPostRepository discussPostRepository;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void highlightQuery() throws Exception{

    }


    @Test
    public void testBitMap() {
        String redisKey = "test:bm:01";

        // 记录
        redisTemplate.opsForValue().setBit(redisKey, 1, true);
        redisTemplate.opsForValue().setBit(redisKey, 4, true);
        redisTemplate.opsForValue().setBit(redisKey, 7, true);

        // 查询
        System.out.println(redisTemplate.opsForValue().getBit(redisKey, 0));
        System.out.println(redisTemplate.opsForValue().getBit(redisKey, 1));
        System.out.println(redisTemplate.opsForValue().getBit(redisKey, 2));

        // 统计
        Object obj = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.bitCount(redisKey.getBytes());
            }
        });

        System.out.println(obj);
    }

}



