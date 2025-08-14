package com.onlineLearningPlatform.Service.Impl;

import com.onlineLearningPlatform.Mapper.CourseMapper;
import com.onlineLearningPlatform.Service.CourseService;
import com.onlineLearningPlatform.dto.*;
import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.Lesson;
import com.onlineLearningPlatform.repository.CourseRepository;
import com.onlineLearningPlatform.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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

    @Override
    public List<CourseDto> getallCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> courseDtos = CourseMapper.mapCourselisttoCourseDtolist(courses);
        return courseDtos;
    }

    @Override
    public CourseDto findById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException(
                        "Course not found with the mentioned Id"
                )
        );
        CourseDto courseDto = CourseMapper.mapcoursetocourseDto(course,new CourseDto());
        return courseDto;
    }

    @Override
    public CourseResponseDto createcourse(CourseCreateDto courseCreateDto) {
        Course course = new Course();
        course.setName(courseCreateDto.getName());
        course.setContents(courseCreateDto.getContents());
        course.setPrice(courseCreateDto.getPrice());
        course.setDescription(courseCreateDto.getDescription());

        courseRepository.save(course);

        List<Lesson> lessons = new ArrayList<>();

        if (courseCreateDto.getLessons() != null) {
            for (LessonDtoforCreateCourse lessonDtoforCreateCourse : courseCreateDto.getLessons()) {
                Lesson lesson = new Lesson();
                lesson.setTitle(lessonDtoforCreateCourse.getTitle());
                lesson.setDescription(lessonDtoforCreateCourse.getDescription());
                lesson.setVideoUrl(lessonDtoforCreateCourse.getVideoUrl());
                lesson.setCourse(course);
                lessons.add(lesson);
            }
        }
        lessonRespository.saveAll(lessons);

        Long defaultnumbers = lessonRespository.countByCourseId(course.getId());

        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setId(course.getId());
        courseResponseDto.setPrice(course.getPrice());
        courseResponseDto.setName(course.getName());
        courseResponseDto.setNumofDefaultlessonadded(defaultnumbers);
        return courseResponseDto;

    }
}
