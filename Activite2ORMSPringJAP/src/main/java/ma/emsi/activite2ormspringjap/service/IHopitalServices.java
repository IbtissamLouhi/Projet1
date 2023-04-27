package ma.emsi.activite2ormspringjap.service;

import ma.emsi.activite2ormspringjap.entities.Consultation;
import ma.emsi.activite2ormspringjap.entities.Medecin;
import ma.emsi.activite2ormspringjap.entities.Patient;
import ma.emsi.activite2ormspringjap.entities.RendezVous;

public interface IHopitalServices {
    Patient savePatient (Patient patient);
    Medecin saveMedecin (Medecin medecin);
    RendezVous saveRendezVous (RendezVous rendezVous);
    Consultation saveConsultation (Consultation consultation);

}
