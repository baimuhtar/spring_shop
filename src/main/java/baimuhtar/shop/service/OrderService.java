package baimuhtar.shop.service;

import baimuhtar.shop.entity.*;
import baimuhtar.shop.entity.enums.OrderStatus;
import baimuhtar.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private OrderRepository orderRepository;

    public void makeOrder(String address) {
        Sort sort = Sort.by(Sort.Order.desc("quantity"));
        List<CartItem> cartItems = cartItemRepository.findAllByUserId(userService.getCurrentUser().getId(), sort);
        Order order = new Order();
        order.setUser(userService.getCurrentUser());
        order.setOrder_status(OrderStatus.SEND);
        order.setDeliveryAddress(address);
        order.setOrderTime(LocalDateTime.now());
        orderRepository.save(order);
        for (int i = 0; i < cartItems.size(); i++) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(cartItems.get(i).getProduct());
            orderProduct.setQuantity(cartItems.get(i).getQuantity());
            orderProductRepository.save(orderProduct);
        }
    }

    public void changeStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setOrder_status(orderStatus);
        orderRepository.save(order);
    }

    public List<OrderStatus> getAllOrderStatuses() {
        return List.of(OrderStatus.values());
    }

    public List<Order> findOrderByUser() {
        return orderRepository.findAllByUserId(userService.getCurrentUser().getId());
    }

    public List<Order> findAllOrders() {
        return orderRepository.sortedOrderList();
    }

    public String getOrderDate(LocalDateTime dateTime) {
        return dateTime.getHour() + ":" + String.format("%02d", dateTime.getMinute()) + " " +
                String.format("%02d", dateTime.getDayOfMonth()) + "/" + String.format("%02d", dateTime.getMonthValue()) + "/" +
                dateTime.getYear();
    }

    public void deleteCartItemsAfterOrder() {
        cartItemRepository.deleteAllByUserId(userService.getCurrentUser().getId());
    }

    public int getOrderPrice() {
        List<Order> orders = orderRepository.findAllByUserId(userService.getCurrentUser().getId());
        int price = 0;
        for (Order order : orders) {
            List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderId(order.getId());
            for (OrderProduct orderProduct : orderProducts) {
                price = price + orderProduct.getProduct().getPrice();
            }
        }
        return price;
    }
}
