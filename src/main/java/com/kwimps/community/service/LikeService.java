package com.kwimps.community.service;

public interface LikeService {

    public void like(int userId, int entityType, int entityId);

    public long findEntityLikeCount(int entityType, int entityId);

    public int findEntityLikeStatus(int userId, int entityType, int entityId);

}
