package baimuhtar.shop.repository;

import baimuhtar.shop.entity.Order;
import baimuhtar.shop.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}
