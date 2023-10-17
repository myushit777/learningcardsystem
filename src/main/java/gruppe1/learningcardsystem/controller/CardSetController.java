package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.controller.responses.Card;
import gruppe1.learningcardsystem.services.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gruppe1.learningcardsystem.controller.responses.CardSet;
import gruppe1.learningcardsystem.controller.requests.CardsetRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cardsets")
public class CardSetController {

    @Autowired
    private CardSetService cardSetService;

    @GetMapping
    public List<CardSet> getAllCardsets() {
        return cardSetService.getAllCardSets();
    }

    @GetMapping("/{id}")
    public Optional<CardSet> getCardset(@PathVariable Long id) {
        return cardSetService.getCardSetById(id);
    }

    @PostMapping
    public CardSet createCardset(@RequestBody CardsetRequest cardsetRequest) {
        CardSet cardset = new CardSet();
        cardset.setName(cardsetRequest.getName());

        return cardSetService.createCardSet(cardset);
    }

    @PutMapping("/{id}")
    public Optional<CardSet> updateCardset(@PathVariable Long id, @RequestBody CardsetRequest cardsetRequest) {
        Optional<CardSet> existingCardset = cardSetService.getCardSetById(id);
        if (existingCardset.isPresent()) {
            CardSet updatedCardSet = existingCardset.get();
            updatedCardSet.setName(cardsetRequest.getName());
            // Aktualisiere andere Eigenschaften nach Bedarf.

            return cardSetService.updateCardSet(id, updatedCardSet);
        }
        return Optional.empty();
    }

    @DeleteMapping("/{id}")
    public void deleteCardset(@PathVariable Long id) {
        cardSetService.deleteCardSet(id);
    }

/*    @PostMapping("/{cardSetId}/addCards")
    public void addCardsToCardSet(@PathVariable Long cardSetId, @RequestBody Card card) {
        cardSetService.addCardToCardSet(cardSetId, card);
    }*/
}
