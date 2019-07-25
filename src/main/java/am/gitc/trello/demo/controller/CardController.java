package am.gitc.trello.demo.controller;

import am.gitc.trello.demo.entity.CardEntity;
import am.gitc.trello.demo.exception.FileLoadException;
import am.gitc.trello.demo.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by User on 20.07.2019.
 */

@Slf4j
@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping("/trello/cards")
    public ModelAndView createCard(ModelAndView modelAndWiew, @ModelAttribute("cardForm") CardEntity cardEntity){
        this.cardService.createCard(cardEntity);
        modelAndWiew.setViewName("home");
        modelAndWiew.addObject("card",cardEntity);
        return modelAndWiew;
    }


    @PutMapping("trello/cards/{id}")
    public ModelAndView updateCard(@PathVariable("id") short id,@ModelAttribute("cardForm") CardEntity cardEntity,
                                   ModelAndView modelAndView){

        cardEntity.setId(id);
        cardEntity = this.cardService.updateCard(cardEntity);
        modelAndView.setViewName("home");
        modelAndView.addObject("card",cardEntity);
        log.info("card width {} id updated",id);
        return modelAndView;
    }



    @DeleteMapping("/trello/cards/{id}")
    public ModelAndView deleteCardById (ModelAndView modelAndView, short cardId){
        this.cardService.deleteCard(cardId);
        modelAndView.addObject("DeleteCard",
                "Your request has been sucssefuly acept");
        modelAndView.setViewName("home");
        log.info("card width {} id deleted",cardId);
        return modelAndView;
    }



    @PostMapping("/trello/cards/{id}/_upload_file")
    public ModelAndView attachFile(@PathVariable("id") Short id,
                                   @RequestParam("file") MultipartFile file,
                                   ModelAndView modelAndView) {
        try {
           CardEntity entity = this.cardService.attachFile(id, file);
            modelAndView.setViewName("home");
            modelAndView.addObject("card", entity);
            return modelAndView;
        } catch (IOException e) {
           log.error("ERROR", e.getMessage());
           throw new FileLoadException(e);
        }
    }
}

