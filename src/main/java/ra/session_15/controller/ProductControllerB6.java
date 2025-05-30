package ra.session_15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ra.session_15.model.ProductB6;
import ra.session_15.model.Review;
import ra.session_15.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductControllerB6 {
    @Autowired
    private ReviewService reviewService;

    private static List<ProductB6> products = new ArrayList<>();

    static {
        products.add(new ProductB6(1, "Sản phẩm A", "Mô tả A", 100));
        products.add(new ProductB6(2, "Sản phẩm B", "Mô tả B", 150));
        products.add(new ProductB6(3, "Sản phẩm C", "Mô tả C", 200));
    }

    @GetMapping("products")
    public String listProducts(Model model) {
        model.addAttribute("products", products);
        return "listProduct";
    }

    @GetMapping("product/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        ProductB6 product = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        List<Review> reviews = reviewService.getReviewsByProduct(id);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("productId", id);
        return "product_detail";
    }

    @PostMapping("review")
    public String addReview(@ModelAttribute Review review) {
        reviewService.addReview(review);
        return "redirect:/product/" + review.getIdProduct();
    }
}
