package com.baizhi.log;

import com.baizhi.dao.BzLogMapper;
import com.baizhi.entity.BzLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Service
public class MqBootListener {

    /**
     * @JmsListener 可以把一个方法变为 Mq的监听方法
     * 方法的形参为 Message
     * 该方法不需要手动调用 在项目启动之后 SpringBoot会自动调用
     * @param message
     */
    @Autowired
    private BzLogMapper logMapper;

    @JmsListener(destination = "mall-log")
    public void bootListener(Message message){
        TextMessage textMessage = (TextMessage) message;
        ObjectMapper mapper = new ObjectMapper();
        try {
            BzLog bzLog = mapper.readValue(textMessage.getText(), BzLog.class);
            logMapper.insert(bzLog);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
