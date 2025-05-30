package ra.session_15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.session_15.model.Review;
import ra.session_15.repository.ReviewRepo;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public List<Review> getReviewsByProduct(int idProduct) {
        return reviewRepo.findByProductId(idProduct);
    }

    @Override
    public boolean addReview(Review review) {
        return reviewRepo.save(review);
    }
}