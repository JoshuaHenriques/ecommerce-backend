package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.inventory.StoreInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The interface Inventory repository.
 */
@Repository
public interface InventoryRepository extends JpaRepository<StoreInventory, UUID> {

}