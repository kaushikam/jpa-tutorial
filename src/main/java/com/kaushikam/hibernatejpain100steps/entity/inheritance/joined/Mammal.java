package com.kaushikam.hibernatejpain100steps.entity.inheritance.joined;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Mammal extends Animal {

    @Column(nullable = false)
    private Integer numberOfNipples;

    public Mammal() {}

    public Mammal(String name, Integer numberOfNipples) {
        super(name);
        this.numberOfNipples = numberOfNipples;
    }

    public Integer getNumberOfNipples() {
        return numberOfNipples;
    }

    public void setNumberOfNipples(Integer numberOfNipples) {
        this.numberOfNipples = numberOfNipples;
    }

    @Override
    public String toString() {
        return "Mammal{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", numberOfNipples=" + numberOfNipples +
                '}';
    }
}
