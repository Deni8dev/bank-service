package com.slmndr.entities.enums;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum TransactionType implements Serializable {
    INCOMING, SENT
}
