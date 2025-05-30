package ra.session_15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ra.session_15.dto.UserDTO;
import ra.session_15.model.User;

import javax.validation.Valid;


@Controller
public class UserController {

    @GetMapping("register")
    public String showForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("register")
    public String submitForm(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Chuyển đổi từ DTO -> Entity
        User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());

        model.addAttribute("user", user);
        return "result";
    }
}