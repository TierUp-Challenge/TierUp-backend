package com.tierup.tierup.challenge.entity;

import com.tierup.tierup.season.entity.Season;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    long id;

    @Column(name = "challenge_name")
    String name;

    @Column(name = "challenge_description")
    String description;

    @Column(name = "challenge_input_description")
    String input_description;

    @Column(name = "challenge_output_description")
    String output_description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    Season season;

    @OneToMany(mappedBy = "challenge")
    List<Testcase> testcases;
}
