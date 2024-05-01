package cn.peregrine.trigger.job;

import cn.peregrine.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.peregrine.domain.strategy.service.IRaffleStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.job
 * @className: UpdateAwardStockJob
 * @author: Peregrine Calder
 * @description: 更新奖品库存任务, 为了避免更新库存的压力打到数据库中, 这里采用redis更新缓存库存, 异步队列更新数据库
 * @date: 2024/4/16 01:15
 * @version: 1.0
 */
@Slf4j
@Component
public class UpdateAwardStockJob {
    @Resource
    private IRaffleStock raffleStock;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec() {
        try {
            StrategyAwardStockKeyVO strategyAwardStockKeyVO = raffleStock.takeQueueValue();
            if (strategyAwardStockKeyVO == null) {
                return;
            }
            log.info("定时任务，更新奖品消耗库存 strategyId:{} awardId:{}", strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
            raffleStock.updateStrategyAwardStock(strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
        } catch (Exception e) {
            log.error("定时任务，更新奖品消耗库存失败", e);
        }
    }

}
