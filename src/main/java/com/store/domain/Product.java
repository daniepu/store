package com.store.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@AllArgsConstructor /// constructor with all arguments
@NoArgsConstructor/// constructor with no arguments
@Data /// setters and getters
@Builder // buildes the attributes
@Entity
public class Product {

    @Id
    private Integer id;
    private String name;
    private String Category;

    @OneToOne
    //@JoinColumn(name = "StoreEventId")
    private StoreEvent storeEvent;

}


