package com.tierup.tierup.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, name = "user_name")
    String name;

    @Column(name = "user_img")
    String img;

    @Column(name = "user_total_point")
    Long totalPoint;

    @Builder
    public User(Long id, String name, String img, Long totalPoint) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.totalPoint = totalPoint;
    }
}
