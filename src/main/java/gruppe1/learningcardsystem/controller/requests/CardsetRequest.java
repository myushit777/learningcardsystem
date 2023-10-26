package gruppe1.learningcardsystem.controller.requests;

import lombok.Data;

//diese Klasse hätte man auch weglassen können aber es wurde bewusst Card von Cardset getrennt
@Data
public class CardsetRequest {

    //beinhaltet den Namen des Cardsets
    private String name;

}
