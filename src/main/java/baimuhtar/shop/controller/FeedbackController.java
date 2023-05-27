package baimuhtar.shop.controller;


import baimuhtar.shop.entity.Feedback;
import baimuhtar.shop.service.FeedbackService;
import baimuhtar.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/leave_feedback")
    public String leaveFeedback(@RequestParam String textFeedback,
                                @RequestParam Integer score,
                                @RequestParam Long productId) {
        feedbackService.leaveFeedback(textFeedback, score, productId);
        return "redirect:/product/show?productId=" + productId;
    }

    @GetMapping("/delete_feedback")
    public String deleteFeedback(@RequestParam(required = false) Long feedbackId,
                                 @RequestParam(required = false) Long productId) {
        feedbackService.deleteFeedback(feedbackId);
        return "redirect:/product/show?productId=" + productId;
    }

}
