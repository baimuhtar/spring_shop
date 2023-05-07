package baimuhtar.shop.service;

import baimuhtar.shop.entity.Feedback;
import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.User;
import baimuhtar.shop.repository.FeedbackRepository;
import baimuhtar.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;

    public void leaveFeedback(String textFeedback, Integer score, Long productId) {

        Product product = productRepository.findById(productId).orElseThrow();

        Feedback feedback = new Feedback();
        feedback.setUser(userService.getCurrentUser());
        feedback.setTextFeedback(textFeedback);
        feedback.setScoreFeedback(score);
        feedback.setProduct(product);
        feedback.setPublishedDate(LocalDateTime.now());
        feedback.setPublished(false);
        feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long feedbackId){
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow();
        feedbackRepository.delete(feedback);
    }

    public void isFeedbackPublished(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow();
        feedback.setPublished(true);
        feedbackRepository.save(feedback);
    }

}
