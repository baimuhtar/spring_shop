package baimuhtar.shop.repository;

import baimuhtar.shop.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByProductId(Long productId);

    Feedback findByUserIdAndProductId(Long userId, Long productId);
}
