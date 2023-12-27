package tn.esprit.clinique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.clinique.entities.Patient;

public interface PatientRepo extends JpaRepository<Patient,Long> {
}
