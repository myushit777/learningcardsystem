package gruppe1.learningcardsystem.controller;

import gruppe1.learningcardsystem.controller.requests.CardsetRequest;
import gruppe1.learningcardsystem.controller.responses.Cardset;
import gruppe1.learningcardsystem.services.CardSetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardsetController {

    private final CardSetService cardSetService;

    public CardsetController(CardSetService cardSetService){
        this.cardSetService = cardSetService;
    }


    @RequestMapping(method = RequestMethod.POST, path = "/cardsets")
    public Cardset createCardset(@RequestBody CardsetRequest request){
        Cardset cardset = cardSetService.createCardset(
                request.getId(),
                request.getName()
        );
        return cardset;
    }

    //GET um mit Id nach Cardset zu suchen
    @GetMapping("/users/{userid}")
    public Cardset getCardset(@PathVariable("setId") Long id){
        return cardSetService.getCardsetById(id);
    }

    //GET alle Cardsets
    @GetMapping(path = "/cardsets")
    public List<Cardset> getAllCardsets(){
        return cardSetService.getAllCardsets();
    }
}
