package com.kaushikam.hibernatejpain100steps.entity.inheritance.mapped_super_class;

import javax.persistence.*;

@MappedSuperclass
public abstract class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    public Publication(String title) {
        this.title = title;
    }

    public Publication() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
