package javaB13.services.impl;

import  javaB13.entity.File;
import  javaB13.entity.Question;
import  javaB13.enums.FileType;
import  javaB13.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Override
    public File createAudioFile(String fileUrl, Question question) {
        File file = new File();
        file.setFileType(FileType.AUDIO);
        file.setFileUrl(fileUrl);
        file.setQuestion(question);
        return file;
    }
}
