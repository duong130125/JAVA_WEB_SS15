package ra.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ResumeDTO {
    private Long id;

    @NotBlank(message = "Tên đầy đủ không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Email không đúng định dạng!")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^(\\+84|0)[0-9]{9,10}$",
            message = "Số điện thoại không đúng định dạng (VD: 0123456789 hoặc +84912345678)"
    )
    private String phoneNumber;
    @NotBlank(message = "Trình độ học vấn không được để trống")
    private String education;
    @NotBlank(message = "Kinh nghiệm làm việc không được để trống")
    private String experience;
    @NotBlank(message = "Kỹ năng không được để trống")
    private String skills;
}
