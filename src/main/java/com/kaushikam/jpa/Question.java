package com.kaushikam.jpa;

import com.kaushikam.jpa.entity.Answer;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString(exclude = "answers")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = 0L;

    @Column(nullable = false)
    private String question;

    @OneToMany(mappedBy = "question", orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Answer> answers = new ArrayList<>();

    public Question(String question) {
        this.question = question;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.setQuestion(null);
    }

    public void setAnswers(List<Answer> answers) {
        answers.forEach(this::addAnswer);
    }
}
