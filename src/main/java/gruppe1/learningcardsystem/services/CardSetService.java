package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.Cardset;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardSetService {

    //Alle Cardsets werden in einer Map gespeichert
    private HashMap<Long, Cardset> cardsets = new HashMap<>();
    private Long nextId = 1l;

    //Erstelle ein Cardset
    public Cardset createCardset(String name){
        Cardset cardset = new Cardset(nextId++,name);
        cardsets.put(cardset.getId(), cardset);
        return cardset;
    }

    public Cardset getCardsetById(Long id){
        return cardsets.get(id);
    }

    public List<Cardset> getAllCardsets() {
        List<Cardset> collect = cardsets.entrySet()
                .stream()
                .map((x) -> x.getValue())
                .collect(Collectors.toList());
        return collect;
    }

    public Cardset updateCardset(Cardset updatedCardset) {
        Long setId = updatedCardset.getId();

        if (cardsets.containsKey(setId)) {
            // Aktualisieren Sie das Cardset mit den neuen Daten
            cardsets.put(setId, updatedCardset);
        } return updatedCardset;
    }
}
