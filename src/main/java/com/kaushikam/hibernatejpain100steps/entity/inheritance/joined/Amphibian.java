package com.kaushikam.hibernatejpain100steps.entity.inheritance.joined;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Amphibian extends Animal {

    @Column(nullable = false)
    private AmphibianType type;

    public Amphibian() {}

    public Amphibian(String name, AmphibianType type) {
        super(name);
        this.type = type;
    }

    public AmphibianType getType() {
        return type;
    }

    public void setType(AmphibianType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Amphibian{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", type=" + type +
                '}';
    }

    public enum AmphibianType {
        CAECILIAN,
        SALAMANDER,
        NEWT,
        MUDPUPPY,
        FROG,
        TOAD
    }
}
