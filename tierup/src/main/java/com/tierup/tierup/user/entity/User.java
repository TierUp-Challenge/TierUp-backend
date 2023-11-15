package com.tierup.tierup.user.entity;

import com.tierup.tierup.auth.dto.UserInfoResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    String username;

    @Column(name = "user_img")
    String img;

    @Column(name = "user_total_point")
    Long totalPoint;

    public User(UserInfoResponse userInfo) {
        this.username = userInfo.getUsername();
        this.img = userInfo.getImgUrl();
        this.totalPoint = 0L;
    }
}
