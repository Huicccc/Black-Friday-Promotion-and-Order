package org.ecommerce.infrastructure.redisrepo;

import com.alibaba.fastjson.JSON;
import java.util.Arrays;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.domain.promotionCache.PromotionCacheDomain;
import org.ecommerce.domain.promotionCache.PromotionCacheRepository;
import org.ecommerce.domain.promotionStockCache.PromotionStockCacheDomain;
import org.ecommerce.domain.promotionStockCache.PromotionStockCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class RedisPromotionCacheDomainRepo implements PromotionCacheRepository,
    PromotionStockCacheRepository {

  @Autowired
  RedisTemplate<String, String> redisTemplate;

  public void set(String key, Object value) {
    redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
  }

  public String get(String key) {
    if (Objects.isNull(key)) {
      return null;
    }
    return redisTemplate.opsForValue().get(key);
  }

  public void delete(String key) {
    if (Objects.nonNull(get(key))) {
      redisTemplate.delete(key);
    } else {
      throw new RuntimeException("key does not exist");
    }
  }

  @Override
  public void setPromotionCacheDomain(PromotionCacheDomain promotionCacheDomain) {
    set(promotionCacheDomain.getPromotionId(), promotionCacheDomain);
  }

  @Override
  public PromotionCacheDomain getPromotionCacheById(String id) {
    return JSON.parseObject(get(id), PromotionCacheDomain.class);
  }


  @Override
  public void setStockCache(String id, Long stock) {
    set(PromotionStockCacheDomain.createPromotionStockKey(id), stock);
  }

  @Override
  public Long getStockCache(String id) {
    String stockKey = PromotionStockCacheDomain.createPromotionStockKey(id);
    return JSON.parseObject(get(stockKey), Long.class);
  }

  @Override
  public boolean lockStockCache(String id) {
    // ---lua script return Long
    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
    // 1) set lua script source
    redisScript.setScriptSource(
        new ResourceScriptSource(new ClassPathResource("redis/lockStock.lua")));
    // 2) return type must be in: Long, Boolean, List, deserialized value
    redisScript.setResultType(Long.class);

    try {
      Long stock = redisTemplate.execute(redisScript,
          Arrays.asList(PromotionStockCacheDomain.createPromotionStockKey(id)));
      if (!Objects.isNull(stock) && stock >= 0L) {
        log.info("Redis: lock-stock in cache successfully");
        return true;
      } else {
        log.info("Redis: lock-stock in cache failed");
        return false;
      }
    } catch (Throwable throwable) {
      log.error("Fail to lock stock for promotionCommodityKey: {}",
          PromotionStockCacheDomain.createPromotionStockKey(id));
      return false;
    }
  }

  @Override
  public boolean revertStockCache(String id) {
    // ---lua script return Long
    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
    // 1) set lua script source
    redisScript.setScriptSource(
        new ResourceScriptSource(new ClassPathResource("redis/revertStock.lua")));
    // 2) return type must be in: Long, Boolean, List, deserialized value
    redisScript.setResultType(Long.class);

    try {
      Long stock = redisTemplate.execute(redisScript,
          Arrays.asList(PromotionStockCacheDomain.createPromotionStockKey(id)));
      if (!Objects.isNull(stock) && stock >= 0L) {
        log.info("Redis: revert-stock in cache successfully");
        return true;
      } else {
        log.info("Redis: revert-stock in cache failed");
        return false;
      }
    } catch (Throwable throwable) {
      log.error("Fail to lock stock for promotionCommodityKey: {}",
          PromotionStockCacheDomain.createPromotionStockKey(id));
      return false;
    }
  }

}
