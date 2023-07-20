package sg.edu.nus.iss.paf_rsvp_ui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Rsvp {
    
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private String confirmationDate;
    private String comments;
}
