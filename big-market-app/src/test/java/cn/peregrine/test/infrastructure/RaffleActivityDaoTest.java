package cn.peregrine.test.infrastructure;

import cn.peregrine.infrastructure.persistent.dao.IRaffleActivityDao;
import cn.peregrine.infrastructure.persistent.po.RaffleActivity;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.test.infrastructure
 * @className: RaffleActivityDaoTest
 * @author: Peregrine Calder
 * @description: 抽奖活动配置Dao测试
 * @date: 2024/4/24 21:03
 * @version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityDaoTest {
    @Resource
    private IRaffleActivityDao raffleActivityDao;
    @Test
    public void test_queryRaffleActivityByActivityId() {
        RaffleActivity raffleActivity = raffleActivityDao.queryRaffleActivityByActivityId(100301L);
        log.info("测试结果：{}", JSON.toJSONString(raffleActivity));
    }


}
