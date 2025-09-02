package com.onlineLearningPlatform.Service.Impl;

import com.onlineLearningPlatform.Service.EnrollmentService;
import com.onlineLearningPlatform.dto.EnrollmentDto;
import com.onlineLearningPlatform.dto.ResponseDto;
import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.Enrollment;
import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.repository.CourseRepository;
import com.onlineLearningPlatform.repository.EnrollmentRespository;
import com.onlineLearningPlatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRespository enrollmentRespository;

    private  final CourseRepository courseRepository;

    private final UserRepository userRepository;

    public EnrollmentServiceImpl(EnrollmentRespository enrollmentRespository, CourseRepository courseRepository, UserRepository userRepository) {
        this.enrollmentRespository = enrollmentRespository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ResponseDto enrolluser(EnrollmentDto enrollmentDto) {
        Course course = courseRepository.findById(enrollmentDto.getCourseId()).orElseThrow(
                () -> new RuntimeException("Course Id not found ")
        );
        User user = userRepository.findByEmailAddress(enrollmentDto.getEmailadress()).orElseThrow(
                () -> new RuntimeException("User Id not found for the same")
        );
            Enrollment enrollment = new Enrollment();
            enrollment.setCourse(course);
            enrollment.setUser(user);
            enrollmentRespository.save(enrollment);

            long resultvalue = enrollment.getId();
            String successmessage = String.valueOf(resultvalue);

            ResponseDto responseDto = new ResponseDto(
                      true,successmessage

            );
            return responseDto;
        }
    }
