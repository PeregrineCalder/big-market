package cn.peregrine.domain.activity.service;

import cn.peregrine.domain.activity.repository.IActivityRepository;
import org.springframework.stereotype.Service;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.service
 * @className: RaffleActivityService
 * @author: Peregrine Calder
 * @description: 抽奖活动服务
 * @date: 2024/4/25 23:35
 * @version: 1.0
 */
@Service
public class RaffleActivityService extends AbstractRaffleActivity{
    public RaffleActivityService(IActivityRepository activityRepository) {
        super(activityRepository);
    }
}
