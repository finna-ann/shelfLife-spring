package com.spring.shelfLife.repository;

import com.spring.shelfLife.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
    List<Household> findByInviteCode(String inviteCode);
    boolean existsByInviteCode(String inviteCode);
}
