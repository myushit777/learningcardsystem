package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CardSetService {

    private final HashMap<Long, CardSet> cardSetMap = new HashMap<>();
    private long nextCardSetId = 1;

    public List<CardSet> getAllCardSets() {
        return new ArrayList<>(cardSetMap.values());
    }

    public Optional<CardSet> getCardSetById(Long id) {
        return Optional.ofNullable(cardSetMap.get(id));
    }

    public CardSet createCardSet(CardSet cardSet) {
        cardSet.setId(nextCardSetId++);
        cardSetMap.put(cardSet.getId(), cardSet);
        return cardSet;
    }

    public Optional<CardSet> updateCardSet(Long id, CardSet updatedCardSet) {
        if (cardSetMap.containsKey(id)) {
            updatedCardSet.setId(id);
            cardSetMap.put(id, updatedCardSet);
            return Optional.of(updatedCardSet);
        }
        return Optional.empty();
    }

    public void deleteCardSet(Long id) {
        cardSetMap.remove(id);
    }

    public void addCardToCardSet(Long cardSetId, Card card) {
        Optional<CardSet> cardSetOptional = getCardSetById(cardSetId);
        if (cardSetOptional.isPresent()) {
            CardSet cardSet = cardSetOptional.get();
            cardSet.addCard(card);
        }
    }

}
