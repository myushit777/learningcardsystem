package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Card createCard(@RequestBody CardRequest cardRequest) {
        Card card = new Card();
        card.setQuestion(cardRequest.getQuestion());
        //success counter ist 0 daher unnötig und answer wird erst in unterklassen implementiert

        return cardService.createCard(card);
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

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
