package ma.emsi.activite2ormspringjap;

import ma.emsi.activite2ormspringjap.entities.*;
import ma.emsi.activite2ormspringjap.repositories.ConsultationRepository;
import ma.emsi.activite2ormspringjap.repositories.MedicineRepository;
import ma.emsi.activite2ormspringjap.repositories.PatientRepository;
import ma.emsi.activite2ormspringjap.repositories.RendezVousRepository;
import ma.emsi.activite2ormspringjap.service.HopitalServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class Activite2OrmsPringJapApplication {
    @Autowired
    private PatientRepository patientRepository;
    private MedicineRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;

    private ConsultationRepository consultationRepository;
    public static void main(String[] args) {
        SpringApplication.run(Activite2OrmsPringJapApplication.class, args);
    }
    //  @Override
    //public void run(String... args) throws Exception {
    @Bean
    CommandLineRunner start(HopitalServicesImpl hopitalServices) {
      return  args -> {
          /*
          -----Patient-----
           */
          for(int i=0;i<200;i++){
             hopitalServices.savePatient(
                      new Patient(null, "Ibtisam", new Date(), false, (int) 100));}

          //  List<Patient> patients = patientRepository.findAll();
          //problème si nombre d'enregistrement est important
          //solution => Pagination
       /*   Page<Patient> patients = hopitalServices.find(PageRequest.of(1, 10));
          System.out.printf("total pages: " + patients.getTotalPages());
          System.out.printf("total element:" + patients.getTotalElements());
          System.out.printf("num page:" + patients.getNumber());
          List<Patient> content = patients.getContent();
          content.forEach(p -> {
          System.out.println("-------------------------Patient--------------------------");
          System.out.println(String.valueOf(p.getId()));
          System.out.println(String.valueOf(p.getNom()));
          System.out.println(String.valueOf(p.getMalade()));
          System.out.println(String.valueOf(p.getDateNaiss()));
          System.out.println(String.valueOf(p.getScore()));
          });
          System.out.println("-----------------------chercher patient par id-------");*/
         Patient patient = hopitalServices.findPatientById(1L);
         /* System.out.println("-------------Patient(id=1)---------");
          Patient patient = patientRepository.findById(1L).orElse(null);
          if (patient != null) {
              System.out.println(String.valueOf(patient.getId()));
              System.out.println(String.valueOf(patient.getNom()));
              System.out.println(String.valueOf(patient.getMalade()));
              System.out.println(String.valueOf(patient.getDateNaiss()));
              System.out.println(String.valueOf(patient.getScore()));
          }
          /*
           -------Medecins------
*/
         Stream.of("kawtar", "samir", "zakaria").forEach(name -> {
              Medecin medecin = new Medecin();
              medecin.setNom(name);
              medecin.setEmail(name + "@gmail.com");
              medecin.setSpecialite(Math.random() > 0.5 ? "cardio" : "Dentiste");
              hopitalServices.saveMedecin(medecin);
          });
          Medecin medecin =  hopitalServices.findMedecinById(1L);

          /*-------Rendez-vous----*/

         RendezVous rendezVous = new RendezVous();
          rendezVous.setDate(new Date());
          rendezVous.setStatuRDV(StatuRDV.PENDING);
          rendezVous.setMedecin(medecin);
          rendezVous.setPatient(patient);
          hopitalServices.saveRendezVous(rendezVous);
          /*
          ------Consultation-----
          */
         // RendezVous rendezVous1 =  rendezVousRepository.findById(1L).orElseThrow(() -> new RuntimeException("patient not found"));
         /* Consultation consultation = new Consultation();
          consultation.setDateConsult(new Date());
          consultation.setRendezVous(rendezVous1);
          consultation.setRapport("rapport de la consultation1--------------");
          consultationRepository.save(consultation);
          // patient.setScore(180);
          // patientRepository.save(patient);//update id !=null
          // patientRepository.deleteById(1L);
          //patientRepository.deleteAll();
          //List<Patient> byMalade = patientRepository.findByMalade(true);
          //byMalade.forEach(System.out::println);
          //List<Patient> nameLike = patientRepository.charcherPatient("%h%");
          // nameLike.forEach(System.out::println);*/
      };
    }
    @Bean
     PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
     }


}
