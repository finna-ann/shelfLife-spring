package com.spring.shelfLife.model;

import com.spring.shelfLife.type.ItemDisposition;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "household_id",nullable = false)
    private Household household;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "added_by_user_id", nullable = false)
    private User addedBy;

    @Column(name = "quantity" , nullable = false)
    private Integer quantity;

    @Column(name = "unit",nullable = false)
    private String unit;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "expiration_date",nullable = false)
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_disposition",nullable = false)
    private ItemDisposition disposition = ItemDisposition.UNUSED;

    @Column(name = "created_at",nullable = false)
    private Instant createdAt;

    @Column(name = "disposed_at")
    private Instant disposedAt;

    @PrePersist
    void onCreate(){
        createdAt = Instant.now();
    }
}
