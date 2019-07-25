package am.gitc.trello.demo.controller;

import am.gitc.trello.demo.repository.BoardRepository;
import am.gitc.trello.demo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class AdminController {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private BoardRepository boardRepository;


    @RequestMapping(value = "/adminPage",method = RequestMethod.GET)
    public String adminPage(ModelMap map){
        map.addAttribute("cards",cardRepository.findAll());
        return "admin";
    }

    @GetMapping(value = "/deleteCardForAdmin")
    public String deleteCard(@RequestParam("id") int id) {
        cardRepository.deleteById(id);
        return "redirect:/adminPage";
    }

    @GetMapping(value = "/deleteBoardForAdmin")
    public String deleteBoard(@RequestParam("id")int id){
        boardRepository.deleteById((byte) id);
        return "redirect:/adminPage";
    }

}
