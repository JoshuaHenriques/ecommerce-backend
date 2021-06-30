package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class Orders {

    // TODO: Fix enum import
    @Column
    private String orderStatus;

    @Column
    private ArrayList<Item> orders;

    // TODO: Implement addItemsToOrder()
    public Orders() {
        orderStatus = "PREPARING";
        this.orders = new ArrayList<Item>();
    }
}
