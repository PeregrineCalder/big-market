package cn.peregrine.trigger.listener;

import cn.peregrine.domain.activity.model.entity.SkuRechargeEntity;
import cn.peregrine.domain.activity.model.valobj.OrderTradeTypeVO;
import cn.peregrine.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.peregrine.domain.credit.model.entity.TradeEntity;
import cn.peregrine.domain.credit.model.valobj.TradeNameVO;
import cn.peregrine.domain.credit.model.valobj.TradeTypeVO;
import cn.peregrine.domain.credit.service.ICreditAdjustService;
import cn.peregrine.domain.rebate.event.SendRebateMessageEvent;
import cn.peregrine.domain.rebate.model.valobj.RebateTypeVO;
import cn.peregrine.types.enums.ResponseCode;
import cn.peregrine.types.event.BaseEvent;
import cn.peregrine.types.exception.AppException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.listener
 * @className: RebateMessageCustomer
 * @author: Peregrine Calder
 * @description: 监听；行为返利消息
 * @date: 2024/8/27 22:03
 * @version: 1.0
 */
@Slf4j
@Component
public class RebateMessageCustomer {
    @Value("${spring.rabbitmq.topic.send_rebate}")
    private String topic;
    @Resource
    private IRaffleActivityAccountQuotaService raffleActivityAccountQuotaService;
    @Resource
    private ICreditAdjustService creditAdjustService;

    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.send_rebate}"))
    public void listener(String message) {
        try {
            log.info("监听用户行为返利消息 topic: {} message: {}", topic, message);
            // 1. 转换消息
            BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> eventMessage = JSON.parseObject(message, new TypeReference<BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage>>(){}.getType());
            SendRebateMessageEvent.RebateMessage rebateMessage = eventMessage.getData();

            switch (rebateMessage.getRebateType()) {
                case "sku":
                    SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
                    skuRechargeEntity.setUserId(rebateMessage.getUserId());
                    skuRechargeEntity.setSku(Long.valueOf(rebateMessage.getRebateConfig()));
                    skuRechargeEntity.setOutBusinessNo(rebateMessage.getBizId());
                    skuRechargeEntity.setOrderTradeType(OrderTradeTypeVO.rebate_no_pay_trade);
                    raffleActivityAccountQuotaService.createOrder(skuRechargeEntity);
                    break;
                case "integral":
                    TradeEntity tradeEntity = new TradeEntity();
                    tradeEntity.setUserId(rebateMessage.getUserId());
                    tradeEntity.setTradeName(TradeNameVO.REBATE);
                    tradeEntity.setTradeType(TradeTypeVO.FORWARD);
                    tradeEntity.setAmount(new BigDecimal(rebateMessage.getRebateConfig()));
                    tradeEntity.setOutBusinessNo(rebateMessage.getBizId());
                    creditAdjustService.createOrder(tradeEntity);
                    break;
            }
        } catch (AppException e) {
            if (ResponseCode.INDEX_DUP.getCode().equals(e.getCode())) {
                log.warn("监听用户行为返利消息，消费重复 topic: {} message: {}", topic, message, e);
                return;
            }
            throw e;
        } catch (Exception e) {
            log.error("监听用户行为返利消息，消费失败 topic: {} message: {}", topic, message, e);
            throw e;
        }
    }
}
