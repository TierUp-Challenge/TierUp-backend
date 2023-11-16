package com.tierup.tierup.user.entity;

import com.tierup.tierup.season.entity.Season;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSeason {
    @Id @GeneratedValue
    @Column(name = "user_season_id")
    private Long id;
    @Column(name = "user_season_point")
    private Long seasonPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;
}
