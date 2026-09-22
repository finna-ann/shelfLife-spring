package com.spring.shelfLife.repositories;

import com.spring.shelfLife.models.HouseholdMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Long> {
    List<HouseholdMember> findByUserId(Long userId);
    List<HouseholdMember> findByHouseholdId(Long householdId);
    Optional<HouseholdMember> findByUserIdAndHouseholdId(Long user, Long householdId);
    boolean existsByUserIdAndHouseholdId(Long user, Long householdId);
}
