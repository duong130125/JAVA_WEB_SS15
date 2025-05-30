package ra.session_15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.session_15.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    // Danh sách sản phẩm mẫu
    public List<Product> productList = Arrays.asList(
            new Product("SP01", "Laptop Dell"),
            new Product("SP02", "Điện thoại Samsung"),
            new Product("SP03", "Chuột Logitech")
    );

    @GetMapping("search")
    public String showSearchForm(Model model) {
        model.addAttribute("keyword", "");
        return "search";
    }

    @PostMapping("search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        if (keyword == null || keyword.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập từ khóa tìm kiếm.");
            return "search";
        }

        List<Product> results = new ArrayList<>();
        for (Product p : productList) {
            if (p.getCode().toLowerCase().contains(keyword.toLowerCase()) ||
                    p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(p);
            }
        }

        model.addAttribute("results", results);
        model.addAttribute("keyword", keyword);
        return "search";
    }
}
