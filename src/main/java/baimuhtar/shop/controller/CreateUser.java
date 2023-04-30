package baimuhtar.shop.controller;

import baimuhtar.shop.entity.User;
import baimuhtar.shop.repository.UserRepository;
import baimuhtar.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping()
public class CreateUser {

    @Autowired
    private UserService userService;

    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }
    @PostMapping("/adduser")
    public String saveUser(@ModelAttribute("user") User user){
     userService.createUser(user);
     return "redirect:/product/list";
    }
}
