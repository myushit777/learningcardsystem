package gruppe1.learningcardsystem.services;

import gruppe1.learningcardsystem.controller.responses.Learningcard;
import gruppe1.learningcardsystem.controller.responses.Numbercard;
import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class CardService {

    private final HashMap<Long, Learningcard> learningCards = new HashMap<>();
    private Long nextId = 1L;

    public Learningcard createLearningCard(String question) {
        Learningcard learningcard = new Learningcard();
        learningcard.setId(nextId++);
        learningcard.setQuestion(question);
        learningCards.put(learningcard.getId(), learningcard);
        return learningcard;
    }

    public Learningcard getLearningCardById(Long id) {
        return learningCards.get(id);
    }

    public Learningcard updateLearningCard(Long id, String question, boolean isDraft) {
        Learningcard existingLearningCard = learningCards.get(id);
        if (existingLearningCard != null) {
            existingLearningCard.setQuestion(question);
            existingLearningCard.setDraft(isDraft);

        } return existingLearningCard;
    }

    public void deleteLearningCard(Long id) {
        learningCards.remove(id);
    }
}

