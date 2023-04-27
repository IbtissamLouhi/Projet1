package ma.emsi.activite2ormspringjap.web;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.emsi.activite2ormspringjap.entities.Patient;
import ma.emsi.activite2ormspringjap.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping(path = "/user/index")
    public String patients(Model model,
                          @RequestParam(name = "page", defaultValue = "0")int page,
                          @RequestParam(name="size",defaultValue = "10")int size,
                          @RequestParam ( name= "keyword",defaultValue = "")String keyword) {
        //Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(page,size));
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatient",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentePage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
    @GetMapping(path = "/admin/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(Long id,String keyword,int page)
    {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/admin/patients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //@RequestBody
    public List<Patient>  listPatient()
    {
        return  patientRepository.findAll();
    }
    @GetMapping(path = "/formPatient")
    public String formpatient(Model model)
    {
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
    @PostMapping(path="/admin/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save (Model model, @Valid Patient patient, BindingResult bindingResult,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "") String keyword)
    {
        if (bindingResult.hasErrors()) {
            return "formPatient";
        }
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(Model model, Long id,String keyword,int page)
    {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient==null) throw  new RuntimeException("patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editPatient";

    }
    @GetMapping("/")
    public  String home(){
        return "redirect:/user/index";

    }
}
