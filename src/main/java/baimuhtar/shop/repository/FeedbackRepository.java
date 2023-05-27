package baimuhtar.shop.repository;

import baimuhtar.shop.entity.Feedback;
import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByProductIdAndIsPublishedTrue(Long productId);
    List<Feedback> findAllByIsPublishedFalse();

    Feedback findByUserIdAndProductId(Long userId, Long productId);

    boolean existsByProductIdAndUserId(Long productId, Long userId);

}
