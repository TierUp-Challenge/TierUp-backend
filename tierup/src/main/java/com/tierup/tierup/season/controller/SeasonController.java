package com.tierup.tierup.season.controller;

import com.tierup.tierup.season.dto.SeasonDto;
import com.tierup.tierup.season.service.SeasonService;
import com.tierup.tierup.problem.dto.ProblemResponse;
import com.tierup.tierup.problem.service.ProblemService;
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
    private final ProblemService problemService;

    @GetMapping("/seasons")
    List<SeasonDto> seasonDtos() {
        return seasonService.findAllChallenges();
    }

    @GetMapping("/seasons/{id}/challenges")
    List<ProblemResponse> challenges(@PathVariable("id") Long id) {
        return problemService.findByChallengeId(id);
    }

    @GetMapping("/seasons/{id}")
    SeasonDto seasonInfo(@PathVariable("id") Long id) {
        return seasonService.findChallengeById(id);
    }

    @GetMapping("seasons/{id}/ranking")
    List<UserRankingDto> seasonRanking(@PathVariable("id") Long id) {
        return seasonService.findUserRankingById(id);
    }
}
