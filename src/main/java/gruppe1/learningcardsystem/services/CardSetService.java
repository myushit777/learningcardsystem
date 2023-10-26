package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

//diese Klasse verwaltet alle Funktionen die ein Cardset braucht
@Service
@Data
public class CardSetService {

    private final LinkedHashMap<Long,CardSet> cardSetMap = new LinkedHashMap<>();
    private long nextCardSetId = 1;

    //erstelle ein Cardset und füge sie der Map hinzu
    public CardSet createCardSet(CardSet cardSet){
        cardSet.setId(nextCardSetId);
        cardSetMap.put(nextCardSetId,cardSet);
        nextCardSetId++;
        return cardSet;
    }

    //gibt ein Cardset per ID zurück
    public CardSet getCardSetbyId(Long id){
        return cardSetMap.get(id);
    }

    //lösche ein Cardset aus der Map
    public void deleteCardSet(Long id){
        cardSetMap.remove(id);
    }

    //aktualisiere ein Cardset
    public CardSet updateCardSet(CardSet cardSet) {
        cardSetMap.put(cardSet.getId(),cardSet);
        return cardSet;
    }


    //ziehe Karte mit dem ältesten DueDate
    public Card drawCardWithOldestDueDateFromSet(CardSet cardSet) {
        LinkedHashMap<Long, Card> cards = cardSet.getCards();

        if (cards.isEmpty()) {
            return null; // Keine Karten im Kartenstapel
        }

        // Filtert alle Karten mit isDraft false raus, um diese abzufragen
        List<Card> nonDraftCards = cards.values().stream()
                .filter(card -> !card.isDraft())
                .collect(Collectors.toList());

        if (nonDraftCards.isEmpty()) {
            return null; // Keine nicht-Entwurfskarten im Kartenstapel
        }

        // Sortiere die nicht-Entwurfskarten nach dem nextDueDate in aufsteigender Reihenfolge
        nonDraftCards.sort(Comparator.comparing(Card::getNextDueDate));

        // Ziehe die Karte mit dem ältesten nextDueDate (erste Karte in der sortierten Liste)
        Card drawnCard = nonDraftCards.get(0);
        nonDraftCards.remove(drawnCard); // Entferne die Karte aus dem Kartenstapel

        return drawnCard;
    }


    //gibt nur die Frage der gezogenen Karte aus um nicht die Antwort zu spoilern
    public String drawCardQuestionWithOldestDueDateFromSet(CardSet cardSet) {
        Card drawnCard = drawCardWithOldestDueDateFromSet(cardSet);

        //if Multiple gebe Frage als auch Auswahl der Antworten zurück
        if(drawnCard instanceof MultipleChoiceCard){
            return drawnCard.getQuestion() + "\n"
                    +Arrays.toString(((MultipleChoiceCard) drawnCard).getAnswer());
        }

        //if Multi gebe Frage als auch Auswahl der Antworten zurück
        if(drawnCard instanceof MultiChoiceCard){
            return drawnCard.getQuestion() + "\n"
                    +Arrays.toString(((MultiChoiceCard) drawnCard).getAnswer());
        }

        if (drawnCard != null) {

            // Rückgabe der Frage der gezogenen Karte
            return drawnCard.getQuestion();

        }

        // Wenn keine geeignete Karte gefunden wurde, gebe null zurück
        return null;
    }

    /*da in der Request nur ein String übergeben wird, parst diese Methode den String in den jeweiligen Datentyp */
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