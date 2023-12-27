package tn.esprit.clinique.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Medicin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMedicin;
    private String nomMedicin;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private int telephone;
    private int prixConsultation;
    @ManyToMany()
    @JsonIgnore
    private List<Clinique> cliniques;
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "medicin")
    @JsonIgnore
    private List<RendezVous> rendezVousList;
}
