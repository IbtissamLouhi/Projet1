package ma.emsi.activite2ormspringjap.repositories;

import ma.emsi.activite2ormspringjap.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
