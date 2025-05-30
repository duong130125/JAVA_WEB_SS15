package ra.session_15.service;

import ra.session_15.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByProduct(int idProduct);
    boolean addReview(Review review);
}
