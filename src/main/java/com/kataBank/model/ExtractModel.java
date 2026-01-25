package com.kataBank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "extract")
public class ExtractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonBackReference
    private AccountModel account;

    @Column(name = "register_date", nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerDate;

    @PrePersist
    public void prePersist() {
        this.registerDate = LocalDateTime.now();
    }
}
