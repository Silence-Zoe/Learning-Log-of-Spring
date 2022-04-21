package com.silence.event;

import com.alibaba.fastjson.JSONObject;
import com.silence.DTO.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void fireEvent(EventDTO event) {
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }
}
