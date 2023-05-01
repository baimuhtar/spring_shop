package baimuhtar.shop.repository;

import baimuhtar.shop.entity.Order;
import baimuhtar.shop.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserId(Long userId);
}
