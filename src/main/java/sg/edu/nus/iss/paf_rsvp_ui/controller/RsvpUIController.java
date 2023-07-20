package sg.edu.nus.iss.paf_rsvp_ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import sg.edu.nus.iss.paf_rsvp_ui.model.Rsvp;
import sg.edu.nus.iss.paf_rsvp_ui.service.RsvpService;

@Controller
@RequestMapping
public class RsvpUIController {

    @Autowired
    RsvpService svc;

    @GetMapping
    public String homePage() {
        return "index";
    }

    @GetMapping("/rsvps")
    public String getAllRsvps(Model model) {
        List<Rsvp> rsvpList = svc.getAllRsvps();

        System.out.println(rsvpList);
        model.addAttribute("rsvps", rsvpList);

        return "rsvps";
    }

    @GetMapping("/search")
    public String search(Model model) {

        return "search";
    }

    @GetMapping("/searchById")
    public String searchById(@RequestParam int id, Model model) {
        if (svc.checkIfIdExists(id)) {
            Rsvp rsvp = svc.getRsvpById(id);
            model.addAttribute("rsvp", rsvp);

            return "redirect:/rsvps/" + id;
        } else {
            return "error";
        }
    }

    @GetMapping("/rsvps/{id}")
    public String getRsvpById(@PathVariable int id, Model model) {
        Rsvp result = svc.getRsvpById(id);
        model.addAttribute("rsvp", result);

        return "rsvp";
    }

    @GetMapping("/newRsvp")
    public String newRsvp(Model model) {
    
        model.addAttribute("newRsvp", new Rsvp());
        return "newRsvp";
    }

    @PostMapping("/created")
    public String newRsvp(@Valid @ModelAttribute("newRsvp") Rsvp newRsvp, BindingResult result) {
        System.out.println("--------------------------------------------------->" + newRsvp);
        if(result.hasErrors()) {
            return "newRsvp";
        }
        
        if(svc.addRsvp(newRsvp)) {
            return "success";
        } else {
            return "error";
        }    
    }

    @GetMapping("/getRsvpDetails")
    public String searchToUpdate() {
        return "searchToEdit";
    }

    @GetMapping("/checkRsvpToUpdate")
    public String searchByIdUpdate(@RequestParam int id) {
        if (svc.checkIfIdExists(id)) {
            return "redirect:/update/" + id;
        }
        return "searchToEdit";
    }

    @GetMapping("/update/{id}")
    public String getRsvpToEdit(@PathVariable int id, Model model) {
        Rsvp toUpdate = svc.getRsvpById(id);
        // System.out.println("------------------------->" + id);
        // System.out.println("-------------------------->" + toUpdate);
        model.addAttribute("rsvp", toUpdate);

        return "update";

    }

    // @GetMapping("/updating")
    // public String submitUpdate (Model model) {

    // model.addAttribute("updateRsvp", new Rsvp());

    // return "update";
    // }

    @PostMapping("/update")
    public String updatedRsvp(@Valid @ModelAttribute("rsvp") Rsvp updateRsvp, BindingResult result, Model model) {
        // System.out.println("----------------------------->" + updateRsvp.getId());
        // System.out.println("--------------------------->" + updateRsvp);
        if(result.hasErrors()) {
            return "update";
        }
        if (svc.updateRsvp(updateRsvp)) {
            return "success";
        }
        return "error";
    }

    @GetMapping("deleteRsvp")
    public String findRsvpToDeletePage() {
        
        return "delete";
    }

    @GetMapping("/deleteById")
    public String searchByIdDelete(@RequestParam int id) {
        
        System.out.println("----------------------->" + id);

        if (svc.checkIfIdExists(id)) {
            return "redirect:/delete/" + id;
        }
        return "delete";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        System.out.println("--------------------------->" + id);
        if(svc.deleteRsvp(id)) {
            return "deleted";
        } else {
            return "error";
        }    
    }
    
}
