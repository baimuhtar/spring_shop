package baimuhtar.shop.controller;

import baimuhtar.shop.entity.User;
import baimuhtar.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping()
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {

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
    @GetMapping("/profile")
    public String showUserProfile(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "profile";
    }
}
