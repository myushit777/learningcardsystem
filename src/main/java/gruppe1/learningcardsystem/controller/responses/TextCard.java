package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TextCard extends Card {

    private String answer;

    public TextCard(){

    }

    public TextCard(String answer) {
        this.answer = answer;
    }

    public TextCard(Long id, Integer successCount, LocalDateTime creationDate, LocalDateTime nextDueDate, boolean isDraft, String question, String answer) {
        super(id, successCount, creationDate, nextDueDate, isDraft, question);
        this.answer = answer;
    }

    public boolean checkUserAnswer(String userAnswer){
        return userAnswer.equalsIgnoreCase(this.answer);
    }
}
