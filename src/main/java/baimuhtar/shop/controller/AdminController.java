package baimuhtar.shop.controller;


import baimuhtar.shop.service.FeedbackService;
import baimuhtar.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/user_orders")
    public String showUserOrders(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("statuses", orderService.getAllOrderStatuses());
        return "user_orders";
    }
    @GetMapping("/feedbacks")
    public String checkUserFeedbacks(Model model) {
        model.addAttribute("feedbacks", feedbackService.findAllIsPublishedFalse());
        return "admin_feedbacks";
    }
    @GetMapping("/post_feedback")
    public String postFeedback(@RequestParam Long feedbackId){
        feedbackService.postFeedback(feedbackId);
        return "redirect:/product/list";
    }

}
