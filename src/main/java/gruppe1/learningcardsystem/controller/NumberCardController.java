package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.services.NumberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gruppe1.learningcardsystem.controller.responses.NumberCard;
import gruppe1.learningcardsystem.controller.requests.CardRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/numbercards")
public class NumberCardController {

    @Autowired
    private NumberCardService numberCardService;

    @GetMapping
    public List<NumberCard> getAllNumberCards() {
        return numberCardService.getAllNumberCards();
    }

    @GetMapping("/{id}")
    public Optional<NumberCard> getNumberCard(@PathVariable Long id) {
        return numberCardService.getNumberCardById(id);
    }

    @PostMapping
    public NumberCard createNumberCard(@RequestBody CardRequest cardRequest) {
        NumberCard numberCard = new NumberCard();
        numberCard.setQuestion(cardRequest.getQuestion());
        numberCard.setAnswer(Double.parseDouble(cardRequest.getAnswer())); // Annahme: Antwort ist eine Dezimalzahl.

        return numberCardService.createNumberCard(numberCard);
    }

    @PutMapping("/{id}")
    public Optional<NumberCard> updateNumberCard(@PathVariable Long id, @RequestBody CardRequest cardRequest) {
        Optional<NumberCard> existingNumberCard = numberCardService.getNumberCardById(id);
        if (existingNumberCard.isPresent()) {
            NumberCard updatedNumberCard = existingNumberCard.get();
            updatedNumberCard.setQuestion(cardRequest.getQuestion());
            updatedNumberCard.setSuccessCount(cardRequest.getSuccessCount());
            updatedNumberCard.setAnswer(Double.parseDouble(cardRequest.getAnswer())); // Aktualisiere die Antwort.

            return numberCardService.updateNumberCard(id, updatedNumberCard);
        }
        return Optional.empty();
    }

    @DeleteMapping("/{id}")
    public void deleteNumberCard(@PathVariable Long id) {
        numberCardService.deleteNumberCard(id);
    }
}
