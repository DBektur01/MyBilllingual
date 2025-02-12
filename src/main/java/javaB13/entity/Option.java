package javaB13.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "options")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_id_gen")
    @SequenceGenerator(name = "option_id_gen", sequenceName = "option_id_gen", allocationSize = 1, initialValue = 21)
    private Long id;
    private String title;
    private Boolean isCorrect;
    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private Question question;
    private String fileUrl;
    private Integer optionOrder;
}
