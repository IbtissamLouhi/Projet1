package ma.emsi.activite2ormspringjap.repositories;

import ma.emsi.activite2ormspringjap.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository  extends JpaRepository<Patient,Long> {
    /*
        select patient where Malade = m
     */
    List<Patient> findByMalade(Boolean m);
    Page<Patient> findByMalade(Boolean m,Pageable pageable);
    List<Patient> findByMaladeAndScoreLessThan(boolean m,int s);
    List<Patient> findByMaladeIsTrueAndScoreLessThan(int s);
    List<Patient> findByDateNaissBetween(Date d1, Date d2);
    @Query("Select p from Patient p where p.nom like :x")
    List<Patient> charcherPatient(@Param("x") String nom);

    Page<Patient> findByNomContains(String kw, Pageable pageable);
}

