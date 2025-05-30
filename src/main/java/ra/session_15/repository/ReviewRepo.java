package ra.session_15.repository;

import ra.session_15.model.Review;

import java.util.List;

public interface ReviewRepo {
    List<Review> findByProductId(int idProduct);
    boolean save(Review review);
}
