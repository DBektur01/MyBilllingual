package javaB13.services;
import javaB13.dto.responses.SimpleResponse;
import org.springframework.stereotype.Service;

@Service
public interface ScoreSender {
    SimpleResponse scoreSender(Long resultId, String link);
}
