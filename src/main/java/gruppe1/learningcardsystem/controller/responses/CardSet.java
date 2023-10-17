package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CardSet {

    private static long nextCardId = 1;
    private long id;
    private String name;
    private List<Card> cards = new ArrayList<>();

    public CardSet(){

    }


    public void addCard(Card card){
        card.setId(nextCardId++);
        cards.add(card);
    }

}
