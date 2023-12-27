package tn.esprit.clinique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.clinique.entities.Clinique;

public interface CliniqueRepo extends JpaRepository<Clinique,Long> {
}
