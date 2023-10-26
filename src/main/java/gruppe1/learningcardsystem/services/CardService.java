package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.Card;
import gruppe1.learningcardsystem.controller.responses.CardSet;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Service
public class CardService {

    public Card addCardToCardSet(CardSet cardSet, Card card) {
        cardSet.addCard(card);
        return card;
    }

    public void deleteCardFromCardSet(CardSet cardSet, Long cardId) {
        cardSet.deleteCard(cardId);
    }

    public Card updateCardInCardSet(CardSet cardSet, Card card) {
        LinkedHashMap<Long, Card> cards = cardSet.getCards();

        if (cards.containsKey(card.getId())) {
            cards.put(card.getId(), card);
            return card;
        }

        return null;
    }

    public Card getCardFromCardSetByID(CardSet cardSet, Long id) {
        return cardSet.getCards().get(id);
    }

    //erhöht Successcount um 1 bei richtiger Antwort
    public void updateCardAfterCorrectAnswer(Card card) {
        card.setSuccessCount(card.getSuccessCount() + 1);
        // Aktualisiere das nextDueDate, z.B. auf einen Tag in der Zukunft:
        card.setNextDueDate(LocalDateTime.now().plusDays(1));
    }

    public boolean isCardType(Card card, Class<?> expectedType) {
        return expectedType.isInstance(card);
    }



}
