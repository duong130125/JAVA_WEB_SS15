package ra.session_15.repository;

import org.springframework.stereotype.Repository;
import ra.session_15.model.Review;
import ra.session_15.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepoImpl implements ReviewRepo {
    @Override
    public List<Review> findByProductId(int idProduct) {
        List<Review> reviews = new ArrayList<>();
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call get_reviews_by_product_id(?)}");
            stmt.setInt(1, idProduct);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Review r = new Review();
                r.setId(rs.getInt("id"));
                r.setIdProduct(rs.getInt("id_product"));
                r.setIdUser(rs.getInt("id_user"));
                r.setRating(rs.getInt("rating"));
                r.setComment(rs.getString("comment"));
                reviews.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return reviews;
    }

    @Override
    public boolean save(Review review) {
        Connection conn = null;
        CallableStatement stmt = null;
        boolean success = false;

        try {
            conn = ConnectionDB.openConnection();
            stmt = conn.prepareCall("{call insert_review(?,?,?,?)}");
            stmt.setInt(1, review.getIdProduct());
            stmt.setInt(2, review.getIdUser());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());
            success = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, stmt);
        }
        return success;
    }
}
