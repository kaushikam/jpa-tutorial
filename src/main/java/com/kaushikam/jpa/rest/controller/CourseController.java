package com.kaushikam.jpa.rest.controller;

import com.kaushikam.jpa.entity.Course;
import com.kaushikam.jpa.repository.CourseRepository;
import com.kaushikam.jpa.rest.dto.CourseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    private CourseRepository courseRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/courses/{id}")
    public CourseDTO getCourse(@PathVariable("id") Long id) {
        return new CourseDTO(courseRepository.findById(id));
    }

}
