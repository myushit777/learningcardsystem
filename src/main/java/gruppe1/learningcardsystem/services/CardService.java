package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.Card;
import gruppe1.learningcardsystem.controller.responses.CardSet;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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
        for (int i = 0; i < cardSet.getCards().size(); i++) {
            if (Objects.equals(cardSet.getCards().get(i).getId(), card.getId())) {
                cardSet.getCards().set(i, card);
                return card;
            }
        }
        return null;
    }

    public Card getCardFromCardSetByID(CardSet cardSet, Long id) {
        return cardSet.getCards().get(id.intValue());
    }


}
