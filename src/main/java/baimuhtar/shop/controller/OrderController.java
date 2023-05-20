package baimuhtar.shop.controller;

import baimuhtar.shop.entity.*;
import baimuhtar.shop.repository.OrderRepository;
import baimuhtar.shop.service.CartItemService;
import baimuhtar.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/order")
    public String showOrders(Model model) {
        model.addAttribute("orders", orderService.findOrder());
        return "order";
    }

//    @PostMapping("/make_order")
//    public String chooseAddressAndMakeOrder(@RequestParam String deliveryAddress) {
//        orderService.makeOrder(deliveryAddress);
//        return "redirect:/order";
//    }
}
