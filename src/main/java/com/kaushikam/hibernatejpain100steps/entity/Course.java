package com.kaushikam.hibernatejpain100steps.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries( value =
        {
                @NamedQuery(name = "findAll", query = "SELECT c FROM Course c")
        }
)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime startedDate;

    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.LAZY,
            // MERGE needed (not ALL) otherwise on fetching it will duplicate added entries
            cascade = {CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();

    public Course() {}

    public Course(String name, LocalDateTime startedDate) {
        this.name = name;
        this.startedDate = startedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setStartedDate(LocalDateTime startedDate) {
        this.startedDate = startedDate;
    }

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        reviews.forEach(this::addReview);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        review.setCourse(this);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
        review.setCourse(null);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
