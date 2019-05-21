package com.slmndr.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "otps")
@Table(name = "otps")
@Getter
@Setter
public class OTP implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "otp", unique = true)
    private Integer otp;

    @Column(name = "account_id")
    private Integer accountId;
}
