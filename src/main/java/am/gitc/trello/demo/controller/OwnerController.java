package am.gitc.trello.demo.controller;

import am.gitc.trello.demo.entity.userModel.Role;
import am.gitc.trello.demo.repository.BoardRepository;
import am.gitc.trello.demo.repository.CardRepository;
import am.gitc.trello.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class OwnerController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private BoardRepository boardRepository;


    @RequestMapping(value = "/ownerPage",method = RequestMethod.GET)
    public String ownerPage(ModelMap map){
        map.addAttribute("cards",cardRepository.findAll());
        map.addAttribute("admins",userRepository.findAllByRole(Role.ADMIN));
        map.addAttribute("users",userRepository.findAllByRole(Role.USER));
        return "owner";
    }

    @GetMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/ownerPage";
    }

    @GetMapping(value = "/deleteCardForOwner")
    public String deleteCard(@RequestParam("id") int id) {
        cardRepository.deleteById(id);
        return "redirect:/ownerPage";
    }

    @GetMapping(value = "/deleteBoardForOwner")
    public String deleteBoard(@RequestParam("id")int id){
        boardRepository.deleteById((byte) id);
        return "redirect:/ownerPage";
    }


}
