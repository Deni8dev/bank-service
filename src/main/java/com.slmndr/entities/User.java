package com.slmndr.entities;

import com.slmndr.common.Gender;
import com.slmndr.common.UserIdType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;


@Entity(name = "users")
@Table(name = "users")
@Data
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "user_id_type", columnDefinition = "enum('ID_CARD','FI_CARD','PASSPORT')")
    @Enumerated(EnumType.STRING)
    private UserIdType userIdType;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "gender", columnDefinition = "enum('MALE','FEMALE')")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Override
    public String toString() {
        return "{ " +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", userIdType=" + userIdType +
            ", userId='" + userId + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", country='" + country + '\'' +
            ", gender=" + gender +
            " " + '}';
    }
}
