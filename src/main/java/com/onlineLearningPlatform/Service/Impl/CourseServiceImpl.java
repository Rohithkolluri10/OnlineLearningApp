package com.onlineLearningPlatform.Service.Impl;

import com.onlineLearningPlatform.Service.CourseService;
import com.onlineLearningPlatform.dto.LessonDetails;
import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.Lesson;
import com.onlineLearningPlatform.repository.CourseRepository;
import com.onlineLearningPlatform.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRespository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public CourseServiceImpl(CourseRepository courseRepository, LessonRepository lessonRespository, ApplicationEventPublisher applicationEventPublisher){
        this.courseRepository=courseRepository;
        this.lessonRespository=lessonRespository;
        this.applicationEventPublisher=applicationEventPublisher;
    }

    public boolean addLesson(Long courseId, LessonDetails lessonDetails){
        Course existingcourse = courseRepository.findById(courseId).orElseThrow(
                () -> new RuntimeException("Course does not exists. Create a new course" + courseId));
        if (lessonDetails.getId() != null){
            updatenewLesson(lessonDetails);
        }
        Optional<Integer> maxOrderOptional = lessonRespository.findTopByCourseOrderByLessonOrderDesc(existingcourse);
        int nextOrder = maxOrderOptional.map(order -> order + 1).orElse(1);

        Lesson newLesson = new Lesson();
        newLesson.setId(lessonDetails.getId());
        newLesson.setDescription(lessonDetails.getDescription());
        newLesson.setCourse(existingcourse);
        newLesson.setTitle(lessonDetails.getTitle());
        newLesson.setVideoUrl(lessonDetails.getVideoUrl());
        newLesson.setLessonOrder(nextOrder);

        if (existingcourse.getLessons() == null){
            existingcourse.setLessons(new ArrayList<>());
        }
        lessonRespository.save(newLesson);

        //applicationEventPublisher.publishEvent();


        return true;

    }

    private void updatenewLesson(LessonDetails lessonDetails) {
    }

    private void addNewlesson(LessonDetails lessonDetails) {

    }
}
