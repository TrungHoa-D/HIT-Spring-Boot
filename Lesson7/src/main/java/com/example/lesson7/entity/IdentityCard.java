package com.example.lesson7.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "identity_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdentityCard {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
