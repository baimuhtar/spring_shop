package baimuhtar.shop.controller;

import baimuhtar.shop.entity.OrderStatus;
import baimuhtar.shop.repository.OrderRepository;
import baimuhtar.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/make_order")
    public String chooseAddressAndStatus(Model model, String deliveryAddress){
        model.addAttribute("deliveryAddress", deliveryAddress);
        return "cart";
    }

    @PostMapping("/make_order")
    public String makeOrder(@RequestParam String deliveryAddress){
        orderService.makeOrder(deliveryAddress);
        return "redirect:/user_cart";
    }
}
