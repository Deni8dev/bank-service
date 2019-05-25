package com.slmndr.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "accounts")
@Table(name = "accounts")
@Getter
@Setter
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance", nullable = false, columnDefinition = "int DEFAULT 10000")
    private Integer balance;

    // TODO: Review here to get data of User owner of the account.
    @Column(name = "user_id")
    private Integer userId;
}
