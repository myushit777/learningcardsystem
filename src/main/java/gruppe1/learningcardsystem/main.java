package gruppe1.learningcardsystem;

import gruppe1.learningcardsystem.controller.responses.Card;
import gruppe1.learningcardsystem.controller.responses.CardSet;
import gruppe1.learningcardsystem.controller.responses.MultipleChoiceCard;
import gruppe1.learningcardsystem.controller.responses.NumberCard;

public class main {
    public static void main(String[] args) {
        String [] answer = {"1, Berlion","2","3"};
        boolean [] correct = {true,true,false};
        MultipleChoiceCard mcc = new MultipleChoiceCard();
        mcc.setAnswer(answer);
        mcc.setAnswerCorrect(correct);
        System.out.println(mcc.checkUserAnswer("3"));
    }
}
