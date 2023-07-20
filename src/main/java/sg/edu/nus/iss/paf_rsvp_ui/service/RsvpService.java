package sg.edu.nus.iss.paf_rsvp_ui.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sg.edu.nus.iss.paf_rsvp_ui.model.Rsvp;

@Service
public class RsvpService {
    
    RestTemplate template = new RestTemplate();

    private final String url = "http://localhost:8080/api";

    public List<Rsvp> getAllRsvps() {
        ResponseEntity<List<Rsvp>> response = template.exchange(
                url + "/rsvps", 
                HttpMethod.GET, 
                null,
                new ParameterizedTypeReference<List<Rsvp>>() {
            
                });
                return response.getBody();

    }

    public Rsvp getRsvpById(int id) {
        return template.getForObject(url + "/rsvp/" + id, Rsvp.class);
    }

    public Boolean checkIfIdExists(int id) {
        return template.getForEntity(url + "/search/" + id, Boolean.class).getBody();
    }

    public Boolean addRsvp(Rsvp newRsvp) {
        System.out.println("----------------------------->" + newRsvp);
        return template.postForEntity(url + "/add", newRsvp, Boolean.class).getBody();
    }

    public Boolean updateRsvp(Rsvp updateRsvp) {
        return template.postForEntity(url + "/update", updateRsvp, Boolean.class).getBody();
    }

    public Boolean deleteRsvp(int id) {
        try {
            template.delete(url + "/delete/" + id);
            return true;   
        } catch (Exception e) {
            return false;
        }

    }
    
}
 