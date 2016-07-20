package jedis.test;

import jedis.jedisutil.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
	public static void main(String[] args) {
		for (int i = 0; i < 5000; i++) {
			JedisUtil.set("" + i, "" + i);

		}
		for (int i = 0; i < 5000; i++) {

			System.out.println(JedisUtil.get("" + i));
		}
	}
}
