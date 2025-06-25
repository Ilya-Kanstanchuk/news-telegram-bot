package com.ilyaproject.newsTelegramBot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Long chatId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "cityId")
    private City city;
}
