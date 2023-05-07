package baimuhtar.shop.controller;


import baimuhtar.shop.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback")
    public String leaveFeedback(@RequestParam String textFeedback,@RequestParam Integer score, @RequestParam Long productId){

        feedbackService.leaveFeedback(textFeedback, score, productId);
        return "redirect:/product/list";
    }
}
