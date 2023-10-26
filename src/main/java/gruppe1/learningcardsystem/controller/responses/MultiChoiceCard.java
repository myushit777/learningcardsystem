package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MultiChoiceCard extends Card {
    private String[] answer;
    private boolean[] answerCorrect;

    public MultiChoiceCard(){

    }


    //die Methode nimmt einen String entgegen, der Zahlen enthält, die mittels Kommata getrennt sind.
    public boolean checkUserAnswer(String userChosenAnswerNumbers) {
        if (userChosenAnswerNumbers == null || userChosenAnswerNumbers.isEmpty()) {
            return false;
        }

        //erstelle String Array aus der userAnswer die per Komma getrennt ist und splitte diese in Felder eines Arrays
        String[] answerNumberStrings = userChosenAnswerNumbers.split(",");
        //checkt ob eine Nummer aus dem Array dem Index von answerCorrect entspricht und prüft auf true und false
        for (String answerNumberString : answerNumberStrings) {
            try {
                int userAnswerNumber = Integer.parseInt(answerNumberString.trim());
                if (userAnswerNumber >= 1 && userAnswerNumber <= answer.length) {
                    if (!answerCorrect[userAnswerNumber - 1]) {
                        // Mindestens eine der ausgewählten Antworten ist nicht korrekt
                        return false;
                    }
                } else {
                    // Ungültige Antwortnummer
                    return false;
                }
            } catch (NumberFormatException e) {
                // Fehlerhafte Antwortnummer
                return false;
            }
        }
        // Alle ausgewählten Antworten sind korrekt
        return true;
    }

}
