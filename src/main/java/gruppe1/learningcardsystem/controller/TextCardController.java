package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.services.TextCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gruppe1.learningcardsystem.controller.responses.TextCard;
import gruppe1.learningcardsystem.controller.requests.CardRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/textcards")
public class TextCardController {

    @Autowired
    private TextCardService textCardService;

    @GetMapping
    public List<TextCard> getAllTextCards() {
        return textCardService.getAllTextCards();
    }

    @GetMapping("/{id}")
    public Optional<TextCard> getTextCard(@PathVariable Long id) {
        return textCardService.getTextCardById(id);
    }

    @PostMapping
    public TextCard createTextCard(@RequestBody CardRequest cardRequest) {
        TextCard textCard = new TextCard();
        textCard.setQuestion(cardRequest.getQuestion());
        textCard.setAnswer(cardRequest.getAnswer()); // Die Antwort ist ein Text.

        return textCardService.createTextCard(textCard);
    }

    @PutMapping("/{id}")
    public Optional<TextCard> updateTextCard(@PathVariable Long id, @RequestBody CardRequest cardRequest) {
        Optional<TextCard> existingTextCard = textCardService.getTextCardById(id);
        if (existingTextCard.isPresent()) {
            TextCard updatedTextCard = existingTextCard.get();
            updatedTextCard.setQuestion(cardRequest.getQuestion());
            updatedTextCard.setSuccessCount(cardRequest.getSuccessCount());
            updatedTextCard.setAnswer(cardRequest.getAnswer()); // Aktualisiere die Antwort.

            return textCardService.updateTextCard(id, updatedTextCard);
        }
        return Optional.empty();
    }

    @DeleteMapping("/{id}")
    public void deleteTextCard(@PathVariable Long id) {
        textCardService.deleteTextCard(id);
    }
}
