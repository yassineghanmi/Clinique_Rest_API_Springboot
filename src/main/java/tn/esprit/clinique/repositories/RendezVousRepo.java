package tn.esprit.clinique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.clinique.entities.RendezVous;
import tn.esprit.clinique.entities.Specialite;

import java.util.Date;
import java.util.List;

public interface RendezVousRepo extends JpaRepository<RendezVous,Long> {
//    @Query("select r from RendezVous r join r.medicin.cliniques c " +
//            "where c.idClinique = ?1 and r.medicin.specialite = ?2")
//    List<RendezVous> getRendezVousByCliniqueAndSpecialite(Long idClinique, Specialite specialite);

    List<RendezVous> findByMedicinCliniquesIdCliniqueAndMedicinSpecialite(Long idClinique, Specialite specialite);
    List<RendezVous> findByDateRDVAfter(Date currentDate);
    List<RendezVous> findByMedicin_IdMedicinAndAndDateRDVBetween(Long idMedecin, Date startDate, Date endDate);
}
