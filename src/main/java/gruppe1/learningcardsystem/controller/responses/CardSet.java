package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;


@Data
public class CardSet {

    private long id;
    private String name;
    private long numberOfCards = 1;
    private LinkedHashMap<Long, Card> cards;

    public CardSet(){
    this.id = id;
    this.name = name;
    this.cards = new LinkedHashMap();
    }


    /*füge eine Card zu einem Cardset hinzu.
     eine Karte kann nicht außerhalb eines Kastens existieren, weil es aus meiner Sicht keinen Sinn ergibt*/
    public void addCard(Card card){
        card.setId(numberOfCards);
        cards.put(numberOfCards,card);
        numberOfCards++;
    }

    //löscht eine Card aus einem Cardset
    public void deleteCard(Long cardId) {
        cards.remove(cardId);
    }
}
