package gruppe1.learningcardsystem;

import gruppe1.learningcardsystem.controller.responses.Card;
import gruppe1.learningcardsystem.controller.responses.CardSet;

public class main {
    public static void main(String[] args) {
        CardSet cardSet =new CardSet();
        cardSet.setId(1);
        Card card = new Card() {
        };
        cardSet.addCard(card);
        cardSet.addCard(card);
        System.out.println(cardSet.getCards());
    }
}
