package baimuhtar.shop.repository;

import baimuhtar.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByUserIdAndProductId(Long userId, Long productId);

    List<CartItem> findAllByUserId(Long userId);
    @Transactional
    void deleteAllByUserId(Long userId);

}
