package am.gitc.trello.demo.controller;


import am.gitc.trello.demo.entity.CardEntity;
import am.gitc.trello.demo.exception.FileLoadException;
import am.gitc.trello.demo.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Optional;

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


//1
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


//@Controller
//@RequestMapping("/home")
//public class HomeController {
//    private final CardService cardService;
//
//    public HomeController(CardService cardService){
//        this.cardService = cardService;
//    }
//
//    @RequestMapping(value = "/createCard", method = RequestMethod.POST)
//    public ModelAndView createCard(String title){
//        ModelAndView modelAndView = new ModelAndView();
////        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        if(!bindingResult.hasErrors()){
//            this.cardService.createCard(title);
//            modelAndView.addObject("title", title);
////        }
//        modelAndView.setViewName("home");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/deleteCard", method = RequestMethod.DELETE)
//    public ModelAndView deleteCard(Integer id){
//        ModelAndView modelAndView = new ModelAndView();
//        this.cardService.deleteCard(id);
//        modelAndView.setViewName("home");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/updateTitle", method = RequestMethod.PATCH)
//    public ModelAndView updateCard(Integer id, String title){
//        ModelAndView modelAndView = new ModelAndView();
//        this.cardService.updateCardChangingTitle(id, title);
//        modelAndView.addObject("tile", title);
//        modelAndView.setViewName("home");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/addComment", method = RequestMethod.PATCH)
//    public ModelAndView addComment(int id, String comment){
//        ModelAndView modelAndView = new ModelAndView();
//        this.cardService.addComment(id, comment);
//        modelAndView.addObject("comment", comment);
//        modelAndView.setViewName("home");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/addDescription", method = RequestMethod.PATCH)
//    public ModelAndView addDescription(int id, String description){
//        ModelAndView modelAndView = new ModelAndView();
//        this.cardService.addDescription(id, description);
//        modelAndView.addObject("description", description);
//        modelAndView.setViewName("home");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/deleteComment", method = RequestMethod.PATCH)
//    public ModelAndView deleteComment(int id, String comment){
//        ModelAndView modelAndView = new ModelAndView();
//        this.cardService.deleteComment(id,comment);
//        modelAndView.setViewName("home");
//        return modelAndView;
//    }
//}
