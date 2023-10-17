package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.NumberCard;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class NumberCardService {

    private final HashMap<Long, NumberCard> numberCardMap = new HashMap<>();
    private long nextNumberCardId = 1;

    public List<NumberCard> getAllNumberCards() {
        return new ArrayList<>(numberCardMap.values());
    }

    public Optional<NumberCard> getNumberCardById(Long id) {
        return Optional.ofNullable(numberCardMap.get(id));
    }

    public NumberCard createNumberCard(NumberCard numberCard) {
        numberCard.setId(nextNumberCardId++);
        numberCardMap.put(numberCard.getId(), numberCard);
        return numberCard;
    }

    public Optional<NumberCard> updateNumberCard(Long id, NumberCard updatedNumberCard) {
        if (numberCardMap.containsKey(id)) {
            updatedNumberCard.setId(id);
            numberCardMap.put(id, updatedNumberCard);
            return Optional.of(updatedNumberCard);
        }
        return Optional.empty();
    }

    public void deleteNumberCard(Long id) {
        numberCardMap.remove(id);
    }
}
