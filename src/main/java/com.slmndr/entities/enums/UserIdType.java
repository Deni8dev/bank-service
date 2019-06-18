package com.slmndr.entities.enums;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum UserIdType implements Serializable {
    ID_CARD, FI_CARD, PASSPORT
}
