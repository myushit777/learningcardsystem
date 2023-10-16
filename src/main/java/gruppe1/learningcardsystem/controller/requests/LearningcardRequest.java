package gruppe1.learningcardsystem.controller.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LearningcardRequest {
    @JsonProperty("id")
    private Long id;
    private Integer successCount;
    private LocalDate creationDate;
    private LocalDate nextDueDate;
    private boolean isDraft;
    private String question;

    public LearningcardRequest(){

    }
}