package baimuhtar.shop.repository;

import baimuhtar.shop.entity.CartItem;
import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByUserIdAndProductId(Long userId, Long productId);
    List<CartItem> findAllByUserId(Long userId);

}
