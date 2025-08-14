package com.onlineLearningPlatform.events;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class Lessonevent extends ApplicationEvent {
    public Lessonevent(Object source , Long lessonId , Long courseId) {
        super(source);
        this.lessonId = lessonId;
        this.courseId = courseId;
    }
    private Long lessonId;

    private Long courseId;
}
