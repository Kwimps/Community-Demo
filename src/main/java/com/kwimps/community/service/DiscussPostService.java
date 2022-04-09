package com.kwimps.community.service;

import com.kwimps.community.entity.DiscussPost;

import java.util.List;

public interface DiscussPostService {

    public List<DiscussPost> findDiscussPosts(int userId,int offset,int limit);

    public int findDiscussPostRows(int userId);

    public int addDiscussPost(DiscussPost post);

    public DiscussPost findDiscussPostById(int id);

}
