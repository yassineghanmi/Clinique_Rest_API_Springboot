package tn.esprit.clinique.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRDV;
    private Date dateRDV;
    private String remarque;
    @ManyToOne()
    private Medicin medicin;
    @ManyToOne()
    private Patient patient;

}
