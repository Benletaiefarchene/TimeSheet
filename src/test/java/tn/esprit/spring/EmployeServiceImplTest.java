package tn.esprit.spring;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tn.esprit.spring.services.EntrepriseServiceImpl;


@SpringBootTest
class EmployeTest {
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private EmployeServiceImpl employeService;
    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    EntrepriseServiceImpl entrepriseService;
    private final static Logger l = LogManager.getLogger(EmployeTest.class);
/*
    @Test
    void ajoutEmploye() {
        Employe employe = new Employe("taherarchene", "benletaief", "benletaief.taherarchene@esprit.tn", true, Role.CHEF_DEPARTEMENT);
        employeService.ajouterEmploye(employe);
        long start = System.currentTimeMillis();
        long elapsedTime = System.currentTimeMillis() - start;
        l.info("Method execution time: " + elapsedTime + " milliseconds.");
        l.info("l'employé est ajouté");
         employeService.deleteEmployeById(employe.getId());
    }
*/
    
    @Test
    void getNombreEmploye() {
        int nombre = employeService.getNombreEmployeJPQL();
        Employe employe = new Employe("taherarchene", "benletaief", "benletaief.taherarchene@esprit.tn", true, Role.CHEF_DEPARTEMENT);
        employeService.ajouterEmploye(employe);
        int secondNombre = employeService.getNombreEmployeJPQL();
        Assertions.assertNotEquals(nombre, secondNombre);
        l.info("le nombre des employés est : " + nombre);
        employeService.deleteEmployeById(employe.getId());
    }



  
  

    @Test
    void DeleteEmploye() {
    	Employe employe = new Employe("taherarchene", "benletaief", "benletaief.taherarchene@esprit.tn", true, Role.CHEF_DEPARTEMENT);
        employeService.ajouterEmploye(employe);
        boolean existBeforeDelete = employeRepository.findById(employe.getId()).isPresent();
        Assert.assertTrue(existBeforeDelete);
        employeService.deleteEmployeById(employe.getId());
        boolean existAfterDelete = employeRepository.findById(employe.getId()).isPresent();
        Assert.assertFalse(existAfterDelete);
        l.info("L'employé est supprimé");
    }

  
}