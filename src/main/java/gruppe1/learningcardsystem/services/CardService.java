package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.Card;
import gruppe1.learningcardsystem.controller.responses.CardSet;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;


//Cardservice bietet 4 CRUD-Methoden an und verwaltet die Logik die im Controller genutzt wird
@Service
public class CardService {

    //fügt eine Card zu einem Cardset hinzu
    public Card addCardToCardSet(CardSet cardSet, Card card) {
        cardSet.addCard(card);
        return card;
    }

    //löscht eine Card aus einem Cardset
    public void deleteCardFromCardSet(CardSet cardSet, Long cardId) {
        cardSet.deleteCard(cardId);
    }

    //Aktualisiert eine Card in einem Cardset
    public Card updateCardInCardSet(CardSet cardSet, Card card) {
        LinkedHashMap<Long, Card> cards = cardSet.getCards();

        if (cards.containsKey(card.getId())) {
            cards.put(card.getId(), card);
            return card;
        }

        return null;
    }

    //sucht eine Card per ID aus einem Cardset
    public Card getCardFromCardSetByID(CardSet cardSet, Long id) {
        return cardSet.getCards().get(id);
    }



    //erhöht den successCount (+1) wenn die Antwort richtig ist
    public void updateCardAfterCorrectAnswer(Card card) {
        card.setSuccessCount(card.getSuccessCount() + 1);
        // Aktualisiere das nextDueDate, z.B. auf einen Tag in der Zukunft:
        card.setNextDueDate(LocalDateTime.now().plusDays(1));
    }

    //überprüft ob die Karte einem bestimmten Kartentyp entspricht
    public boolean isCardType(Card card, Class<?> expectedType) {
        return expectedType.isInstance(card);
    }



}
