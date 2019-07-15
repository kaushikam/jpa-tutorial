package com.kaushikam.jpa.rest.dto;

import com.kaushikam.jpa.entity.Course;

public class CourseDTO {
    private Long id;
    private String name;

    public CourseDTO(Course course) {
        this(course.getId(), course.getName());
    }

    public CourseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
