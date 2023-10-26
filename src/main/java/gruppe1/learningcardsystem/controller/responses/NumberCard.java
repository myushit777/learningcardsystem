package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

@Data
public class NumberCard<T extends Number> extends Card {

    //poah wir haben das so clever mit generics gemacht holy shit
    private T answer;

    public NumberCard() {
    }

//    public boolean checkUserAnswer(T userAnswer){
//        return userAnswer.equals(this.answer);
//    }
//
//}

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
