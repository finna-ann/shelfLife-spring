package com.spring.shelfLife.repository;

import com.spring.shelfLife.model.InventoryItem;
import com.spring.shelfLife.type.ItemDisposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByHouseholdId(UUID householdId);
    List<InventoryItem> findByHouseholdIdAndDisposition(UUID householdId, ItemDisposition disposition);
    boolean existsByHouseholdIdAndDisposition(UUID householdId, ItemDisposition disposition);
    boolean existsByHouseholdId(UUID householdId);
}
