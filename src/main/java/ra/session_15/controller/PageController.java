package ra.session_15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("home")
    public String home() {
        return "home";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("contact")
    public String contact() {
        return "contact";
    }
}
