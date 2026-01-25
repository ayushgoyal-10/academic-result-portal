package com.result_portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subjectName;

    @Column(nullable = false)
    private String subjectCode;

    @Column(nullable = false)
    private double obtainedMarks;
    @Column(nullable = false)
    private double maxMarks;

    @ManyToOne
    @JoinColumn(name = "result_id", nullable = false)
    private Result result;
}
