package com.onlineLearningPlatform.Service.Impl;

import com.onlineLearningPlatform.Service.LessonService;
import com.onlineLearningPlatform.dto.LessonDto;
import com.onlineLearningPlatform.dto.ResponseDto;
import com.onlineLearningPlatform.events.Lessonevent;
import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.Lesson;
import com.onlineLearningPlatform.repository.CourseRepository;
import com.onlineLearningPlatform.repository.LessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public ResponseDto addLesson(LessonDto lessonDto) {
        Course course = courseRepository.findById(lessonDto.getCourseId()).orElseThrow(
                () -> new RuntimeException("course not found with givenID"));
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDto.getTitle());
        lesson.setCourse(course);
        lesson.setVideoUrl(lessonDto.getVideourl());
        lesson.setDescription(lessonDto.getDescription());
        lesson.setLessonOrder(lessonRepository.findNextLessonOrderForCourse(course.getId()));
        lessonRepository.save(lesson);


        log.info("Lesson saved successfully. Publishing event for course: {}", course.getId());
        applicationEventPublisher.publishEvent(new Lessonevent(this,
                lesson.getId(),course.getId()

        ));
        log.info("Event Published");

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(lesson.getVideoUrl());
        responseDto.setSuccess(true);

        return  responseDto;
    }




}
