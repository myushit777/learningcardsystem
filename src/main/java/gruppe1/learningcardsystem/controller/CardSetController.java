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
    @PostMapping("/{cardSetId}/addNumber")
    public Card addDoubleCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        NumberCard numberCard = new NumberCard<>();
        numberCard.setQuestion(request.getQuestion());
        numberCard.setAnswer(cardSetService.parseValue(request.getAnswer()));
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), numberCard);
        return numberCard;
    }

    //ADD NUMBERCARD INT TO CARDSET
/*    @PostMapping("/{cardSetId}/addInt")
    public Card addIntCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        NumberCard numberCard = new NumberCard<>();
        numberCard.setQuestion(request.getQuestion());
        numberCard.setAnswer(Integer.parseInt(request.getAnswer())); //INT
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), numberCard);
        return numberCard;
    }*/

    //ADD NUMBERCARD LONG TO CARDSET
/*    @PostMapping("/{cardSetId}/addLong")
    public Card addLongCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        NumberCard numberCard = new NumberCard<>();
        numberCard.setQuestion(request.getQuestion());
        numberCard.setAnswer(Long.parseLong(request.getAnswer())); //LONG
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), numberCard);
        return numberCard;
    }*/

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

    // Update a Card in a CardSet (generic method for all card types)
    @PutMapping("/{cardSetId}/{cardId}")
    public Card updateCardYEAInCardSet(@PathVariable Long cardSetId, @PathVariable Long cardId, @RequestBody CardRequest request) {
        CardSet cardSet = cardSetService.getCardSetbyId(cardSetId);
        Card existingCard = cardService.getCardFromCardSetByID(cardSet, --cardId);

        //existiert eine Card?
        if (existingCard != null) {

            /*
            Hier wird gecheckt ob die request eine Frage beinhaltet denn NUR DANN wird die question geändert
            sonst bleibt sie bestehen aber es können andere Elemente verändert werden basierend auf dem Kartentyp
             */
            if (request.getQuestion() != null) {
                existingCard.setQuestion(request.getQuestion());
            }

            //wenn NumberCard
            if (existingCard instanceof NumberCard) {
                NumberCard<?> numberCard = (NumberCard<?>) existingCard;

                //wenn in request mitgegeben
                if (request.getAnswer() != null) {
                    numberCard.setAnswer(cardSetService.parseValue(request.getAnswer()));
                }

                //wenn TextCard
            } else if (existingCard instanceof TextCard) {

                //wenn in request mitgegeben
                if (request.getAnswer() != null) {
                    ((TextCard) existingCard).setAnswer(request.getAnswer());
                }

                //wenn MultipleChoice
            } else if (existingCard instanceof MultipleChoiceCard) {
                MultipleChoiceCard multipleChoiceCard = (MultipleChoiceCard) existingCard;

                //wenn in request mitgegeben
                if (request.getChoices() != null) {
                    multipleChoiceCard.setAnswer(request.getChoices());
                }
                if (request.getAnswerCorrect() != null) {
                    multipleChoiceCard.setAnswerCorrect(request.getAnswerCorrect());
                }

                //wenn MultiChoicde
            } else if (existingCard instanceof MultiChoiceCard) {
                MultiChoiceCard multiChoiceCard = (MultiChoiceCard) existingCard;

                //wenn in request mitgegeben
                if (request.getChoices() != null) {
                    multiChoiceCard.setAnswer(request.getChoices());
                }
                if (request.getAnswerCorrect() != null) {
                    multiChoiceCard.setAnswerCorrect(request.getAnswerCorrect());
                }
            }

            cardService.updateCardInCardSet(cardSet, existingCard);

        }
        return existingCard;
    }

}