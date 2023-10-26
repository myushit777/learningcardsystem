package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

@Data
public class NumberCard<T extends Number> extends Card {

    //wir verwenden hier generics da eine Einteilung von int, double und long Cards nicht nötig ist
    private T answer;

    public NumberCard() {
    }

    //da wir nur einen String als Antwort in unserer Request übergeben wird diese hier wieder zu einer Zahl geparst
    public boolean checkUserAnswer(String userAnswer) {
        try {
            if (answer instanceof Double) {
                Double userAnswerDouble = Double.parseDouble(userAnswer);
                return userAnswerDouble.equals(answer);
            } else if (answer instanceof Long) {
                Long userAnswerLong = Long.parseLong(userAnswer);
                return userAnswerLong.equals(answer);
            } else if (answer instanceof Integer) {
                Integer userAnswerInt = Integer.parseInt(userAnswer);
                return userAnswerInt.equals(answer);
            } else {
                return false; // Unbekannter Datentyp
            }
        } catch (NumberFormatException e) {
            return false; // Ungültige Benutzereingabe
        }
    }
}
