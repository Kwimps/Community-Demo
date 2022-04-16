package com.kwimps.community.controller;

import com.kwimps.community.entity.DiscussPost;
import com.kwimps.community.entity.Page;
import com.kwimps.community.service.ElasticSearchService;
import com.kwimps.community.service.LikeService;
import com.kwimps.community.service.UserService;
import com.kwimps.community.util.CommunityConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController implements CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private ElasticSearchService elasticsearchService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    // search?keyword=xxx
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String search(String keyword, Page page, Model model) {

        List<DiscussPost> searchResult = null;
        // 搜索帖子
        try{
            searchResult =
                    elasticsearchService.searchDiscussPost(keyword, page.getOffset(), page.getLimit());
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException("搜索失败，服务器发生异常！",e);
        }

        // 聚合数据
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (searchResult != null) {
            for (DiscussPost post : searchResult) {
                Map<String, Object> map = new HashMap<>();
                // 帖子
                map.put("post", post);
                // 作者
                map.put("user", userService.findUserById(post.getUserId()));
                // 点赞数量
                map.put("likeCount", likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId()));

                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("keyword", keyword);

        // 分页信息
        page.setPath("/search?keyword=" + keyword);
        int count = 0;
        try{
            count = elasticsearchService.getDiscussPostCount(keyword);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException("查询数量失败，服务器异常！",e);
        }
        page.setRows(searchResult == null ? 0 : count);

        return "site/search";
    }

}