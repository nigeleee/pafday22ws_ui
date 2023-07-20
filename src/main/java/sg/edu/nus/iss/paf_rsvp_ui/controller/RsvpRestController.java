package sg.edu.nus.iss.paf_rsvp_ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_rsvp_ui.model.Rsvp;

@RestController
@RequestMapping("/api")
public class RsvpRestController {
   
    @Autowired
    private JdbcTemplate template;

    private final String findAllByIdSQL = "select * from rsvp where id = ?";
    private final String findAllSQL = "select * from rsvp";
    private final String insertRsvp = "insert into rsvp (full_name, email, phone, confirmation_date, comments) values (?, ?, ?, ?, ?)";
    private final String updateRsvp = "update rsvp set full_name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ? where id = ?";
    private final String deleteRsvp = "delete from rsvp where id = ?";
   

    @GetMapping("/rsvps")
    public List<Rsvp> findRsvps() {
        return template.query(findAllSQL, BeanPropertyRowMapper.newInstance(Rsvp.class));
    }


    @GetMapping("/rsvp/{id}")
    public Rsvp findRsvpById(@PathVariable int id) {
        return template.queryForObject(findAllByIdSQL, BeanPropertyRowMapper.newInstance(Rsvp.class), id);
    }

    @GetMapping("/search/{id}")
    public Boolean checkIfIdExists(@PathVariable int id) {
        Rsvp result =  template.queryForObject(findAllByIdSQL, BeanPropertyRowMapper.newInstance(Rsvp.class), id);
        if(result.getId() != 0) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/add")
    public Boolean addRsvp(@RequestBody Rsvp rsvp) {
        
        System.out.println("------------------------------->" + rsvp);
        
        int result = template.update(insertRsvp, rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments());

        if(result == 1) {
            return true;
        } else { 
            return false;
        }
    }

    @PostMapping("/update") 
    public Boolean updateRsvp(@RequestBody Rsvp rsvp) {
        int result = template.update(updateRsvp, rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments(), rsvp.getId());

        if(result == 1) {
            return true;
        } else { 
            return false;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteRsvp(@PathVariable int id) {
        int result = template.update(deleteRsvp, id);
        if(result == 1) {
            return true;
        }else {

            return false;

        }
    }


}

