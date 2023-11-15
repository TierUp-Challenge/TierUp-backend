package com.tierup.tierup.challenge.controller;

import com.tierup.tierup.challenge.dto.ChallengeDto;
import com.tierup.tierup.challenge.service.ChallengeService;
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
public class ChallengeController {

    private final ChallengeService challengeService;
    private final ProblemService problemService;

    @GetMapping("/challenges")
    List<ChallengeDto> challengeDtos() {
        return challengeService.findAllChallenges();
    }

    @GetMapping("/challenges/{id}/problems")
    List<ProblemResponse> problems(@PathVariable("id") Long id) {
        return problemService.findByChallengeId(id);
    }

    @GetMapping("/challenges/{id}")
    ChallengeDto challengeInfo(@PathVariable("id") Long id) {
        return challengeService.findChallengeById(id);
    }

    @GetMapping("challenges/{id}/ranking")
    List<UserRankingDto> challengesRanking(@PathVariable("id") Long id) {
        return challengeService.findUserRankingById(id);
    }
}
