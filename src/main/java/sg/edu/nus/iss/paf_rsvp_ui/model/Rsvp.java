package sg.edu.nus.iss.paf_rsvp_ui.model;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Rsvp {
    
    private Integer id;

    @NotNull(message = "Field cannot be empty")
    @NotBlank(message = "Field cannot be blank")
    private String fullName;

    @NotNull(message = "Field cannot be empty")
    @NotBlank(message = "Field cannot be blank")
    @Email(message = "Provide a valid email address") 
    private String email;

    @NotNull(message = "Field cannot be empty")
    @NotBlank(message = "Field cannot be blank")
    @Pattern(regexp = "^[0-9]{8,}$", message="Phone number must be 8 digits")
    private String phone;

    @Future(message = "Date must be set after present date")
    private Date confirmationDate;
    private String comments;
}
