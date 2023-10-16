package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Textcard extends Learningcard{

    private String answer;

    public Textcard(String answer) {
        this.answer = answer;
    }

    public Textcard(Long id, Integer successCount, LocalDateTime creationDate, LocalDateTime nextDueDate, boolean isDraft, String question, String answer) {
        super(id, successCount, creationDate, nextDueDate, isDraft, question);
        this.answer = answer;
    }

    public boolean checkUserAnswer(String userAnswer){
        return userAnswer.equalsIgnoreCase(this.answer);
    }
}
