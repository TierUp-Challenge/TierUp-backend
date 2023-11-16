package com.tierup.tierup.challenge.service;

import com.tierup.tierup.challenge.dto.ChallengeResponse;
import com.tierup.tierup.challenge.entity.Challenge;
import com.tierup.tierup.challenge.mapper.ChallengeMapper;
import com.tierup.tierup.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public List<ChallengeResponse> findByChallengeId(Long id) {
        List<Challenge> challenges = challengeRepository.findAllBySeasonId(id);
        return challenges.stream().map(ChallengeMapper::toChallengeResponse).toList();
    }
}
