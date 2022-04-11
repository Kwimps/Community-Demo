package com.kwimps.community.controller;

import com.kwimps.community.entity.Comment;
import com.kwimps.community.entity.DiscussPost;
import com.kwimps.community.entity.Page;
import com.kwimps.community.entity.User;
import com.kwimps.community.service.CommentService;
import com.kwimps.community.service.DiscussPostService;
import com.kwimps.community.service.UserService;
import com.kwimps.community.util.CommunityConstant;
import com.kwimps.community.util.CommunityUtil;
import com.kwimps.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController implements CommunityConstant {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content) {
        User user = hostHolder.getUser();
        if (user == null) {
            return CommunityUtil.getJSONString(403, "你还没有登录哦!");
        }

        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);

        // 报错的情况,将来统一处理.
        return CommunityUtil.getJSONString(0, "发布成功!");
    }

    @RequestMapping(path = "/detail/{discussPostId}", method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model, Page page) {
        // 帖子
        DiscussPost post = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post", post);
        // 作者
        User user = userService.findUserById(post.getUserId());
        model.addAttribute("user", user);

        page.setLimit(5);
        page.setPath("/discuss/detail/"+discussPostId);
        page.setRows(post.getCommentCount());

        List<Comment> commentList = commentService.findCommentsByEntity(
                ENTITY_TYPE_POST,discussPostId,page.getOffset(),page.getLimit());

        List<Map<String,Object>> commentVoList = new ArrayList<>();
        if (commentList!=null){
            for (Comment comment:commentList){
                Map<String,Object> commentVo = new HashMap<>();
                commentVo.put("comment",comment);
                commentVo.put("user",userService.findUserById(comment.getUserId()));

                List<Comment> replyList = commentService.findCommentsByEntity(
                        ENTITY_TYPE_COMMENT,comment.getId(),0,Integer.MAX_VALUE);

                List<Map<String,Object>> replyVolist = new ArrayList<>();
                if (replyList!=null){
                    for (Comment reply:replyList){
                        Map<String,Object> replyVO = new HashMap<>();
                        replyVO.put("reply",reply);
                        replyVO.put("user",userService.findUserById(reply.getUserId()));
                        User target = reply.getTargetId() == 0?null:userService.findUserById(reply.getTargetId());
                        replyVO.put("target",target);
                        replyVolist.add(replyVO);
                    }
                }
                commentVo.put("replys",replyVolist);

                int replyCount = commentService.findCommentCount(ENTITY_TYPE_COMMENT,comment.getId());
                commentVo.put("replyCount",replyCount);
                commentVoList.add(commentVo);
            }
        }
        model.addAttribute("comments",commentVoList);

        return "site/discuss-detail";
    }

}