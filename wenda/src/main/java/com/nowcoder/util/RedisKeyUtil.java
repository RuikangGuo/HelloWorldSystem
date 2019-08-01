package com.nowcoder.util;

public class RedisKeyUtil {
  private static String SPLIT=":";
  private static String BIZ_LIKE="LIKE";
  private static String BIZ_DISLIKE="DISLIKE";
  private static String BIZ_EVENTQUEUE="EVEVT_QUEUE	";
  
  //保证不重复，前缀是业务，参数用分隔符结合起来
  public static String getLikeKey(int entityType,int entityId) {
	  return BIZ_LIKE+SPLIT+String.valueOf(entityType)+SPLIT+String.valueOf(entityId);
  }
  public static String getDisLikeKey(int entityType,int entityId) {
	  return BIZ_DISLIKE+SPLIT+String.valueOf(entityType)+SPLIT+String.valueOf(entityId);
  }
  public static String getEventQueueKey() {
	  return BIZ_EVENTQUEUE;
  }
}
