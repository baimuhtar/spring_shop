package baimuhtar.shop.service;

import baimuhtar.shop.entity.*;
import baimuhtar.shop.repository.CartItemRepository;
import baimuhtar.shop.repository.OrderRepository;
import baimuhtar.shop.repository.ProductRepository;
import baimuhtar.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;

    public void makeOrder(Long cartItemId, Long userId, Long productId) {
        List<CartItem> cartItems = cartItemRepository.findAll();

        Order order = new Order();
        order.setUser(userService.getCurrentUser());
        order.setOrder_status(OrderStatus.SEND);
        order.setPr;

    }
}
