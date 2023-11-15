package com.tierup.tierup.season.service;

import com.tierup.tierup.season.dto.SeasonDto;
import com.tierup.tierup.season.entity.Season;
import com.tierup.tierup.season.repository.SeasonRepository;
import com.tierup.tierup.user.dto.UserRankingDto;
import com.tierup.tierup.user.entity.UserSeason;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;

    public List<SeasonDto> findAllSeasons(){
        List<Season> seasons = seasonRepository.findAll();

        return seasons.stream()
                .map(entity -> SeasonDto.builder()
                        .name(entity.getName())
                        .img(entity.getImg())
                        .beginDate(entity.getBeginDate())
                        .endDate(entity.getEndDate())
                        .point(entity.getPoint())
                        .state(entity.getState()).build())
                .collect(Collectors.toList());
    }

    public SeasonDto findSeasonById(Long id) {
        Optional<Season> challengeOptional = seasonRepository.findById(id);

        Season season = challengeOptional.orElseThrow(()
                -> new RuntimeException("Challenge not found with id: " + id));

        SeasonDto seasonDto = SeasonDto.builder()
                .name(season.getName())
                .img(season.getImg())
                .beginDate(season.getBeginDate())
                .endDate(season.getEndDate())
                .point(season.getPoint())
                .state(season.getState())
                .build();

        return seasonDto;
    }

    public List<UserRankingDto> findUserRankingById(Long challengeId) {
        Season season = seasonRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + challengeId));
        List<UserSeason> userSeasons = season.getUserSeasons();

        userSeasons.sort(Comparator
                .comparing(UserSeason::getUserSeasonPoint)
                .reversed()
                .thenComparing(u -> u.getUser().getId())
        );

        AtomicLong rank = new AtomicLong(1L);
        return userSeasons.stream()
                .map(entity -> UserRankingDto.builder()
                        .ranking(rank.getAndIncrement())
                        .point(entity.getUserSeasonPoint())
                        .name(entity.getUser().getName())
                        .build())
                .collect(Collectors.toList());
    }


}
