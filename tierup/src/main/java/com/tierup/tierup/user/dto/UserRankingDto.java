package com.tierup.tierup.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRankingDto {
    private String name;
    private Long point;
    private Long ranking;
    @Builder
    public UserRankingDto(String name, Long point, Long ranking) {
        this.name = name;
        this.point = point;
        this.ranking = ranking;
    }
}
