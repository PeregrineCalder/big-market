package cn.peregrine.test.trigger;

import cn.peregrine.trigger.api.IRaffleStrategyService;
import cn.peregrine.trigger.api.dto.RaffleAwardListRequestDTO;
import cn.peregrine.trigger.api.dto.RaffleAwardListResponseDTO;
import cn.peregrine.types.model.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @projectName: big-market
 * @package: cn.peregrine.test.trigger
 * @className: RaffleStrategyControllerTest
 * @author: Peregrine Calder
 * @description: 营销抽奖服务测试
 * @date: 2024/5/2 21:32
 * @version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyControllerTest {
    @Resource
    private IRaffleStrategyService raffleStrategyService;

    @Test
    public void test_queryRaffleAwardList() {
        RaffleAwardListRequestDTO request = new RaffleAwardListRequestDTO();
        request.setUserId("xiaofuge");
        request.setActivityId(100301L);
        Response<List<RaffleAwardListResponseDTO>> response = raffleStrategyService.queryRaffleAwardList(request);

        log.info("请求参数：{}", JSON.toJSONString(request));
        log.info("测试结果：{}", JSON.toJSONString(response));
    }

}
