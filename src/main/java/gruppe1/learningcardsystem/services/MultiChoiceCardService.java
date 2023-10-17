package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.MultiChoiceCard;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MultiChoiceCardService {

    private final HashMap<Long, MultiChoiceCard> multiChoiceCardMap = new HashMap<>();
    private long nextMultiChoiceCardId = 1;

    public List<MultiChoiceCard> getAllMultiChoiceCards() {
        return new ArrayList<>(multiChoiceCardMap.values());
    }

    public Optional<MultiChoiceCard> getMultiChoiceCardById(Long id) {
        return Optional.ofNullable(multiChoiceCardMap.get(id));
    }

    public MultiChoiceCard createMultiChoiceCard(MultiChoiceCard multiChoiceCard) {
        multiChoiceCard.setId(nextMultiChoiceCardId++);
        multiChoiceCardMap.put(multiChoiceCard.getId(), multiChoiceCard);
        return multiChoiceCard;
    }

    public Optional<MultiChoiceCard> updateMultiChoiceCard(Long id, MultiChoiceCard updatedMultiChoiceCard) {
        if (multiChoiceCardMap.containsKey(id)) {
            updatedMultiChoiceCard.setId(id);
            multiChoiceCardMap.put(id, updatedMultiChoiceCard);
            return Optional.of(updatedMultiChoiceCard);
        }
        return Optional.empty();
    }

    public void deleteMultiChoiceCard(Long id) {
        multiChoiceCardMap.remove(id);
    }
}
