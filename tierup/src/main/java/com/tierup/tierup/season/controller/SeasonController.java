package com.tierup.tierup.season.controller;

import com.tierup.tierup.season.dto.SeasonDto;
import com.tierup.tierup.season.service.SeasonService;
import com.tierup.tierup.challenge.dto.ChallengeResponse;
import com.tierup.tierup.challenge.service.ChallengeService;
import com.tierup.tierup.user.dto.UserRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonService seasonService;
    private final ChallengeService challengeService;

    @GetMapping("/seasons")
    List<SeasonDto> challengeDtos() {
        return seasonService.findAllSeasons();
    }

    @GetMapping("/seasons/{id}/challenges")
    List<ChallengeResponse> problems(@PathVariable("id") Long id) {
        return challengeService.findByChallengeId(id);
    }

    @GetMapping("/seasons/{id}")
    SeasonDto challengeInfo(@PathVariable("id") Long id) {
        return seasonService.findSeasonById(id);
    }

    @GetMapping("seasons/{id}/ranking")
    List<UserRankingDto> challengesRanking(@PathVariable("id") Long id) {
        return seasonService.findUserRankingById(id);
    }
}
