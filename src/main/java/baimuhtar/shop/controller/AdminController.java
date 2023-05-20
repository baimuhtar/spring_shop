package baimuhtar.shop.controller;

import baimuhtar.shop.service.FeedbackService;
import baimuhtar.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private OrderService orderService;


}
