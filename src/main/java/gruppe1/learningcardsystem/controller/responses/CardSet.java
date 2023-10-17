package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.util.HashSet;

@Data
public class CardSet {

    private long id;

    private String name;
    private HashSet<Card> cards;

    public CardSet(){

    }

    public CardSet(Long id, String name) {
        this.id = id;
        this.name = name;
        this.cards = new HashSet<>(); {
        };
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

}
