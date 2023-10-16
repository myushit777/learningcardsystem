package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MultiChoiceCard extends Learningcard {
    private String[] answer;
    private boolean[] answerCorrect;

    public MultiChoiceCard(
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

    public boolean checkUserAnswer(int[] userChosenAnswerNumbers) {
        if (userChosenAnswerNumbers == null) {
            return false;
        }

        for (int userAnswerNumber : userChosenAnswerNumbers) {
            if (userAnswerNumber >= 1 && userAnswerNumber <= answer.length) {
                if (!answerCorrect[userAnswerNumber - 1]) {
                    // Mindestens eine der ausgewählten Antworten ist nicht korrekt
                    return false;
                }
            } else {
                // Ungültige Antwortnummer
                return false;
            }
        }
        // Alle ausgewählten Antworten sind korrekt
        return true;
    }
}
