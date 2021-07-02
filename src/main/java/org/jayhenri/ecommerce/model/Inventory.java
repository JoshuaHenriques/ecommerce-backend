package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

// TODO: Abstract for different type of items
// TODO: Implement ratings
// TODO: Implement comments
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = -1112477284611964207L;

    @Id
    @Column(unique = true)
    private String productName;

    @Column
    private int quantity;

    private Item item;
}
