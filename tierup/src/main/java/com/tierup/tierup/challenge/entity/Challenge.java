package com.tierup.tierup.challenge.entity;

import com.tierup.tierup.user.entity.UserChallenge;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "challenge_name")
    private String name;
    @Column(name = "challenge_img")
    private String img;
    @Column(name = "challenge_begin_date")
    private String beginDate;
    @Column(name = "challenge_end_date")
    private String endDate;
    @Column(name = "challenge_point")
    private Long point;
    @Column(name = "challenge_state")
    private String state;
    @OneToMany(mappedBy = "challenge")
    private List<UserChallenge> userChallenges = new ArrayList<>();
}
