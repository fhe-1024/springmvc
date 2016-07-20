package jedis.test;

import jedis.jedisutil.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
	public static void main(String[] args) {

		JedisUtil.set("hello", "world");
		System.out.println(JedisUtil.get("hello"));
	}
}
