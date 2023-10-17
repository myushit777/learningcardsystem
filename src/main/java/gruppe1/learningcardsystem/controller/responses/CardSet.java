package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CardSet {

    private long id;
    private String name;
    private List<Card> cards;
    private long numberOfCards = 0;

    public CardSet(){
    this.id = id;
    this.name = name;
    this.cards = new ArrayList<>();
    }


    public void addCard(Card card){
        numberOfCards++;
        card.setId(numberOfCards);
        cards.add(card);
    }

    public void deleteCard(Long cardId) {
        cards.remove(cardId.intValue());
    }
}
