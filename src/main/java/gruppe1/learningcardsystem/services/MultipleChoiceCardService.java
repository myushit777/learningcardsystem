package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.MultipleChoiceCard;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MultipleChoiceCardService {

    private final HashMap<Long, MultipleChoiceCard> multipleChoiceCardMap = new HashMap<>();
    private long nextMultipleChoiceCardId = 1;

    public List<MultipleChoiceCard> getAllMultipleChoiceCards() {
        return new ArrayList<>(multipleChoiceCardMap.values());
    }

    public Optional<MultipleChoiceCard> getMultipleChoiceCardById(Long id) {
        return Optional.ofNullable(multipleChoiceCardMap.get(id));
    }

    public MultipleChoiceCard createMultipleChoiceCard(MultipleChoiceCard multipleChoiceCard) {
        multipleChoiceCard.setId(nextMultipleChoiceCardId++);
        multipleChoiceCardMap.put(multipleChoiceCard.getId(), multipleChoiceCard);
        return multipleChoiceCard;
    }

    public Optional<MultipleChoiceCard> updateMultipleChoiceCard(Long id, MultipleChoiceCard updatedMultipleChoiceCard) {
        if (multipleChoiceCardMap.containsKey(id)) {
            updatedMultipleChoiceCard.setId(id);
            multipleChoiceCardMap.put(id, updatedMultipleChoiceCard);
            return Optional.of(updatedMultipleChoiceCard);
        }
        return Optional.empty();
    }

    public void deleteMultipleChoiceCard(Long id) {
        multipleChoiceCardMap.remove(id);
    }
}
