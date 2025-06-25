package com.ilyaproject.newsTelegramBot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private String name;
    @OneToMany(mappedBy = "city", targetEntity = User.class, fetch = FetchType.EAGER)
    private Set<User> users;
}
