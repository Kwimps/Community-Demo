package com.kwimps.community.controller;

import com.kwimps.community.controller.annotation.LoginRequired;
import com.kwimps.community.entity.User;
import com.kwimps.community.service.LikeService;
import com.kwimps.community.util.CommunityUtil;
import com.kwimps.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LikeController {

    @Resource
    private LikeService likeService;

    @Resource
    private HostHolder hostHolder;


    @RequestMapping(path = "/like", method = RequestMethod.POST)
    @ResponseBody
    public String like(int entityType, int entityId) {
        User user = hostHolder.getUser();
        if (user==null) return CommunityUtil.getJSONString(1,"请先登录");
        // 点赞
        likeService.like(user.getId(), entityType, entityId);

        // 数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        // 状态
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);
        // 返回的结果
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);

        return CommunityUtil.getJSONString(0, null, map);
    }

}
