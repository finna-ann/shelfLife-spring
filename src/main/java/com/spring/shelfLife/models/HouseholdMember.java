package com.spring.shelfLife.models;

import com.spring.shelfLife.types.HouseholdRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "household_members", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "household_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseholdMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id",nullable = false)
    private Household household;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HouseholdRole role;

    @Column(nullable = false,updatable = false)
    private Instant joinedAt;

    @PrePersist
    void onJoin() {
        joinedAt = Instant.now();
    }
}
