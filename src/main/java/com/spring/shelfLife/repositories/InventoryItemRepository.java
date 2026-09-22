package com.spring.shelfLife.repositories;

import com.spring.shelfLife.models.InventoryItem;
import com.spring.shelfLife.types.ItemDisposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByHouseholdId(Long householdId);
    List<InventoryItem> findByHouseholdIdAndDisposition(Long householdId, ItemDisposition disposition);
    boolean existsByHouseholdIdAndDisposition(Long householdId, ItemDisposition disposition);
    boolean existsByHouseholdId(Long householdId);
}
