package gruppe1.learningcardsystem.controller.responses;

import lombok.Data;

@Data
public class NumberCard<T extends Number> extends Card {

    //poah wir haben das so clever mit generics gemacht holy shit
    private T answer;

    public NumberCard(){
    }

    public boolean checkUserAnswer(T userAnswer){
        return userAnswer.equals(this.answer);
    }

}
