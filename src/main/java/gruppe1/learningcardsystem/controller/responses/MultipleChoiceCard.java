package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MultipleChoiceCard extends Card {
    private String[] answer;
    private boolean[] answerCorrect;

    public MultipleChoiceCard(){

    }

    public MultipleChoiceCard(
            Long id,
            Integer successCount,
            LocalDateTime creationDate,
            LocalDateTime nextDueDate,
            boolean isDraft,
            String question,
            String[] answer,
            boolean[] answerCorrect
    ) {
        super(id, successCount, creationDate, nextDueDate, isDraft, question);
        this.answer = answer;
        this.answerCorrect = answerCorrect;
    }

    public boolean checkUserAnswer(int userChooseAnswerNumber) {
        if (userChooseAnswerNumber >= 1 && userChooseAnswerNumber <= answer.length) {
            return answerCorrect[userChooseAnswerNumber - 1];
        }
        return false;
    }
}
