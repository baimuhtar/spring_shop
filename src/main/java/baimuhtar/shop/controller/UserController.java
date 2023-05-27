package baimuhtar.shop.controller;

import baimuhtar.shop.entity.Order;
import baimuhtar.shop.entity.User;
import baimuhtar.shop.repository.FeedbackRepository;
import baimuhtar.shop.service.FeedbackService;
import baimuhtar.shop.service.OrderService;
import baimuhtar.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
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
    @GetMapping("/user/feedback/delete")
    public String deleteUserFeedback(@RequestParam Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return "redirect:/user/feedback";
    }
}
