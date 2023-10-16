package gruppe1.learningcardsystem.controller.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class Cardset {

    private long id;

    private String name;
    private HashMap<Long, Learningcard> cards;

    public Cardset(){

    }

    public Cardset(Long id, String name) {
        this.id = id;
        this.name = name;
        this.cards = new HashMap<>();
    }

    public void addCard(Long id, Learningcard learningcard){
        cards.put(id, learningcard);
    }

    public void removeCard(Long id){ cards.remove(id);}
}
