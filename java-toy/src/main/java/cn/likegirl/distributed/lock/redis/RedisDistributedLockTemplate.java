package cn.likegirl.distributed.lock.redis;

import cn.likegirl.distributed.lock.Callback;
import cn.likegirl.distributed.lock.DistributedLockTemplate;
import java.util.concurrent.TimeUnit;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

/**
 * Created by sunyujia@aliyun.com on 2016/2/26.
 */
public class RedisDistributedLockTemplate implements DistributedLockTemplate {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RedisDistributedLockTemplate.class);

    private JedisPool jedisPool;


    public RedisDistributedLockTemplate(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


    @Override
    public Object execute(String lockId, int timeout, Callback callback) {
        RedisReentrantLock distributedReentrantLock = null;
        boolean getLock=false;
        try {
            distributedReentrantLock = new RedisReentrantLock(jedisPool,lockId);
            if(distributedReentrantLock.tryLock(new Long(timeout), TimeUnit.MILLISECONDS)){
                getLock=true;
                return callback.onGetLock();
            }else{
                return callback.onTimeout();
            }
        }catch(InterruptedException ex){
            log.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {
            if(getLock) {
                distributedReentrantLock.unlock();
            }
        }
        return null;
    }
}
