package com.tierup.tierup.challenge.service;

import com.tierup.tierup.challenge.dto.ChallengeDto;
import com.tierup.tierup.challenge.entity.Challenge;
import com.tierup.tierup.challenge.repository.ChallengeRepository;
import com.tierup.tierup.user.dto.UserRankingDto;
import com.tierup.tierup.user.entity.UserChallenge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public List<ChallengeDto> findAllChallenges(){
        List<Challenge> challenges = challengeRepository.findAll();

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

        Challenge challenge = challengeOptional.orElseThrow(()
                -> new RuntimeException("Challenge not found with id: " + id));

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

    public List<UserRankingDto> findUserRankingById(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + challengeId));
        List<UserChallenge> userChallenges = challenge.getUserChallenges();

        userChallenges.sort(Comparator
                .comparing(UserChallenge::getChallengePoint)
                .reversed()
                .thenComparing(u -> u.getUser().getId())
        );

        AtomicLong rank = new AtomicLong(1L);
        return userChallenges.stream()
                .map(entity -> UserRankingDto.builder()
                        .ranking(rank.getAndIncrement())
                        .point(entity.getChallengePoint())
                        .name(entity.getUser().getName())
                        .build())
                .collect(Collectors.toList());
    }


}
