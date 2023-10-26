package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.controller.requests.CardRequest;
import gruppe1.learningcardsystem.controller.responses.*;
import gruppe1.learningcardsystem.services.CardService;
import gruppe1.learningcardsystem.services.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gruppe1.learningcardsystem.controller.requests.CardsetRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;


/*
Es gibt nur ein Controller, da für jede Aktion entweder alle Cardsets oder ein bestimmtes Cardset angesprochen wird.
Karten existieren außerhalb eines Kastens nicht, somit wird immer zuerst der Kasten dann die Karte angesprochen
 */
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

    //gebe alle Cardsets aus
    @GetMapping
    public LinkedHashMap<Long,CardSet> getAllCardsets() {
        return cardSetService.getCardSetMap();
    }

    //ein Cardset per ID suchen
    @GetMapping("/{id}")
    public CardSet getCardSetbyId(@PathVariable Long id) {
        return cardSetService.getCardSetbyId(id);
    }

    //suche in einem Cardset eine Card (man benötigt ID von Kasten und Karte)
    @GetMapping("/{cardSetId}/{cardId}")
    public Card getCardById(@PathVariable Long cardSetId, @PathVariable Long cardId){
        //return cardSetService.getCardSetbyId(cardSetId).getCards().get((cardId);
        return cardService.getCardFromCardSetByID(cardSetService.getCardSetbyId(cardSetId),cardId);
    }

    //erstelle ein neues Cardset
    @PostMapping
    public CardSet addCardSet(@RequestBody CardsetRequest request) {
        CardSet cardSet = new CardSet();
        cardSet.setName(request.getName());

        return cardSetService.createCardSet(cardSet);
    }

    //füge eine Numbercard einem Cardset hinzu
    @PostMapping("/{cardSetId}/addNumber")
    public Card addDoubleCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        NumberCard numberCard = new NumberCard<>();
        numberCard.setQuestion(request.getQuestion());
        numberCard.setAnswer(cardSetService.parseValue(request.getAnswer()));
        numberCard.setDraft(false);
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), numberCard);
        return numberCard;
    }

    //füge eine Textcard einem Cardset hinzu
    @PostMapping("/{cardSetId}/addText")
    public Card addTextCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        TextCard textCard = new TextCard();
        textCard.setQuestion(request.getQuestion());
        textCard.setAnswer(request.getAnswer()); //Double sein Vater
        textCard.setDraft(false);
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), textCard);
        return textCard;
    }

    //füge eine Multiplechoicecard einem Cardset hinzu
    @PostMapping("/{cardSetId}/addMultiple")
    public Card addMultipleChoiceCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        MultipleChoiceCard multipleChoiceCard = new MultipleChoiceCard();
        multipleChoiceCard.setQuestion(request.getQuestion());
        multipleChoiceCard.setAnswer(request.getChoices());
        multipleChoiceCard.setAnswerCorrect(request.getAnswerCorrect());
        multipleChoiceCard.setDraft(false);
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), multipleChoiceCard);
        return multipleChoiceCard;
    }

    //füge eine Multichoicecard einem Cardset hinzu
    @PostMapping("/{cardSetId}/addMulti")
    public Card addMultiChoiceCardToCardSet(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        MultiChoiceCard multiChoiceCard = new MultiChoiceCard();
        multiChoiceCard.setQuestion(request.getQuestion());
        multiChoiceCard.setAnswer(request.getChoices());
        multiChoiceCard.setAnswerCorrect(request.getAnswerCorrect());
        multiChoiceCard.setDraft(false);
        cardService.addCardToCardSet(cardSetService.getCardSetbyId(cardSetId), multiChoiceCard);
        return multiChoiceCard;
    }

    //lösche eine Card in einem Cardset (man benötigt ID von Cardset und Card)
    @DeleteMapping("/{cardSetId}/{cardId}")
    public void deleteCardFromCardSet(@PathVariable Long cardSetId, @PathVariable Long cardId) {
        cardService.deleteCardFromCardSet(cardSetService.getCardSetbyId(cardSetId), cardId);
        cardSetService.updateCardSet(cardSetService.getCardSetbyId(cardSetId));
    }

    //lösche ein Cardset per ID
    @DeleteMapping("/{id}")
    public void deleteCardSet(@PathVariable Long id) {
        cardSetService.deleteCardSet(id);
    }

    //aktualisiere ein Cardset per ID
    @PutMapping("/{id}")
    public CardSet updateCardSet(@PathVariable Long id, @RequestBody CardsetRequest request) {
        CardSet existingCardSet = cardSetService.getCardSetbyId(id);
        existingCardSet.setName(request.getName());
        return cardSetService.updateCardSet(existingCardSet);
    }

    // Update eine Card in einem Cardset (man benötigt ID von Cardset und Card)
    @PutMapping("/{cardSetId}/{cardId}")
    public Card updateCardInCardSet(@PathVariable Long cardSetId, @PathVariable Long cardId, @RequestBody CardRequest request) {
        CardSet cardSet = cardSetService.getCardSetbyId(cardSetId);
        Card existingCard = cardService.getCardFromCardSetByID(cardSet, cardId);

        //existiert eine Card?
        if (existingCard != null) {
            //bei Änderung wird dueDate aktualisiert
            existingCard.setNextDueDate(LocalDateTime.now());

            /*
            Hier wird gecheckt ob die request eine Frage beinhaltet denn NUR DANN wird die question geändert
            sonst bleibt sie bestehen aber es können andere Elemente verändert werden basierend auf dem Kartentyp
             */
            if (request.getQuestion() != null) {
                existingCard.setQuestion(request.getQuestion());
            }

            //wenn NumberCard
            if (cardService.isCardType(existingCard, NumberCard.class)) {
                NumberCard<?> numberCard = (NumberCard<?>) existingCard;

                //wenn in request mitgegeben
                if (request.getAnswer() != null) {
                    numberCard.setAnswer(cardSetService.parseValue(request.getAnswer()));
                }

                //wenn TextCard
            } else if (cardService.isCardType(existingCard,TextCard.class)) {

                //wenn in request mitgegeben
                if (request.getAnswer() != null) {
                    ((TextCard) existingCard).setAnswer(request.getAnswer());
                }

                //wenn MultipleChoice
            } else if (cardService.isCardType(existingCard,MultipleChoiceCard.class)) {
                MultipleChoiceCard multipleChoiceCard = (MultipleChoiceCard) existingCard;

                //wenn in request mitgegeben
                if (request.getChoices() != null) {
                    multipleChoiceCard.setAnswer(request.getChoices());
                }
                if (request.getAnswerCorrect() != null) {
                    multipleChoiceCard.setAnswerCorrect(request.getAnswerCorrect());
                }

                //wenn MultiChoicde
            } else if (cardService.isCardType(existingCard,MultiChoiceCard.class)) {
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


    //ziehe die Karte mit dem "ältesten" dueDate und gebe die Frage zurück
    @GetMapping("/{cardSetId}/drawCard")
    public String drawCardWithOldestDueDate(@PathVariable Long cardSetId) {

        return cardSetService.drawCardQuestionWithOldestDueDateFromSet(cardSetService.getCardSetbyId(cardSetId)); // Return the drawn card, which includes the card's ID.
    }

    // Beantworten einer Karte und Aktualisieren der Karte
    @PutMapping("/{cardSetId}/answerCard")
    public Card answerCard(@PathVariable Long cardSetId, @RequestBody CardRequest request) {
        //wenn in einer Methode ein Wert mehr als einmal gebraucht wird lohnt sich die variable cardSet? frage an Dozent :)
        CardSet cardSet = cardSetService.getCardSetbyId(cardSetId);
        Card drawnCard = cardSetService.drawCardWithOldestDueDateFromSet(cardSet);

        if (drawnCard != null) {

            //wenn Textcard
            if (cardService.isCardType(drawnCard, TextCard.class)) {
                if (((TextCard) drawnCard).checkUserAnswer(request.getUserAnswer())) {
                    //wenn antwort korrekt aktualisiere karte
                    cardService.updateCardAfterCorrectAnswer(drawnCard);
                    cardService.updateCardInCardSet(cardSet, drawnCard);
                }
            }

            //wenn Numbercard
            if(cardService.isCardType(drawnCard, NumberCard.class)) {
                if (((NumberCard<?>) drawnCard).checkUserAnswer(request.getUserAnswer())) {
                    //wenn antwort korrekt aktualisiere karte
                    cardService.updateCardAfterCorrectAnswer(drawnCard);
                    cardService.updateCardInCardSet(cardSet, drawnCard);
                }
            }

            //wenn Multiple
            if (cardService.isCardType(drawnCard,MultipleChoiceCard.class)) {
                if (((MultipleChoiceCard) drawnCard).checkUserAnswer(request.getUserAnswer())) {
                    //wenn antwort korrekt aktualisiere karte
                    cardService.updateCardAfterCorrectAnswer(drawnCard);
                    cardService.updateCardInCardSet(cardSet, drawnCard);
                }
            }

            //wenn Multi
            if (cardService.isCardType(drawnCard,MultiChoiceCard.class)) {
                if (((MultiChoiceCard) drawnCard).checkUserAnswer(request.getUserAnswer())) {
                    //wenn antwort korrekt aktualisiere karte
                    cardService.updateCardAfterCorrectAnswer(drawnCard);
                    cardService.updateCardInCardSet(cardSet, drawnCard);
                }
            }
        }

        return drawnCard; // return updated card
    }
}