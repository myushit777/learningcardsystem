package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.services.MultipleChoiceCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gruppe1.learningcardsystem.controller.responses.MultipleChoiceCard;
import gruppe1.learningcardsystem.controller.requests.CardRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/multiplechoicecards")
public class MultipleChoiceCardController {

    @Autowired
    private MultipleChoiceCardService multipleChoiceCardService;

    @GetMapping
    public List<MultipleChoiceCard> getAllMultipleChoiceCards() {
        return multipleChoiceCardService.getAllMultipleChoiceCards();
    }

    @GetMapping("/{id}")
    public Optional<MultipleChoiceCard> getMultipleChoiceCard(@PathVariable Long id) {
        return multipleChoiceCardService.getMultipleChoiceCardById(id);
    }

    @PostMapping
    public MultipleChoiceCard createMultipleChoiceCard(@RequestBody CardRequest cardRequest) {
        MultipleChoiceCard multipleChoiceCard = new MultipleChoiceCard();
        multipleChoiceCard.setQuestion(cardRequest.getQuestion());
        //multipleChoiceCard.setAnswer(cardRequest.getAnswer()); // Annahme: Antwort ist eine Zeichenkette.
        //multipleChoiceCard.setChoices(cardRequest.getChoices());
        multipleChoiceCard.setAnswerCorrect(cardRequest.getAnswerCorrect());

        return multipleChoiceCardService.createMultipleChoiceCard(multipleChoiceCard);
    }

/*    @PutMapping("/{id}")
    public Optional<MultipleChoiceCard> updateMultipleChoiceCard(@PathVariable Long id, @RequestBody CardRequest cardRequest) {
        Optional<MultipleChoiceCard> existingMultipleChoiceCard = multipleChoiceCardService.getMultipleChoiceCardById(id);
        if (existingMultipleChoiceCard.isPresent()) {
            //MultipleChoiceCard updatedMultipleChoiceCard = existingMultiple
        }
    }*/
}