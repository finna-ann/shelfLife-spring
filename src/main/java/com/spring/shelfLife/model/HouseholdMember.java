package com.spring.shelfLife.model;

import com.spring.shelfLife.type.HouseholdRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "household_members", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "household_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseholdMember {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id",nullable = false)
    private Household household;

    @Enumerated(EnumType.STRING)
    @Column(name = "household_role", nullable = false)
    private HouseholdRole role;

    @Column(name = "joined_at", nullable = false,updatable = false)
    private Instant joinedAt;

    @PrePersist
    void onJoin() {
        joinedAt = Instant.now();
    }
}
