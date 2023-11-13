package com.tierup.tierup.challenge.service;

import com.tierup.tierup.challenge.dto.ChallengeDto;
import com.tierup.tierup.challenge.entity.Challenge;
import com.tierup.tierup.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository repository;

    public List<ChallengeDto> findAllChallenges(){
        List<Challenge> challenges = repository.findAll();

        return challenges.stream()
                .map(entity -> ChallengeDto.builder()
                        .name(entity.getName())
                        .img(entity.getImg())
                        .beginDate(entity.getBeginDate())
                        .endDate(entity.getEndDate())
                        .point(entity.getPoint())
                        .state(entity.getState()).build())
                .collect(Collectors.toList());
    }
}
