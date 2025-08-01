package com.onlineLearningPlatform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String videoUrl;

    @Column(name="lesson_order")
    private int lessonOrder;


}
