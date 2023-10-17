package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.controller.requests.CardRequest;
import gruppe1.learningcardsystem.controller.responses.*;
import gruppe1.learningcardsystem.services.CardService;
import gruppe1.learningcardsystem.services.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gruppe1.learningcardsystem.controller.requests.CardsetRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cardsets")
public class CardSetController {

    private CardSetService cardSetService;
    private CardService cardService;

    @Autowired
    public CardSetController(CardSetService cardSetService, CardService cardService){
        this.cardSetService = cardSetService;
        this.cardService = cardService;
    }

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


            return cardSetService.updateCardSet(id, updatedCardSet);
        }
        return Optional.empty();
    }

    @DeleteMapping("/{id}")
    public void deleteCardset(@PathVariable Long id) {
        cardSetService.deleteCardSet(id);
    }


    @PostMapping("/{cardSetId}/addNumber")
    public NumberCard addNumbercardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest cardRequest) {
        NumberCard numberCard = new NumberCard<>();
        numberCard.setQuestion(cardRequest.getQuestion());
        numberCard.setAnswer(Double.parseDouble(cardRequest.getAnswer())); // Annahme: Antwort ist eine Dezimalzahl.
        cardService.createCard(numberCard);
        cardSetService.addCardToCardSet(cardSetId,numberCard);

        return numberCard;
    }

    @PostMapping("/{cardSetId}/addText")
    public TextCard addTextcardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest cardRequest) {
        TextCard textCard = new TextCard();
        textCard.setQuestion(cardRequest.getQuestion());
        textCard.setAnswer(cardRequest.getAnswer()); // Die Antwort ist ein Text.
        cardService.createCard(textCard);
        cardSetService.addCardToCardSet(cardSetId, textCard);

        return textCard;
    }

    @PostMapping("/{cardSetId}/addMultipleChoice")
    public MultipleChoiceCard addMultiplechoicecardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest cardRequest) {
        MultipleChoiceCard multipleChoiceCard = new MultipleChoiceCard();
        multipleChoiceCard.setQuestion(cardRequest.getQuestion());
        multipleChoiceCard.setAnswer(cardRequest.getChoices());
        multipleChoiceCard.setAnswerCorrect(cardRequest.getAnswerCorrect());
        cardService.createCard(multipleChoiceCard);
        cardSetService.addCardToCardSet(cardSetId,multipleChoiceCard);

        return multipleChoiceCard;
    }
}
