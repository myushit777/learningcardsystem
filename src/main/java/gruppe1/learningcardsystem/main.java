package gruppe1.learningcardsystem;

import gruppe1.learningcardsystem.controller.responses.Card;
import gruppe1.learningcardsystem.controller.responses.CardSet;
import gruppe1.learningcardsystem.controller.responses.NumberCard;

public class main {
    public static void main(String[] args) {
        NumberCard card = new NumberCard<>();
        card.setAnswer(4);
        System.out.println(card.checkUserAnswer(4));
    }
}
