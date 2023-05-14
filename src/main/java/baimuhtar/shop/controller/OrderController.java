package baimuhtar.shop.controller;

import baimuhtar.shop.entity.*;
import baimuhtar.shop.repository.OrderRepository;
import baimuhtar.shop.service.CartItemService;
import baimuhtar.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CartItemService cartItemService;

@GetMapping("/user_orders")
public String showOrders(Model model){
    List<CartItem> cartItems = cartItemService.getItemsByUserId();
    model.addAttribute("cartItems", cartItems);
    return "orders";
}

    @GetMapping("/change_status")
    public String chooseOrderStatus(@RequestParam Long orderId, @RequestParam Long userId, @RequestParam OrderStatus orderStatus, Model model) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        List<Order> orders = orderRepository.findAllByUserId(userId);
        model.addAttribute("order", order);
        model.addAttribute("orders", orders);
        return "redirect:/user_orders";
    }

    @PostMapping("/change_status")
    public String changeStatus(@RequestParam Long orderId) {
        orderService.changeStatus(orderId);
        return "redirect:/user_orders";
    }
    @GetMapping("/make_order")
    public String chooseAddressAndStatus(Model model, String deliveryAddress) {
        model.addAttribute("deliveryAddress", deliveryAddress);
        return "redirect:/user_orders";
    }

    @PostMapping("/make_order")
    public String makeOrder(@RequestParam String deliveryAddress) {
        orderService.makeOrder(deliveryAddress);
        return "redirect:/user_orders";
    }

}
