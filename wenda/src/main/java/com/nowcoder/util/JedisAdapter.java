package com.nowcoder.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JedisAdapter implements InitializingBean {
	private static final Logger logger=LoggerFactory.getLogger(JedisAdapter.class);
	private JedisPool pool;

	 
	@Override
	public void afterPropertiesSet() throws Exception {
	
		pool=new JedisPool("redis://localhost:6379/5");
	}
	/**
	 * 加
	 * @param key
	 * @param value
	 * @return
	 */
	public long sadd(String key,String value) {
		Jedis jedis=null;
		try {
			jedis=pool.getResource();
			return jedis.sadd(key, value);
		}catch(Exception e) {
			logger.error("发生异常"+e.getMessage());
		
		}finally {
			if(jedis!=null) {
				jedis.close();
			}
		}
		return 0;
	}
	/**
	 * 减，去除
	 * @param key
	 * @param value
	 * @return
	 */
	public long srem(String key,String value) {
		Jedis jedis=null;
		try {
			jedis=pool.getResource();
			return jedis.srem(key,value);
		}catch(Exception e) {
			logger.error("发生异常"+e.getMessage());
		
		}finally {
			if(jedis!=null) {
				jedis.close();
			}
		}
		return 0;
	}
	
	
	public long lpush(String key,String value) {
		Jedis jedis=null;
		try {
			jedis=pool.getResource();
			return jedis.lpush(key,value);
		}catch(Exception e) {
			logger.error("发生异常"+e.getMessage());
		
		}finally {
			if(jedis!=null) {
				jedis.close();
			}
		}
		return 0;
	}
	
	
	public List<String> brpop(int  timeout,String key) {
		Jedis jedis=null;
		try {
			jedis=pool.getResource();
			return jedis.brpop(timeout,key);
		}catch(Exception e) {
			logger.error("发生异常"+e.getMessage());
		
		}finally {
			if(jedis!=null) {
				jedis.close();
			}
		}
		return null;
	}
	/**
	 * 成员个数
	 * @param key
	 * @return
	 */
	public long scard(String key) {
		Jedis jedis=null;
		try {
			jedis=pool.getResource();
			return jedis.scard(key);
		}catch(Exception e) {
			logger.error("发生异常"+e.getMessage());
		
		}finally {
			if(jedis!=null) {
				jedis.close();
			}
		}
		
		return 0;
	}
	/**
	 * 是否是成员
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean sismember(String key,String value) {
		Jedis jedis=null;
		try {
			jedis=pool.getResource();
			return jedis.sismember(key,value);
		}catch(Exception e) {
			logger.error("发生异常"+e.getMessage());
			
		}finally {
			if(jedis!=null) {
				jedis.close();
			}
		}
		return false;
	}
	
	
}
