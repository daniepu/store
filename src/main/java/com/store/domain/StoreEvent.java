package com.store.domain;


import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class StoreEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer StoreEventId;
    @Enumerated(value = EnumType.STRING)
    private StoreEventType storeEventType;

    @OneToOne(mappedBy = "storeEvent" ,cascade = CascadeType.ALL)
    @ToString.Exclude
    private Product product;
}
