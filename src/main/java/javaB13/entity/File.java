package javaB13.entity;

import jakarta.persistence.*;
import javaB13.enums.FileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_id_gen")
    @SequenceGenerator(name = "file_id_gen", sequenceName = "file_id_gen", allocationSize = 1, initialValue = 5)
    private Long id;
    private String fileUrl;
    @ManyToOne(cascade = {REFRESH, PERSIST, MERGE, DETACH})
    private Question question;
    @Enumerated(value = EnumType.STRING)
    private FileType fileType;

    public File(FileType fileType, String fileUrl, Question question) {
        this.fileType = fileType;
        this.fileUrl = fileUrl;
        this.question = question;
    }
}
