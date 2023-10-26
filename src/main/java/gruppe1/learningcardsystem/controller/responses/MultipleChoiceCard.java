package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MultipleChoiceCard extends Card{
    private String[] answer;
    private boolean[] answerCorrect;

    public MultipleChoiceCard(){

    }

    public boolean checkUserAnswer(String userChosenAnswer) {
        try {
            int index = Integer.parseInt(userChosenAnswer) - 1; // Der Index beginnt bei 0, deshalb subtrahieren wir 1.
            if (index >= 0 && index < answerCorrect.length) {
                return answerCorrect[index];
            }
        } catch (NumberFormatException e) {
            // Wenn die Umwandlung in einen Index fehlschlägt, wird false zurückgegeben.
        }
        return false; // Wenn der Index ungültig ist oder ein Fehler auftritt, wird false zurückgegeben.
    }

}
