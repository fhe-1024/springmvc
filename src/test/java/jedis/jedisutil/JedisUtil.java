package jedis.jedisutil;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	private static JedisPool jedisPool = null;

	static {
		try {
			JedisPoolConfig redisConfig = new JedisPoolConfig();
			redisConfig.setMaxTotal(Integer.valueOf(RedisConfig.REDIS_MAXTOTAL));
			redisConfig.setMaxIdle(Integer.valueOf(RedisConfig.REDIS_MAXIDLE));
			redisConfig.setMaxWaitMillis(Long.valueOf(RedisConfig.REDIS_MAXWAITMILLIS));
			redisConfig.setTestOnBorrow(Boolean.valueOf(RedisConfig.REDIS_TESTONBORROW));
			redisConfig.setTestOnReturn(Boolean.valueOf(RedisConfig.REDIS_TESTONRETURN));
			jedisPool = new JedisPool(redisConfig, RedisConfig.REDIS_IP, Integer.valueOf(RedisConfig.REDIS_PORT));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * 设置过期时间
	 *
	 * @param key
	 * @param seconds
	 */
	public static void expire(String key, int seconds) {
		if (seconds <= 0) {
			return;
		}
		Jedis jedis = getJedis();
		jedis.expire(key, seconds);
		returnResource(jedis);
	}

	/**
	 * 取消过期时间
	 *
	 * @param key
	 * @param seconds
	 */
	public static void persist(String key) {
		Jedis jedis = getJedis();
		jedis.persist(key);
		returnResource(jedis);
	}

	/**
	 * 设置key
	 */
	public static String set(String key, String value) {
		Jedis jedis = getJedis();
		String stata = jedis.set(key, value);
		returnResource(jedis);
		return stata;
	}

	/**
	 * 获取value
	 */
	public static String get(String key) {
		Jedis jedis = getJedis();
		String stata = jedis.get(key);
		returnResource(jedis);
		return stata;
	}

	/**
	 * 查询所有复合通配的Key
	 */
	public static Set<String> keys(String pattern) {
		Jedis jedis = getJedis();
		Set<String> set = jedis.keys(pattern);
		returnResource(jedis);
		return set;
	}

	/**
	 * value自增长
	 */
	public static long incr(String key) {
		Jedis jedis = getJedis();
		long l = jedis.incr(key);
		returnResource(jedis);
		return l;
	}

	/**
	 * 设置新值并获取旧值
	 */
	public static String getset(String key, String value) {
		Jedis jedis = getJedis();
		String s = jedis.getSet(key, value);
		returnResource(jedis);
		return s;
	}

	/**
	 * 设置key带过期时间
	 */
	public static String setex(String key, int seconds, String value) {
		Jedis jedis = getJedis();
		String stata = jedis.setex(key, seconds, value);
		returnResource(jedis);
		return stata;
	}

	/**
	 * 删除keys对应的记录,可以是多个key
	 *
	 * @param String
	 *            ... keys
	 * @return 删除的记录数
	 */
	public static long del(String... keys) {
		Jedis jedis = getJedis();
		long count = jedis.del(keys);
		returnResource(jedis);
		return count;
	}

	/**
	 * 判断key是否存在
	 *
	 * @param String
	 *            key
	 * @return boolean
	 */
	public static boolean exists(String key) {
		Jedis sjedis = getJedis();
		boolean exis = sjedis.exists(key);
		returnResource(sjedis);
		return exis;
	}

	/**
	 * 向List头部追加记录
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 记录总数
	 */
	public static long lpush(String key, String value) {
		Jedis jedis = getJedis();
		long count = jedis.lpush(key, value);
		returnResource(jedis);
		return count;
	}

	/**
	 * 向List尾部追加记录
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 记录总数
	 */
	public static long rpush(String key, String value) {
		Jedis jedis = getJedis();
		long count = jedis.rpush(key, value);
		returnResource(jedis);
		return count;
	}

	/**
	 * 将List中的第一条记录移出List
	 * 
	 * @param byte[]
	 *            key
	 * @return 移出的记录
	 */
	public String lpop(String key) {
		Jedis jedis = getJedis();
		String value = jedis.lpop(key);
		returnResource(jedis);
		return value;
	}

	/**
	 * 将List中最后第一条记录移出List
	 *
	 * @param byte[]
	 *            key
	 * @return 移出的记录
	 */
	public static String rpop(String key) {
		Jedis jedis = getJedis();
		String value = jedis.rpop(key);
		returnResource(jedis);
		return value;
	}

	/**
	 * 移除List中的元素
	 * 
	 * @param key
	 * @param count
	 *            移除的个数
	 * @param value
	 *            要移除元素的值
	 * @return
	 */
	public static long lrem(String key, long count, String value) {
		Jedis jedis = getJedis();
		long l = jedis.lrem(key, count, value);
		returnResource(jedis);
		return l;
	}

	/**
	 * 获取指定范围的记录，可以做为分页使用
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            start
	 * @param long
	 *            end
	 * @return List
	 */
	public static List<String> lrange(String key, long start, long end) {
		Jedis sjedis = getJedis();
		List<String> list = sjedis.lrange(key, start, end);
		returnResource(sjedis);
		return list;
	}

	/**
	 * List长度
	 * 
	 * @param String
	 *            key
	 * @return 长度
	 */
	public static long llen(String key) {
		Jedis jedis = getJedis();
		long count = jedis.llen(key);
		returnResource(jedis);
		return count;
	}

	/**
	 * 获取List中指定位置的值
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            index 位置
	 * @return 值
	 **/
	public static String lindex(String key, int index) {
		Jedis jedis = getJedis();
		String value = jedis.lindex(key, index);
		returnResource(jedis);
		return value;
	}

	/**
	 * 覆盖操作,将覆盖List中指定位置的值
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            index 位置
	 * @param String
	 *            value 值
	 * @return 状态码
	 */
	public static String lset(String key, int index, String value) {
		Jedis jedis = getJedis();
		String status = jedis.lset(key, index, value);
		returnResource(jedis);
		return status;
	}

	/**
	 * 添加一个对应关系
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            field
	 * @param String
	 *            value
	 * @return 状态码 1成功，0失败，field已存在将更新，也返回0
	 **/
	public static long hset(String key, String field, String value) {
		Jedis jedis = getJedis();
		long s = jedis.hset(key, field, value);
		returnResource(jedis);
		return s;
	}

	/**
	 * 添加对应关系，只有在field不存在时才执行
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            field
	 * @param String
	 *            value
	 * @return 状态码 1成功，0失败field已存
	 **/
	public static long hsetnx(String key, String field, String value) {
		Jedis jedis = getJedis();
		long s = jedis.hsetnx(key, field, value);
		returnResource(jedis);
		return s;
	}

	/**
	 * 返回hash中指定存储位置的值
	 *
	 * @param String
	 *            key
	 * @param String
	 *            field 存储的名字
	 * @return 存储对应的值
	 */
	public static String hget(String key, String field) {
		Jedis jedis = getJedis();
		String s = jedis.hget(key, field);
		returnResource(jedis);
		return s;
	}

	public static void hmset(String key, Map<String, String> map) {
		Jedis jedis = getJedis();
		jedis.hmset(key, map);
		returnResource(jedis);
	}

	public static Map<String, String> hgetAll(String key) {
		Jedis jedis = getJedis();
		Map<String, String> map = jedis.hgetAll(key);
		returnResource(jedis);
		return map;
	}
}
