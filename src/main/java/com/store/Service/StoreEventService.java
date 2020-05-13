package com.store.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.Repository.StoreEventRepository;
import com.store.domain.StoreEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StoreEventService {

    @Autowired
    private StoreEventRepository storeEventRepository;

    @Autowired
    ObjectMapper objectMapper;

    public void processMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        StoreEvent storeEvent = objectMapper.readValue(consumerRecord.value(),StoreEvent.class);
        log.info("the store event is: {}",storeEvent);

        switch (storeEvent.getStoreEventType()) {
            case NEW:
                save(storeEvent);
                break;
            case UPDATE:
                validate(storeEvent);
                save(storeEvent);
                break;
            default:
                log.info("invalid store event : {} ", storeEvent);
        }
    }

    private void validate(StoreEvent storeEvent) {
        if (storeEvent.getStoreEventId()==null)
        {
            throw new IllegalArgumentException("the store event id is missing");
        }
        Optional<StoreEvent> storeEventOptional = storeEventRepository.findById(storeEvent.getStoreEventId());
        if (!storeEventOptional.isPresent())
        {
            throw new IllegalArgumentException("not a valid store") ;
        }
        log.info("Validation is succeful for the store : {}",storeEventOptional.get());
    }

    private void save(StoreEvent storeEvent) {
        storeEvent.getProduct().setStoreEvent(storeEvent);
        storeEventRepository.save(storeEvent);
        log.info("successfully persisted store event: {}",storeEvent);
    }

}
