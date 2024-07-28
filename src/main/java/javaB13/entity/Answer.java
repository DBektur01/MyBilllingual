package javaB13.entity;
import jakarta.persistence.*;
import javaB13.enums.AnswerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "answers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_id_gen")
    @SequenceGenerator(name = "answer_id_gen", sequenceName = "answer_id_gen", allocationSize = 1, initialValue = 11)
    private Long id;
    @OneToOne(cascade = {REFRESH, MERGE, PERSIST, DETACH})
    private Question question;
    @ManyToOne(cascade = {REFRESH, MERGE, PERSIST, DETACH})
    private User user;
    @ManyToMany(cascade = {REFRESH, MERGE, PERSIST, DETACH})
    private List<Option> options;
    private String data;
    private float evaluatedScore;
    private int numberOfWords;
    private int numberOfPlays;
    @Enumerated(EnumType.STRING)
    private AnswerStatus answerStatus;
    @OneToMany(cascade = ALL)
    private List<File> files;
}
