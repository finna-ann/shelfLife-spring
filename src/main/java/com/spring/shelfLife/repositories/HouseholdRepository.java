package com.spring.shelfLife.repositories;

import com.spring.shelfLife.models.Household;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
    List<Household> findByInviteCode(String inviteCode);
    boolean existsByInviteCode(String inviteCode);
}
