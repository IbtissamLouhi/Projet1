package ma.emsi.activite2ormspringjap.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity //Entity jpa => classe persistante => exite dans la bdd (Tabe)
@Data
@NoArgsConstructor @AllArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    @NotEmpty
    @Size(min = 4,max=15)
    private String nom;
    @Temporal(TemporalType.DATE) //jute la date
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaiss;
    private Boolean malade;
    @DecimalMin("100")
    private int score;
}
//traitement
//Diagnostic 
//
//actuel
//Symptômes
