package com.tierup.tierup.problem.entity;

import com.tierup.tierup.challenge.entity.Challenge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "problem_name")
    String name;

    @Column(name = "problem_description")
    String description;

    @Column(name = "problem_input_description")
    String input_description;

    @Column(name = "problem_output_description")
    String output_description;

    @ManyToOne(fetch = FetchType.LAZY)
    Challenge challenge;

    @OneToMany(mappedBy = "problem")
    List<Testcase> testcases;
}
