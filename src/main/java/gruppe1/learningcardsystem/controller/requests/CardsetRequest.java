package gruppe1.learningcardsystem.controller.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import gruppe1.learningcardsystem.controller.responses.Learningcard;
import lombok.Data;

import java.util.HashMap;

@Data
public class CardsetRequest {

    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cards")
    private HashMap<Long, Learningcard> cards;

    public CardsetRequest() {

    }

    public void addCard(Long id, Learningcard learningcard){
        cards.put(id, learningcard);
    }
}
