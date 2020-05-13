package com.store.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.Producer.StoreEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StoreEventController {

    @Autowired
    public StoreEventProducer storeEventProducer;

    @PostMapping("/v1/storeevents")
    public ResponseEntity<StoreEvent> postMessage(@RequestBody StoreEvent storeEvent) throws JsonProcessingException {
        storeEvent.setStoreEventType(StoreEventType.NEW);
        storeEventProducer.sendStoreEvent(storeEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeEvent);
    }

    @PutMapping("v1/storeevents")
    public ResponseEntity<?> putMessage(@RequestBody StoreEvent storeEvent) throws JsonProcessingException {
        if (storeEvent.getStoreEventId() == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(storeEvent);
        }
        storeEvent.setStoreEventType(StoreEventType.UPDATE);
        storeEventProducer.sendStoreEvent(storeEvent);
        return ResponseEntity.status(HttpStatus.OK).body(storeEvent);
    }

}
