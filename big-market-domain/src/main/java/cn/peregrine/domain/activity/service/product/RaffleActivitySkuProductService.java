package cn.peregrine.domain.activity.service.product;

import cn.peregrine.domain.activity.model.entity.SkuProductEntity;
import cn.peregrine.domain.activity.repository.IActivityRepository;
import cn.peregrine.domain.activity.service.IRaffleActivitySkuProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.service.product
 * @className: RaffleActivitySkuProductService
 * @author: Peregrine Calder
 * @description: sku商品服务
 * @version: 1.0
 */
@Service
public class RaffleActivitySkuProductService implements IRaffleActivitySkuProductService {

    @Resource
    private IActivityRepository repository;

    @Override
    public List<SkuProductEntity> querySkuProductEntityListByActivityId(Long activityId) {
        return repository.querySkuProductEntityListByActivityId(activityId);
    }

}

