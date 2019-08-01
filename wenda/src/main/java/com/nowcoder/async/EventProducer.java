package com.nowcoder.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;

@Service
public class EventProducer {
	private static final Logger logger=LoggerFactory.getLogger(EventProducer.class);
	@Autowired
	JedisAdapter jedisAdapter;
	
	public boolean fireEvent(EventModel eventModel) {
		try {
			String json=JSONObject.toJSONString(eventModel);
			String key=RedisKeyUtil.getEventQueueKey();
			jedisAdapter.lpush(key,json);
			return true;
		}catch(Exception e) {
			logger.error("消息发送失败"+e.getMessage());
			return false;
		}
	}
}
