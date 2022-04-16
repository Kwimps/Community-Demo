package com.kwimps.community.service;

import com.kwimps.community.entity.DiscussPost;


import java.io.IOException;
import java.util.List;

public interface ElasticSearchService {

    public void saveDiscussPost(DiscussPost post);

    public void deleteDiscussPost(int id);

    public List<DiscussPost> searchDiscussPost(String keyword, int current, int limit) throws IOException;

    public int getDiscussPostCount(String keyword) throws IOException;

}
