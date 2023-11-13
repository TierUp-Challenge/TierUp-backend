package com.tierup.tierup.problem.service;

import com.tierup.tierup.problem.dto.ProblemResponse;
import com.tierup.tierup.problem.entity.Problem;
import com.tierup.tierup.problem.mapper.ProblemMapper;
import com.tierup.tierup.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public List<ProblemResponse> findByChallengeId(Long id) {
        List<Problem> problems = problemRepository.findAllByChallengeId(id);
        return problems.stream().map(ProblemMapper::toProblemResponse).toList();
    }
}
