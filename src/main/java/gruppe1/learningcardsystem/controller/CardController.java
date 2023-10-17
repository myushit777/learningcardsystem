package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gruppe1.learningcardsystem.controller.responses.Card;
import gruppe1.learningcardsystem.controller.requests.CardRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Optional<Card> getCard(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @PutMapping("/{id}")
    public Optional<Card> updateCard(@PathVariable Long id, @RequestBody CardRequest cardRequest) {
        Optional<Card> existingCard = cardService.getCardById(id);
        if (existingCard.isPresent()) {
            Card updatedCard = existingCard.get();
            updatedCard.setQuestion(cardRequest.getQuestion());
            updatedCard.setSuccessCount(cardRequest.getSuccessCount());
            // Aktualisiere andere Eigenschaften nach Bedarf.

            return cardService.updateCard(id, updatedCard);
        }
        return Optional.empty();
    }

    @DeleteMapping("/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
    }

}
