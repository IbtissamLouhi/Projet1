package ma.emsi.activite2ormspringjap.service;

import jakarta.transaction.Transactional;
import ma.emsi.activite2ormspringjap.entities.Consultation;
import ma.emsi.activite2ormspringjap.entities.Medecin;
import ma.emsi.activite2ormspringjap.entities.Patient;
import ma.emsi.activite2ormspringjap.entities.RendezVous;
import ma.emsi.activite2ormspringjap.repositories.ConsultationRepository;
import ma.emsi.activite2ormspringjap.repositories.MedicineRepository;
import ma.emsi.activite2ormspringjap.repositories.PatientRepository;
import ma.emsi.activite2ormspringjap.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HopitalServicesImpl implements IHopitalServices {
    private PatientRepository patientRepository;
    private MedicineRepository medicineRepository;
    private ConsultationRepository consultationRepository;
    private RendezVousRepository rendezVousRepository;
    /*
       ----Injection des dépendances via le constructeur------
    */
    public HopitalServicesImpl(PatientRepository patientRepository,
                               MedicineRepository medicineRepository,
                               ConsultationRepository consultationRepository,
                               RendezVousRepository rendezVousRepository) {
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
        this.consultationRepository = consultationRepository;
        this.rendezVousRepository = rendezVousRepository;
    }
    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medicineRepository.save(medecin);
    }
    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }
    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return  consultationRepository.save(consultation);
    }
    public Medecin findMedecinById(Long id)
    {
        return medicineRepository.findById(id).orElseThrow(() -> new RuntimeException("medecins not found"));
    }
    public Patient findPatientById(Long id)
    {
        return patientRepository.findById(id).orElseThrow(() ->  new  RuntimeException("patient not found"));
    }

}
