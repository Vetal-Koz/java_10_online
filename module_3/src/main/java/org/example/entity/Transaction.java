package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    private String description;
    private Double sum;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "transaction_category_id", nullable = false)
    private TransactionCategory transactionCategory;

    public Transaction() {
        this.createdOn = LocalDateTime.now();
    }
}
