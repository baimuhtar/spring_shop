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
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private OrderService orderService;

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
//    @GetMapping("/user/feedback/")
//    public String userFeedbackPage(Model model) {
//        model.addAttribute("feedbacks",feedbackService.getFeedbackByUser());
//        return "user_feedback";
//    }
    @GetMapping("/user/feedback/delete")
    public String deleteUserFeedback(@RequestParam Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return "redirect:/user/feedback";
    }
    @GetMapping("/user/orders")
    public String userOrderPage(Model model) {
        model.addAttribute("orders", orderService.getOrdersByUser());
    return "order";
    }
}
