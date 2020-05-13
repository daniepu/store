package com.store.Repository;

import com.store.domain.StoreEvent;
import org.springframework.data.repository.CrudRepository;

public interface StoreEventRepository extends CrudRepository<StoreEvent,Integer> {

}
