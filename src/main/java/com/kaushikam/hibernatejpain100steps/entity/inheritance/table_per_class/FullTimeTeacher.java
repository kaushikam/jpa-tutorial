package com.kaushikam.hibernatejpain100steps.entity.inheritance.table_per_class;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class FullTimeTeacher extends Teacher {

    private BigDecimal salary;

    public FullTimeTeacher() {}

    public FullTimeTeacher(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                "id=" + getId() +
                ", name="  + getName() +
                ", salary=" + salary +
                '}';
    }
}
