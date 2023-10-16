package gruppe1.learningcardsystem;

import gruppe1.learningcardsystem.controller.responses.MultiChoiceCard;
import gruppe1.learningcardsystem.controller.responses.MultipleChoiceCard;
import gruppe1.learningcardsystem.controller.responses.Numbercard;


public class main {
    public static void main(String[] args) {
        Numbercard numbercard = new Numbercard<>();
        System.out.println(numbercard.getId());

    }
}
