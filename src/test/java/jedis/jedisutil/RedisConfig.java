package jedis.jedisutil;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RedisConfig {

	private static final Log log = LogFactory.getLog(RedisConfig.class);

	public static PropertiesConfiguration CONFIG;

	static {
		try {
			CONFIG = new PropertiesConfiguration("redis.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** Redis maxTotal **/
	public static final int REDIS_MAXTOTAL = CONFIG.getInt("redis.pool.maxTotal");

	/** Redis maxIdle **/
	public static final int REDIS_MAXIDLE = CONFIG.getInt("redis.pool.maxIdle");

	/** Redis maxWaitMillis **/
	public static final long REDIS_MAXWAITMILLIS = CONFIG.getLong("redis.pool.maxWaitMillis");

	/** Redis testOnBorrow **/
	public static final boolean REDIS_TESTONBORROW = CONFIG.getBoolean("redis.pool.testOnBorrow");

	/** Redis testOnReturn **/
	public static final boolean REDIS_TESTONRETURN = CONFIG.getBoolean("redis.pool.testOnReturn");

	/** Redis maxTotal **/
	public static final String REDIS_IP = CONFIG.getString("redis.ip");

	/** Redis maxTotal **/
	public static final int REDIS_PORT = CONFIG.getInt("redis.port");

}
