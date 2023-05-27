package baimuhtar.shop.controller;

import baimuhtar.shop.entity.*;
import baimuhtar.shop.entity.enums.OrderStatus;
import baimuhtar.shop.repository.OrderRepository;
import baimuhtar.shop.service.CartItemService;
import baimuhtar.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/order")
    public String findOrderByUser(Model model) {
        model.addAttribute("ordersByUser", orderService.findOrderByUser());
        model.addAttribute("statuses", orderService.getAllOrderStatuses());
        return "order";
    }
    @GetMapping("/show_admin_orders")
    public String findAllOrders(Model model) {
        model.addAttribute("all_orders", orderService.findAllOrders());
        model.addAttribute("statuses", orderService.getAllOrderStatuses());
        return "admin_orders";
    }
    @GetMapping ("/change_status")
    public String changeOrderStatus(@RequestParam Long orderId, @RequestParam String orderStatus) {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        OrderStatus status = OrderStatus.valueOf(orderStatus);
        orderService.changeStatus(orderId, status);
        return "redirect:/order";
    }
    @GetMapping("/delete_order")
    public String deleteOrder(@RequestParam Long orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/order";
    }
}
