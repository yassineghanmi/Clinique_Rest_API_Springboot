package tn.esprit.clinique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.clinique.entities.Medicin;

public interface MedicinRepo extends JpaRepository<Medicin,Long> {
}
