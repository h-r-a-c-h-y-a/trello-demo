package am.gitc.trello.demo.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 20.07.2019.
 */

@RestController
public class HomeController{

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
