package com.tierup.tierup.season.entity;

import com.tierup.tierup.user.entity.UserSeason;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Season {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_id")
    private Long id;

    @Column(name = "season_name")
    private String name;
    @Column(name = "season_img")
    private String img;
    @Column(name = "season_begin_date")
    private String beginDate;
    @Column(name = "season_end_date")
    private String endDate;
    @Column(name = "season_point")
    private Long point;
    @Column(name = "season_state")
    private String state;
    @OneToMany(mappedBy = "season")
    private List<UserSeason> userSeasons = new ArrayList<>();
}
