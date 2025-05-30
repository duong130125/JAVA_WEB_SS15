package ra.session_15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ra.session_15.model.Student;

import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {

    @GetMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = Arrays.asList(
                new Student("SV001", "Nguyễn Văn A", 20, "CNTT1", "a@gmail.com", "Hà Nội", "0123456789"),
                new Student("SV002", "Trần Thị B", 21, "CNTT2", "b@gmail.com", "Hồ Chí Minh", "0987654321"),
                new Student("SV003", "Lê Văn C", 22, "CNTT1", "c@gmail.com", "Đà Nẵng", "0911223344")
        );

        model.addAttribute("students", students);
        return "students";
    }
}