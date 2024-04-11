package cn.peregrine.test;

import cn.peregrine.infrastructure.persistent.redis.RedissonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {
    @Resource
    private RedissonService redissonService;
    @Test
    public void test() {
        RMap<Object, Object> map = redissonService.getMap("strategy_id_100001");
        map.put(1, 101);
        log.info("测试结果:{}", redissonService.getFromMap("strategy_id_100001", 1).toString());
    }

}
