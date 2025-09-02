package com.onlineLearningPlatform.Service.Impl;

import com.onlineLearningPlatform.Mapper.CourseMapper;
import com.onlineLearningPlatform.Service.CourseService;
import com.onlineLearningPlatform.config.Awsclient;
import com.onlineLearningPlatform.dto.*;
import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.Enrollment;
import com.onlineLearningPlatform.model.Lesson;
import com.onlineLearningPlatform.model.Review;
import com.onlineLearningPlatform.repository.CourseRepository;
import com.onlineLearningPlatform.repository.EnrollmentRespository;
import com.onlineLearningPlatform.repository.LessonRepository;
import com.onlineLearningPlatform.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.File;
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
    private EnrollmentRespository enrollmentRespository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private Awsclient awsclient;

    public CourseServiceImpl(CourseRepository courseRepository, LessonRepository lessonRespository, ApplicationEventPublisher applicationEventPublisher, Awsclient awsclient){
        this.courseRepository=courseRepository;
        this.lessonRespository=lessonRespository;
        this.applicationEventPublisher=applicationEventPublisher;
        this.awsclient=awsclient;
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

    @Override
    public ResponseDto updateCourse(CourseDto courseDto) {
        return courseRepository.findById(courseDto.getId())
                .map(
                        existingcourse -> {
                            existingcourse.setName(courseDto.getName());
                            existingcourse.setDescription(courseDto.getDescription());
                            existingcourse.setContents(courseDto.getContents());

                            courseRepository.save(existingcourse);

                            return new ResponseDto(true, "Course Has been updated");
                        })
                .orElseThrow(
                        () -> new RuntimeException(
                                "Course Not found with the Following Id:" + courseDto.getId()));
    }

    @Override
    public ResponseDto deleteCourse(Long id){
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course not found with the following id:" + id));
        List<Enrollment> enrollment = enrollmentRespository.findByCourseId(course.getId());
        enrollmentRespository.deleteAllInBatch(enrollment);
        courseRepository.delete(course);
        return new ResponseDto(true,"Course has been deleted");
    }

    @Override
    public List<CourseDto> searchCourse(String keyword) {
        List<Course> courses = courseRepository.findByDescriptionContainingIgnoreCase(keyword);
        List<CourseDto> courseDto = CourseMapper.mapCourselisttoCourseDtolist(courses);
        return courseDto;
    }

    @Override
    public ResponseDto addreviewtoCourse(ReviewDto reviewDto) {
        Course course = courseRepository.findById(reviewDto.getCourseId()).orElseThrow(
                () -> new RuntimeException("Course Not found by the following Id:" + reviewDto.getCourseId())
        );
        Review review = new Review();
        review.setComment(review.getComment());
        review.setCourse(course);
        reviewRepository.save(review);

        return new ResponseDto(true,"Review has been added to the course");
    }

    @Override
    public ResponseDto getReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new RuntimeException(
                        "Review has been found by the mentioned id: " + id
                )
        );
        return new ResponseDto(true,review.getComment());
    }

}

