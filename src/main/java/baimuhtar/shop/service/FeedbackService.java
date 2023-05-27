package baimuhtar.shop.service;

import baimuhtar.shop.entity.Feedback;
import baimuhtar.shop.entity.Product;
import baimuhtar.shop.entity.User;
import baimuhtar.shop.repository.FeedbackRepository;
import baimuhtar.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;

    public void leaveFeedback(String textFeedback, Integer score, Long productId) {

        Feedback feedback = new Feedback();
        feedback.setUser(userService.getCurrentUser());
        feedback.setTextFeedback(textFeedback);
        feedback.setScoreFeedback(score);
        feedback.setProduct(productRepository.findById(productId).orElseThrow());
        feedback.setPublishedDate(LocalDateTime.now());
        feedback.setPublished(false);
        feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long feedbackId){
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow();
        feedbackRepository.delete(feedback);
    }
    public void postFeedback(long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow();
        feedback.setPublished(true);
        feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackByUser(){
        return feedbackRepository.findAllByUserOrderById(userService.getCurrentUser());
    }

    public Feedback findFeedback(Long productId) {
        return feedbackRepository.findByUserIdAndProductId(userService.getCurrentUser().getId(), productId);
    }

    public List<Feedback> findAllIsPublishedTrue(Long productId) {
        return feedbackRepository.findAllByProductIdAndIsPublishedTrue(productId);
    }
    public List<Feedback> findAllIsPublishedFalse() {
        return feedbackRepository.findAllByIsPublishedFalse();
    }
}
