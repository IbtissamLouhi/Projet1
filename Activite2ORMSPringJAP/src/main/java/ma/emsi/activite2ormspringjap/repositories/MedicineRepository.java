package ma.emsi.activite2ormspringjap.repositories;

import ma.emsi.activite2ormspringjap.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medecin,Long> {

}
