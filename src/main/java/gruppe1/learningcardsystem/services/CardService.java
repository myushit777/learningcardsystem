package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.Card;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final List<Card> cards = new ArrayList<>();
    private long nextCardId = 1;

    public List<Card> getAllCards() {
        return cards;
    }

    public Optional<Card> getCardById(Long id) {
        return cards.stream().filter(card -> card.getId().equals(id)).findFirst();
    }

    public Card createCard(Card card) {
        card.setId(nextCardId++);
        cards.add(card);
        return card;
    }

    public Optional<Card> updateCard(Long id, Card updatedCard) {
        Optional<Card> existingCard = getCardById(id);
        if (existingCard.isPresent()) {
            cards.remove(existingCard.get());
            updatedCard.setId(id);
            cards.add(updatedCard);
            return Optional.of(updatedCard);
        }
        return Optional.empty();
    }

    public void deleteCard(Long id) {
        cards.removeIf(card -> card.getId().equals(id));
    }
}
