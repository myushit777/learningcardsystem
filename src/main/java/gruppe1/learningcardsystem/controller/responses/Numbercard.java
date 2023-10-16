package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Numbercard <T extends Number> extends Learningcard{

    //poah wir haben das so clever mit generics gemacht holy shit
    private T answer;

    public Numbercard(){
    }

    public boolean checkUserAnswer(T userAnswer){
        return userAnswer.equals(this.answer);
    }

    @Override
    public String toString() {
        return "Numbercard{" +
                "answer=" + answer +
                '}';
    }
}
