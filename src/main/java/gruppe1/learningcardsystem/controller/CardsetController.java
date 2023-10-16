package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.controller.requests.CardsetRequest;
import gruppe1.learningcardsystem.controller.requests.LearningcardRequest;
import gruppe1.learningcardsystem.controller.requests.NumbercardRequest;
import gruppe1.learningcardsystem.controller.responses.Cardset;
import gruppe1.learningcardsystem.controller.responses.Learningcard;
import gruppe1.learningcardsystem.controller.responses.Numbercard;
import gruppe1.learningcardsystem.services.CardSetService;
import gruppe1.learningcardsystem.services.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardsetController {

    private final CardSetService cardSetService;
    private final CardService cardService;

    public CardsetController(CardSetService cardSetService, CardService cardService) {
        this.cardSetService = cardSetService;
        this.cardService = cardService;
    }


    @RequestMapping(method = RequestMethod.POST, path = "/cardsets")
    public Cardset createCardset(@RequestBody CardsetRequest request) {
        Cardset cardset = cardSetService.createCardset(
                request.getName()
        );
        return cardset;
    }

    //GET um mit Id nach Cardset zu suchen
    @GetMapping("/cardsets/{setId}")
    public Cardset getCardset(@PathVariable("setId") Long id) {
        return cardSetService.getCardsetById(id);
    }

    //GET alle Cardsets
    @GetMapping(path = "/cardsets")
    public List<Cardset> getAllCardsets() {
        return cardSetService.getAllCardsets();
    }

    //Learningcard einem Cardset per Id hinzufügen
    @PostMapping("/cardsets/{setId}/add")
    public Cardset addLearningcardToCardset(@PathVariable("setId") Long setId, @RequestBody LearningcardRequest request) {
        //Cardset per id suchen
        Cardset cardset = cardSetService.getCardsetById(setId);
        Learningcard learningcard = cardService.createLearningCard(
                request.getQuestion()
        );

        if (cardset != null) {
            // learningcard zu cardset hinzufügen
            cardset.addCard(learningcard.getId(), learningcard);
           cardSetService.updateCardset(cardset);
        }
       return cardset;
    }

    @PostMapping("/cardsets/{setId}/addNumber")
    public Cardset addLearningcardToCardset(@PathVariable("setId") Long setId, @RequestBody NumbercardRequest request) {
        //Cardset per id suchen
        Cardset cardset = cardSetService.getCardsetById(setId);
        Numbercard number = (Numbercard)cardService.createLearningCard(
                request.getQuestion()
        );

        if (cardset != null) {
            // learningcard zu cardset hinzufügen
            cardset.addCard(number.getId(), number);
            cardSetService.updateCardset(cardset);
        }
        return cardset;
    }

    @DeleteMapping("/cardsets/{cardsetId}/{learningcardId}")
    public void removeLearningcardFromCardset(
            @PathVariable("cardsetId") Long cardsetId,
            @PathVariable("learningcardId") Long learningcardId
    ) {
        Cardset cardset = cardSetService.getCardsetById(cardsetId);

        if (cardset != null) {
            cardset.removeCard(learningcardId);
                cardSetService.updateCardset(cardset);
        }
    }
}

