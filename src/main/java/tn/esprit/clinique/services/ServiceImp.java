package tn.esprit.clinique.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.clinique.entities.*;
import tn.esprit.clinique.repositories.CliniqueRepo;
import tn.esprit.clinique.repositories.MedicinRepo;
import tn.esprit.clinique.repositories.PatientRepo;
import tn.esprit.clinique.repositories.RendezVousRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceImp implements IService{
    private final CliniqueRepo cliniqueRepo;
    private final MedicinRepo medicinRepo;
    private final PatientRepo patientRepo;
    private final RendezVousRepo rendezVousRepo;
    @Override
    public Clinique addClinique (Clinique clinique){
        return cliniqueRepo.save(clinique);
    }

    @Override
    public Medicin addMedecinAndAssignToClinique(Medicin medecin, Long cliniqueId) {
        Clinique clinique = cliniqueRepo.findById(cliniqueId).orElse(null);
        if (medecin.getCliniques() == null) {
            medecin.setCliniques(new ArrayList<>());
        }
        medecin.getCliniques().add(clinique);
        Medicin savedMedicin = medicinRepo.save(medecin);
        clinique.getMedicins().add(savedMedicin);
        cliniqueRepo.save(clinique);
        return savedMedicin;
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepo.save(patient);
    }

    @Override
    public void addRDVAndAssignMedAndPatient(RendezVous rdv, Long idMedecin, Long idPatient) {
        Medicin medicin = medicinRepo.findById(idMedecin).orElse(null);
        Patient patient = patientRepo.findById(idPatient).orElse(null);
        rdv.setMedicin(medicin);
        rdv.setPatient(patient);
        RendezVous savedRendezVous = rendezVousRepo.save(rdv);
        patient.getRendezVousList().add(savedRendezVous);
        patientRepo.save(patient);
        medicin.getRendezVousList().add(savedRendezVous);
        medicinRepo.save(medicin);
    }

    @Override
    public List<RendezVous> getRendezVousByCliniqueAndSpecialite(Long idClinique, Specialite specialite) {
        return rendezVousRepo.findByMedicinCliniquesIdCliniqueAndMedicinSpecialite(idClinique, specialite);
    }

    @Override
    public int getNbrRendezVousMedecin(Long idMedecin) {
        Medicin medicin = medicinRepo.findById(idMedecin).orElse(null);
        return medicin.getRendezVousList().size();
    }

    @Override
    @Scheduled(fixedDelay = 30000)
    public void retrieveRendezVous() {
        List<RendezVous> rendezVousList = rendezVousRepo.findByDateRDVAfter(new Date());
        for(RendezVous rdv : rendezVousList){
            log.info("La liste des RendezVous : "
                    + rdv.getDateRDV()
                    + " Medicin : "
                    + rdv.getMedicin().getNomMedicin()
                    + " Patient : "
                    + rdv.getPatient().getNomPatient());
        }
    }

    @Override
    public int getRevenuMedecin(Long idMedecin, Date startDate, Date endDate) {
        int countRDV = rendezVousRepo.findByMedicin_IdMedicinAndAndDateRDVBetween(idMedecin, startDate, endDate).size();
        int prixC = medicinRepo.findById(idMedecin).orElse(null).getPrixConsultation();
        return prixC * countRDV;
    }
}

