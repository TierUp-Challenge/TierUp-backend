package com.tierup.tierup.problem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Testcase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "testcase_input")
    String input;

    @Column(name = "testcase_output")
    String output;

    @ManyToOne(fetch = FetchType.LAZY)
    Problem problem;
}
