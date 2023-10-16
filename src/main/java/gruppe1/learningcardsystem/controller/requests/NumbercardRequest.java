package gruppe1.learningcardsystem.controller.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import gruppe1.learningcardsystem.controller.responses.Learningcard;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NumbercardRequest <T extends Number> extends Learningcard {

    //poah wir haben das so clever mit generics gemacht holy shit
    @JsonProperty("answer")
    private T answer;

    public NumbercardRequest() {

    }
    public boolean checkUserAnswer(T userAnswer){
        return userAnswer.equals(this.answer);
    }
}
