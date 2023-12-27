package tn.esprit.clinique.controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.clinique.entities.*;
import tn.esprit.clinique.services.ServiceImp;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class AllRestController {

    private final ServiceImp serviceImp;

    @PostMapping("addClinique")
    public Clinique addClinique (@RequestBody Clinique clinique){
        return serviceImp.addClinique(clinique);
    }

    @PostMapping("addMedecinAndAssignToClinique/{cliniqueId}")
    public Medicin addMedecinAndAssignToClinique (@RequestBody Medicin medecin,@PathVariable("cliniqueId") Long cliniqueId){
        return serviceImp.addMedecinAndAssignToClinique(medecin,cliniqueId);
    }

    @PostMapping("addPatient")
    public Patient addPatient(@RequestBody Patient patient){
        return serviceImp.addPatient(patient);
    }

    @PostMapping("addRDVAndAssignMedAndPatient/{idMedecin}/{idPatient}")
    public void addRDVAndAssignMedAndPatient(@RequestBody RendezVous rdv,@PathVariable("idMedecin") Long idMedecin,@PathVariable("idPatient") Long idPatient) {
        serviceImp.addRDVAndAssignMedAndPatient(rdv, idMedecin, idPatient);
    }

    @GetMapping("getRendezVousByCliniqueAndSpecialite/{idClinique}/{specialite}")
    public List<RendezVous> getRendezVousByCliniqueAndSpecialite(@PathVariable("idClinique") Long idClinique,@PathVariable("specialite") Specialite specialite){
        return serviceImp.getRendezVousByCliniqueAndSpecialite(idClinique, specialite);
    }

    @GetMapping("getNbrRendezVousMedecin/{idMedecin}")
    public int getNbrRendezVousMedecin(@PathVariable("idMedecin") Long idMedecin){
        return serviceImp.getNbrRendezVousMedecin(idMedecin);
    }

    @GetMapping("revenu/{idMedecin}/{startDate}/{endDate}")
    public int getRevenuMedecin (
            @PathVariable("idMedecin") Long idMedecin,
            @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate

    ){
        return serviceImp.getRevenuMedecin(idMedecin, startDate, endDate);
    }
}