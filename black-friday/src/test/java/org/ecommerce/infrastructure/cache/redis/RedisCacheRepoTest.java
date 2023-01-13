package org.ecommerce.infrastructure.cache.redis;

import static org.assertj.core.api.Assertions.assertThat;

import org.ecommerce.infrastructure.redisrepo.RedisPromotionCacheDomainRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class RedisCacheRepoTest {

  @Autowired
  RedisPromotionCacheDomainRepo redisCacheRepo;

  @Test
  void test() {
    redisCacheRepo.set("project_name", "blackFriday");
    String projectName = (String) redisCacheRepo.get("project_name");
    assertThat(projectName).isEqualTo("blackFriday");
    redisCacheRepo.delete("project_name");
  }
}