package baimuhtar.shop.service;

import baimuhtar.shop.entity.*;
import baimuhtar.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;

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
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;


    public void makeOrder(String deliveryAddress) {
        List<CartItem> cartItems = cartItemRepository.findByUser(userService.getCurrentUser());

        Order order = new Order();
        order.setUser(userService.getCurrentUser());
        order.setOrder_status(OrderStatus.SEND);
        order.setDeliveryAddress(deliveryAddress);
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
}
