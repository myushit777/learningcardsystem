package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.controller.requests.CardsetRequest;
import gruppe1.learningcardsystem.controller.requests.LearningcardRequest;
import gruppe1.learningcardsystem.controller.responses.Cardset;
import gruppe1.learningcardsystem.controller.responses.Learningcard;
import gruppe1.learningcardsystem.services.CardSetService;
import gruppe1.learningcardsystem.services.LearningcardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardsetController {

    private final CardSetService cardSetService;
    private final LearningcardService learningcardService;

    public CardsetController(CardSetService cardSetService, LearningcardService learningcardService) {
        this.cardSetService = cardSetService;
        this.learningcardService = learningcardService;
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
        Learningcard learningcard = learningcardService.createLearningCard(
                request.getQuestion()
        );

        if (cardset != null) {
            // learningcard zu cardset hinzufügen
            cardset.addCard(learningcard.getId(), learningcard);
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

