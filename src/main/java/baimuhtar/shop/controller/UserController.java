package baimuhtar.shop.controller;

import baimuhtar.shop.entity.User;
import baimuhtar.shop.service.FeedbackService;
import baimuhtar.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping(path = "/login")
    public String loginPage() {
        return "login_page";
    }

    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/adduser")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.createUser(user);
            return "redirect:/adduser";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Пользователь с данным логином уже существует");
            return "registration";
        }
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "profile";
    }

    @GetMapping("/user/feedback/delete")
    public String deleteUserFeedback(@RequestParam Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return "redirect:/user/feedback";
    }
}
