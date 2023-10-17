package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.controller.requests.CardRequest;
import gruppe1.learningcardsystem.controller.responses.*;
import gruppe1.learningcardsystem.services.CardService;
import gruppe1.learningcardsystem.services.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gruppe1.learningcardsystem.controller.requests.CardsetRequest;

import java.util.List;

@RestController
@RequestMapping("/cardsets")
public class CardSetController {

    private CardSetService cardSetService;

    private CardService cardService;

    @Autowired
    public CardSetController(CardSetService cardSetService, CardService cardService) {
        this.cardSetService = cardSetService;
        this.cardService = cardService;
    }

    //ALLE CARDSETS
    @GetMapping
    public List<CardSet> getAllCardsets() {
        return cardSetService.getCardSetList();
    }

    //CARDSETS PER ID SUCHEN
    @GetMapping("/{id}")
    public CardSet getCardSetbyId(@PathVariable Long id) {
        return cardSetService.getCardSetbyId(id);
    }

    //ADD CARDSET
    @PostMapping
    public CardSet addCardSet(@RequestBody CardsetRequest request) {
        CardSet cardSet = new CardSet();
        cardSet.setName(request.getName());

        return cardSetService.createCardSet(cardSet);
    }

    //ADD NUMBERCARD DOUBLE TO CARDSET
    @PostMapping("/{cardSetId}/addDouble")
    public Card addDoubleCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        NumberCard numberCard = new NumberCard<>();
        numberCard.setQuestion(request.getQuestion());
        numberCard.setAnswer(Double.parseDouble(request.getAnswer())); //DOUBLE
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), numberCard);
        return numberCard;
    }

    //ADD NUMBERCARD INT TO CARDSET
    @PostMapping("/{cardSetId}/addInt")
    public Card addIntCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        NumberCard numberCard = new NumberCard<>();
        numberCard.setQuestion(request.getQuestion());
        numberCard.setAnswer(Integer.parseInt(request.getAnswer())); //INT
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), numberCard);
        return numberCard;
    }

    //ADD NUMBERCARD LONG TO CARDSET
    @PostMapping("/{cardSetId}/addLong")
    public Card addLongCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        NumberCard numberCard = new NumberCard<>();
        numberCard.setQuestion(request.getQuestion());
        numberCard.setAnswer(Long.parseLong(request.getAnswer())); //LONG
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), numberCard);
        return numberCard;
    }

    //ADD TEXTCARD STRING TO CARDSET
    @PostMapping("/{cardSetId}/addT")
    public Card addTextCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        TextCard textCard = new TextCard();
        textCard.setQuestion(request.getQuestion());
        textCard.setAnswer(request.getAnswer()); //Double sein Vater
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), textCard);
        return textCard;
    }

    //ADD MULTIPLECHOICECARD TO CARDSET
    @PostMapping("/{cardSetId}/addMultiple")
    public Card addMutlipleChoiceCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        MultipleChoiceCard multipleChoiceCard = new MultipleChoiceCard();
        multipleChoiceCard.setQuestion(request.getQuestion());
        multipleChoiceCard.setAnswer(request.getChoices());
        multipleChoiceCard.setAnswerCorrect(request.getAnswerCorrect());
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), multipleChoiceCard);
        return multipleChoiceCard;
    }

    //ADD MULTICARD TO CARDSET
    @PostMapping("/{cardSetId}/addMulti")
    public Card addMultiChoiceCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        MultiChoiceCard multiChoiceCard = new MultiChoiceCard();
        multiChoiceCard.setQuestion(request.getQuestion());
        multiChoiceCard.setAnswer(request.getChoices());
        multiChoiceCard.setAnswerCorrect(request.getAnswerCorrect());
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), multiChoiceCard);

        return multiChoiceCard;
    }

    //DELETE CARD FROM SET
    @DeleteMapping("/{cardSetId}/{cardId}")
    public void deleteCardFromCardSet(@PathVariable Long cardSetId, @PathVariable Long cardId) {
        cardService.deleteCardFromCardSet(cardSetService.getCardSetbyId(cardSetId), --cardId);
    }

    //DELETE CARDSET
    @DeleteMapping("/{id}")
    public void deleteCardSet(@PathVariable Long id) {
        cardSetService.deleteCardSet(id);
    }

    //UPDATE A CARDSET
    @PutMapping("/{id}")
    public CardSet updateCardSet(@PathVariable Long id, @RequestBody CardsetRequest request) {
        CardSet existingCardSet = cardSetService.getCardSetbyId(id);
        existingCardSet.setName(request.getName());
        return cardSetService.updateCardSet(existingCardSet);
    }

/*    //UPDATE A CARD IN CARDSET
    @PutMapping("/{cardSetId}/{cardId}")
    public Card updateCardInCardSet(@PathVariable Long cardSetId, @PathVariable Long cardId, @RequestBody CardRequest request) {
        CardSet cardSet = cardSetService.getCardSetbyId(cardSetId);
        Card existingCard = cardService.getCardFromCardSetByID(cardSet, --cardId);
        existingCard.setQuestion(request.getQuestion());
        cardService.updateCardInCardSet(cardSet, existingCard);
        return existingCard;
    }*/

    // Update a Card in a CardSet (generic method for all card types)
    @PutMapping("/{cardSetId}/updateCard/{cardId}")
    public Card updateCardYEAInCardSet(@PathVariable Long cardSetId, @PathVariable Long cardId, @RequestBody CardRequest request) {
        CardSet cardSet = cardSetService.getCardSetbyId(cardSetId);
            Card existingCard = cardService.getCardFromCardSetByID(cardSet, cardId);
                existingCard.setQuestion(request.getQuestion());

                if (existingCard instanceof NumberCard) {
                    ((NumberCard<Number>) existingCard).setAnswer(Double.parseDouble(request.getAnswer()));
                } else if (existingCard instanceof TextCard) {
                    ((TextCard) existingCard).setAnswer(request.getAnswer());
                } else if (existingCard instanceof MultipleChoiceCard) {
                    // Handle multiple choice card fields
                } else if (existingCard instanceof MultiChoiceCard) {
                    // Handle multi-choice card fields
                }
                cardService.updateCardInCardSet(cardSet, existingCard);
                return existingCard;

    }
}