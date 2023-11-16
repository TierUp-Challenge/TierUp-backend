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

    public List<SeasonDto> findAllChallenges(){
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

    public SeasonDto findChallengeById(Long id) {
        Optional<Season> seasonOptional = seasonRepository.findById(id);

        Season season = seasonOptional.orElseThrow(()
                -> new RuntimeException("Season not found with id: " + id));

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

    public List<UserRankingDto> findUserRankingById(Long seasonId) {
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new RuntimeException("Season not found with id: " + seasonId));
        List<UserSeason> userSeasons = season.getUserSeasons();

        userSeasons.sort(Comparator
                .comparing(UserSeason::getSeasonPoint)
                .reversed()
                .thenComparing(u -> u.getUser().getId())
        );

        AtomicLong rank = new AtomicLong(1L);
        return userSeasons.stream()
                .map(entity -> UserRankingDto.builder()
                        .ranking(rank.getAndIncrement())
                        .point(entity.getSeasonPoint())
                        .name(entity.getUser().getName())
                        .build())
                .collect(Collectors.toList());
    }


}
