package javaB13.entity;

import jakarta.persistence.*;
import javaB13.enums.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_id_gen")
    @SequenceGenerator(name = "result_id_gen", sequenceName = "result_id_seq", allocationSize = 1, initialValue = 5)
    private Long id;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST})
    private Test test;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST})
    private User user;
    @Enumerated(value = EnumType.STRING)
    private ResultStatus status;
    private float score;
    private LocalDateTime dateOfSubmission;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;
}
