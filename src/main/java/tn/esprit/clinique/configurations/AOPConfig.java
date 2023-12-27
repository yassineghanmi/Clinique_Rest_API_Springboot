package tn.esprit.clinique.configurations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AOPConfig {
    @After("execution(* tn.esprit.clinique.services.ServiceImp.get*(..))")
    public void message(){
        log.info("méthode exécutée");
    }
}
