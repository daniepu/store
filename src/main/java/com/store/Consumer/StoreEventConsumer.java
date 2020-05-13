package com.store.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.Service.StoreEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StoreEventConsumer {

    @Autowired
    private StoreEventService storeEventService;

    @KafkaListener(topics = "store-events")
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        storeEventService.processMessage(consumerRecord);
        log.info("consumed record: {}",consumerRecord);
    }

}
