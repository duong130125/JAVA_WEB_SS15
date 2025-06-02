package ra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String education;
    private String experience;
    private String skills;
}
