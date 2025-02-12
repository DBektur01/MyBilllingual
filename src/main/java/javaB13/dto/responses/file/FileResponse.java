package javaB13.dto.responses.file;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import javaB13.enums.FileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class FileResponse {
    private Long id;
    @Enumerated(EnumType.STRING)
    private FileType fileType;
    private String fileUrl;
    private Long questionId;

}
