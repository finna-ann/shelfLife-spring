package com.spring.shelfLife.repository;

import com.spring.shelfLife.model.InventoryItem;
import com.spring.shelfLife.type.ItemDisposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByHouseholdId(Long householdId);
    List<InventoryItem> findByHouseholdIdAndDisposition(Long householdId, ItemDisposition disposition);
    boolean existsByHouseholdIdAndDisposition(Long householdId, ItemDisposition disposition);
    boolean existsByHouseholdId(Long householdId);
}
