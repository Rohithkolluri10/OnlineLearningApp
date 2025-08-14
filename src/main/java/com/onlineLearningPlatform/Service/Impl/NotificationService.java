package com.onlineLearningPlatform.Service.Impl;

import com.onlineLearningPlatform.events.Lessonevent;
import com.onlineLearningPlatform.model.Enrollment;
import com.onlineLearningPlatform.model.User;
import com.onlineLearningPlatform.repository.CourseRepository;
import com.onlineLearningPlatform.repository.EnrollmentRespository;
import com.onlineLearningPlatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NotificationService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final EnrollmentRespository enrollmentRespository;

    public NotificationService( SimpMessagingTemplate simpMessagingTemplate, EnrollmentRespository enrollmentRespository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.enrollmentRespository = enrollmentRespository;
    }

    @EventListener
    public void handlelessonaddedevent(Lessonevent lessonevent){
        log.info("Received Lessonevent for course: {}", lessonevent.getCourseId());
        List<Enrollment> enrollments = enrollmentRespository.findByCourseId(lessonevent.getCourseId());

        if (!enrollments.isEmpty()){
            for (Enrollment enrollment: enrollments){
                User user = enrollment.getUser();

                simpMessagingTemplate.convertAndSendToUser(user.getEmailAddress(),
                        "/queue/notifications",
                        "A New Lesson has been added to the Course you are Enrolled in ");
            }
        }

    }
}
