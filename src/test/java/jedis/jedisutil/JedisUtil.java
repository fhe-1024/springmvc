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
	 * ��ȡJedisʵ��
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
	 * �ͷ�jedis��Դ
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * ���ù���ʱ��
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
	 * ȡ������ʱ��
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
	 * ����key
	 */
	public static String set(String key, String value) {
		Jedis jedis = getJedis();
		String stata = jedis.set(key, value);
		returnResource(jedis);
		return stata;
	}

	/**
	 * ��ȡvalue
	 */
	public static String get(String key) {
		Jedis jedis = getJedis();
		String stata = jedis.get(key);
		returnResource(jedis);
		return stata;
	}

	/**
	 * ��ѯ���и���ͨ���Key
	 */
	public static Set<String> keys(String pattern) {
		Jedis jedis = getJedis();
		Set<String> set = jedis.keys(pattern);
		returnResource(jedis);
		return set;
	}

	/**
	 * value������
	 */
	public static long incr(String key) {
		Jedis jedis = getJedis();
		long l = jedis.incr(key);
		returnResource(jedis);
		return l;
	}

	/**
	 * ������ֵ����ȡ��ֵ
	 */
	public static String getset(String key, String value) {
		Jedis jedis = getJedis();
		String s = jedis.getSet(key, value);
		returnResource(jedis);
		return s;
	}

	/**
	 * ����key������ʱ��
	 */
	public static String setex(String key, int seconds, String value) {
		Jedis jedis = getJedis();
		String stata = jedis.setex(key, seconds, value);
		returnResource(jedis);
		return stata;
	}

	/**
	 * ɾ��keys��Ӧ�ļ�¼,�����Ƕ��key
	 *
	 * @param String
	 *            ... keys
	 * @return ɾ���ļ�¼��
	 */
	public static long del(String... keys) {
		Jedis jedis = getJedis();
		long count = jedis.del(keys);
		returnResource(jedis);
		return count;
	}

	/**
	 * �ж�key�Ƿ����
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
	 * ��Listͷ��׷�Ӽ�¼
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return ��¼����
	 */
	public static long lpush(String key, String value) {
		Jedis jedis = getJedis();
		long count = jedis.lpush(key, value);
		returnResource(jedis);
		return count;
	}

	/**
	 * ��Listβ��׷�Ӽ�¼
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return ��¼����
	 */
	public static long rpush(String key, String value) {
		Jedis jedis = getJedis();
		long count = jedis.rpush(key, value);
		returnResource(jedis);
		return count;
	}

	/**
	 * ��List�еĵ�һ����¼�Ƴ�List
	 * 
	 * @param byte[]
	 *            key
	 * @return �Ƴ��ļ�¼
	 */
	public String lpop(String key) {
		Jedis jedis = getJedis();
		String value = jedis.lpop(key);
		returnResource(jedis);
		return value;
	}

	/**
	 * ��List������һ����¼�Ƴ�List
	 *
	 * @param byte[]
	 *            key
	 * @return �Ƴ��ļ�¼
	 */
	public static String rpop(String key) {
		Jedis jedis = getJedis();
		String value = jedis.rpop(key);
		returnResource(jedis);
		return value;
	}

	/**
	 * �Ƴ�List�е�Ԫ��
	 * 
	 * @param key
	 * @param count
	 *            �Ƴ��ĸ���
	 * @param value
	 *            Ҫ�Ƴ�Ԫ�ص�ֵ
	 * @return
	 */
	public static long lrem(String key, long count, String value) {
		Jedis jedis = getJedis();
		long l = jedis.lrem(key, count, value);
		returnResource(jedis);
		return l;
	}

	/**
	 * ��ȡָ����Χ�ļ�¼��������Ϊ��ҳʹ��
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
	 * List����
	 * 
	 * @param String
	 *            key
	 * @return ����
	 */
	public static long llen(String key) {
		Jedis jedis = getJedis();
		long count = jedis.llen(key);
		returnResource(jedis);
		return count;
	}

	/**
	 * ��ȡList��ָ��λ�õ�ֵ
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            index λ��
	 * @return ֵ
	 **/
	public static String lindex(String key, int index) {
		Jedis jedis = getJedis();
		String value = jedis.lindex(key, index);
		returnResource(jedis);
		return value;
	}

	/**
	 * ���ǲ���,������List��ָ��λ�õ�ֵ
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            index λ��
	 * @param String
	 *            value ֵ
	 * @return ״̬��
	 */
	public static String lset(String key, int index, String value) {
		Jedis jedis = getJedis();
		String status = jedis.lset(key, index, value);
		returnResource(jedis);
		return status;
	}

	/**
	 * ���һ����Ӧ��ϵ
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            field
	 * @param String
	 *            value
	 * @return ״̬�� 1�ɹ���0ʧ�ܣ�field�Ѵ��ڽ����£�Ҳ����0
	 **/
	public static long hset(String key, String field, String value) {
		Jedis jedis = getJedis();
		long s = jedis.hset(key, field, value);
		returnResource(jedis);
		return s;
	}

	/**
	 * ��Ӷ�Ӧ��ϵ��ֻ����field������ʱ��ִ��
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            field
	 * @param String
	 *            value
	 * @return ״̬�� 1�ɹ���0ʧ��field�Ѵ�
	 **/
	public static long hsetnx(String key, String field, String value) {
		Jedis jedis = getJedis();
		long s = jedis.hsetnx(key, field, value);
		returnResource(jedis);
		return s;
	}

	/**
	 * ����hash��ָ���洢λ�õ�ֵ
	 *
	 * @param String
	 *            key
	 * @param String
	 *            field �洢������
	 * @return �洢��Ӧ��ֵ
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
