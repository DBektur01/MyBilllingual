package javaB13.services;

import  javaB13.entity.File;
import  javaB13.entity.Question;

public interface FileService {
    File createAudioFile(String fileUrl, Question question);
}
