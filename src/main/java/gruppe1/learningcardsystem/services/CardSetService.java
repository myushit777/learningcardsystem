package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class CardSetService {

    private final List<CardSet> cardSetList = new ArrayList<>();
    private long nextCardSetId = 0;

    public CardSet createCardSet(CardSet cardSet){
        cardSet.setId(nextCardSetId);
        cardSetList.add(cardSet);
        nextCardSetId++;
        return cardSet;
    }

    public CardSet getCardSetbyId(Long id){
        return cardSetList.get(id.intValue());
    }

    public void deleteCardSet(Long id){
        cardSetList.remove(id.intValue());
    }

    public CardSet updateCardSet(CardSet cardSet) {
        for (int i = 0; i < cardSetList.size(); i++) {
            if (cardSetList.get(i).getId() ==(cardSet.getId())) {
                cardSetList.set(i, cardSet);
                return cardSet;
            }
        } return null;
    }

    //ziehe Karte mit dem ältesten DueDate
        public Card drawCardWithOldestDueDateFromSet(CardSet cardSet) {
            List<Card> cards = cardSet.getCards();

            if (cards.isEmpty()) {
                return null; // Keine Karten im Kartenstapel
            }

            // filtert alle Karten mit isDraft false raus um diese abzufragen
            List<Card> nonDraftCards = cards.stream()
                    .filter(card -> !card.isDraft())
                    .collect(Collectors.toList());

            if (nonDraftCards.isEmpty()) {
                return null; // Keine nicht-Entwurfskarten im Kartenstapel
            }

            // Sortieren Sie die nicht-Entwurfskarten nach dem nextDueDate in aufsteigender Reihenfolge
            nonDraftCards.sort(Comparator.comparing(Card::getNextDueDate));

            // Ziehen Sie die Karte mit dem ältesten nextDueDate (erste Karte in der sortierten Liste)
            Card drawnCard = nonDraftCards.get(0);
            nonDraftCards.remove(drawnCard); // Entfernen Sie die Karte aus dem Kartenstapel

            return drawnCard;
        }




    public  <T> T parseValue(String value) {
        try {
            if (value.contains(".")) {
                return (T) (Double) Double.parseDouble(value);
            } else {
                if (value.length() <= 9) {
                    return (T) (Integer) Integer.parseInt(value);
                } else {
                    return (T) (Long) Long.parseLong(value);
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value format");
        }
}
}