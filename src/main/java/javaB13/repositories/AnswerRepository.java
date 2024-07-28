package javaB13.repositories;

import  javaB13.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    Answer findAnswerByQuestionId(Long id);
}
