package baimuhtar.shop.repository;

import baimuhtar.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    @Query("select o from Order o order by o.id asc")
    List<Order> sortedOrderList();

    Order findByUserId(Long userId);
}
