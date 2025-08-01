package com.onlineLearningPlatform.events;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class Lessonevent extends ApplicationEvent {
    public Lessonevent(Object source) {
        super(source);
    }

    public Lessonevent(Object source, Clock clock) {
        super(source, clock);
    }

    private Long lessonId;

    private Long courseId;

    private Long instructorId;


}
