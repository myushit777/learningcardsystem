package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.TextCard;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TextCardService {

    private final HashMap<Long, TextCard> textCardMap = new HashMap<>();
    private long nextTextCardId = 1;

    public List<TextCard> getAllTextCards() {
        return new ArrayList<>(textCardMap.values());
    }

    public Optional<TextCard> getTextCardById(Long id) {
        return Optional.ofNullable(textCardMap.get(id));
    }

    public TextCard createTextCard(TextCard textCard) {
        textCard.setId(nextTextCardId++);
        textCardMap.put(textCard.getId(), textCard);
        return textCard;
    }

    public Optional<TextCard> updateTextCard(Long id, TextCard updatedTextCard) {
        if (textCardMap.containsKey(id)) {
            updatedTextCard.setId(id);
            textCardMap.put(id, updatedTextCard);
            return Optional.of(updatedTextCard);
        }
        return Optional.empty();
    }

    public void deleteTextCard(Long id) {
        textCardMap.remove(id);
    }
}
