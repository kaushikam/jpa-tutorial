package com.kaushikam.hibernatejpain100steps.entity.inheritance.mapped_super_class;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Book extends Publication {

    @Column(nullable = false)
    private Integer numberOfPages;

    public Book(String title, Integer numberOfPages) {
        super(title);
        this.numberOfPages = numberOfPages;
    }

    public Book() {}

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
