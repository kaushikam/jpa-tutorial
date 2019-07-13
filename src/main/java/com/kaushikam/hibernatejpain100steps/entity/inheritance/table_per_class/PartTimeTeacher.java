package com.kaushikam.hibernatejpain100steps.entity.inheritance.table_per_class;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PartTimeTeacher extends Teacher {

    private BigDecimal hourlyWages;

    public PartTimeTeacher() {}

    public PartTimeTeacher(String name, BigDecimal hourlyWages) {
        super(name);
        this.hourlyWages = hourlyWages;
    }

    public BigDecimal getHourlyWages() {
        return hourlyWages;
    }

    public void setHourlyWages(BigDecimal hourlyWages) {
        this.hourlyWages = hourlyWages;
    }

    @Override
    public String toString() {
        return "PartTimeTeacher{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", hourlyWages=" + hourlyWages +
                '}';
    }
}
