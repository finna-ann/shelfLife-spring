package com.spring.shelfLife.repository;

import com.spring.shelfLife.model.HouseholdMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Long> {
    List<HouseholdMember> findByUserId(UUID userId);
    List<HouseholdMember> findByHouseholdId(UUID householdId);
    Optional<HouseholdMember> findByUserIdAndHouseholdId(UUID user, UUID householdId);
    boolean existsByUserIdAndHouseholdId(UUID user, UUID householdId);
}
