package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.services.MultiChoiceCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import gruppe1.learningcardsystem.controller.responses.MultiChoiceCard;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/multichoicecards")
public class MultiChoiceCardController {

    @Autowired
    private MultiChoiceCardService multiChoiceCardService;

    @GetMapping
    public List<MultiChoiceCard> getAllMultiChoiceCards() {
        return multiChoiceCardService.getAllMultiChoiceCards();
    }

    @GetMapping("/{id}")
    public Optional<MultiChoiceCard> getMultiChoiceCard(@PathVariable Long id) {
        return multiChoiceCardService.getMultiChoiceCardById(id);
    }

    @PostMapping
    public MultiChoiceCard createMultiChoiceCard(@RequestBody MultiChoiceCard multiChoiceCard) {
        return multiChoiceCardService.createMultiChoiceCard(multiChoiceCard);
    }
}

