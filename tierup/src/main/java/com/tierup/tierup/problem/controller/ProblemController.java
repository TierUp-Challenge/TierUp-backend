package com.tierup.tierup.problem.controller;

import com.tierup.tierup.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

}
