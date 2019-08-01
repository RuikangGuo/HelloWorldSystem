package com.nowcoder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;

@Service
public class LikeService {
  @Autowired
  JedisAdapter jedisAdapter;
  /**
   * 某个人喜欢了某个东西
   * @param userId
   * @param entityType
   * @param entityId
   * @return
   */
  public long like(int userId,int entityType,int entityId) {
	  
	 String likeKey=RedisKeyUtil.getLikeKey(entityType, entityId);
	 jedisAdapter.sadd(likeKey, String.valueOf(userId));
	 String dislikeKey=RedisKeyUtil.getDisLikeKey(entityType, entityId);
	 jedisAdapter.srem(dislikeKey, String.valueOf(userId));
	 System.out.println(jedisAdapter.scard(likeKey));
	  return jedisAdapter.scard(likeKey);
  }
  /**
   * 不喜欢
   * @param userId
   * @param entityType
   * @param entityId
   * @return
   */
  public long dislike(int userId,int entityType,int entityId) {
	  String dislikeKey=RedisKeyUtil.getDisLikeKey(entityType, entityId);
	  jedisAdapter.sadd(dislikeKey, String.valueOf(userId));
	  String likeKey=RedisKeyUtil.getLikeKey(entityType, entityId);
	  jedisAdapter.srem(likeKey, String.valueOf(userId));
	  return jedisAdapter.scard(likeKey);
  }
  
  /**
   * 点赞加亮，所以要找出状态，点赞是加亮的状态
   * 用户对某一个元素的喜欢还是不喜欢的状态表示
   * @param userId
   * @param entityType
   * @param entityId
   * @return
   */
  public int getLikeStatus(int userId,int entityType,int entityId) {
	  String likeKey=RedisKeyUtil.getLikeKey(entityType, entityId);
	  if(jedisAdapter.sismember(likeKey, String.valueOf(userId))) {
		  return 1;
	  }
	  String dislikeKey=RedisKeyUtil.getDisLikeKey(entityType, entityId);
	 return jedisAdapter.sismember(dislikeKey, String.valueOf(userId))?-1:0;
  }
  /**
   * 看看有多少人喜欢
   * @param entityType
   * @param entityId
   * @return
   */
  public long getLikeCount(int entityType,int entityId) {
	  String likeKey=RedisKeyUtil.getLikeKey(entityType, entityId);
	
	  return jedisAdapter.scard(likeKey);
  }
}

