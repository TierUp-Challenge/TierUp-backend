package com.tierup.tierup.challenge.service;

import com.tierup.tierup.challenge.dto.ChallengeDto;
import com.tierup.tierup.challenge.entity.Challenge;
import com.tierup.tierup.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public ChallengeDto findChallengeById(Long id) {
        Optional<Challenge> challengeOptional = repository.findById(id);
        if (challengeOptional.isEmpty()) {
            // 오류 처리
            throw new RuntimeException("Challenge not found with id: " + id);
        }

        Challenge challenge = challengeOptional.get();

        ChallengeDto challengeDto = ChallengeDto.builder()
                .name(challenge.getName())
                .img(challenge.getImg())
                .beginDate(challenge.getBeginDate())
                .endDate(challenge.getEndDate())
                .point(challenge.getPoint())
                .state(challenge.getState())
                .build();

        return challengeDto;

    }
}
