package com.rockfintech.reas.springboot.listener;

import com.rockfintech.reas.springboot.common.emun.MqExchange;
import com.rockfintech.reas.springboot.service.IXaLogService;
import com.rockfintech.reas.springboot.util.json.JsonUtils;
import com.rockfintech.reas.springboot.vo.mq.RabbitMqCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class XinAnNoticeListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(XinAnNoticeListener.class);
    @Autowired
    private IXaLogService xaLogService;

    @SuppressWarnings("rawtypes")
    @Override
    public void onMessage(Message message) {
        String body = new String(message.getBody());
        RabbitMqCommon common = null;
        try {
            logger.info("监听开始执行:" + body );
            common = JsonUtils.readValue(body, RabbitMqCommon.class);
            Map<String, Object> callbackMap = common.getCallbackMap();


            logger.info("当前交换机Exchange为：[" + common.getExchange() +"]");
            if (MqExchange.XANOTICE.getExchange().equals(common.getExchange())) {
                xaLogService.notice(callbackMap);
            }
           logger.info("监听执行结束");
        } catch (Exception e) {
            logger.error("MQ监听处理失败: ", e);
        }
    }
}
