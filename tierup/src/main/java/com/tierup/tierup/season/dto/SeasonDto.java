package com.tierup.tierup.season.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SeasonDto {

    private String name;

    private String img;

    private String beginDate;

    private String endDate;

    private Long point;

    private String state;

    @Builder
    public SeasonDto(String name, String img, String beginDate, String endDate, Long point, String state) {
        this.name = name;
        this.img = img;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.point = point;
        this.state = state;
    }
}
