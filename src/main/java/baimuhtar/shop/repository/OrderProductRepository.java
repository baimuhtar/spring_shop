package baimuhtar.shop.repository;

import baimuhtar.shop.entity.Order;
import baimuhtar.shop.entity.OrderProduct;
import baimuhtar.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
