package com.slmndr.entities;


import com.slmndr.entities.enums.TransactionType;
import com.slmndr.entities.enums.UserIdType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "type", columnDefinition = "enum('INCOMING','SENT')")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "value")
    private float value;

    @Column(name = "date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "thirdparty_account")
    private String thirdPartyAccount;

    @Column(name = "thirdparty_id")
    private String thirdPartyId;

    @Column(name = "thirdparty_id_type", columnDefinition = "enum('ID_CARD','FI_CARD','PASSPORT')")
    @Enumerated(EnumType.STRING)
    private UserIdType thirdPartyIdType;

    @Column(name = "thirdparty_name")
    private String thirdPartyName;

    @Column(name = "approval")
    private Boolean approval;

    @Column(name = "id_account")
    private Integer accountId;
}
