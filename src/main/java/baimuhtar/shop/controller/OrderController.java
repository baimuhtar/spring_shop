package baimuhtar.shop.controller;

import baimuhtar.shop.entity.Order;
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

import java.util.List;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/make_order")
    public String chooseAddressAndStatus(Model model, String deliveryAddress) {
        model.addAttribute("deliveryAddress", deliveryAddress);
        return "cart";
    }

    @PostMapping("/make_order")
    public String makeOrder(@RequestParam String deliveryAddress) {
        orderService.makeOrder(deliveryAddress);
        return "redirect:/user_cart";
    }

    @GetMapping("/change_status")
    public String chooseOrderStatus(@RequestParam Long orderId, @RequestParam Long userId, @RequestParam OrderStatus orderStatus, Model model) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        List<Order> orders = orderRepository.findAllByUserId(userId);
        model.addAttribute("order", order);
        model.addAttribute("orders", orders);
        return "user_orders";
    }

    @PostMapping("/change_status")
    public String changeStatus(@RequestParam Long orderId) {
        orderService.changeStatus(orderId);
        return "redirect:/user_orders";
    }
}
