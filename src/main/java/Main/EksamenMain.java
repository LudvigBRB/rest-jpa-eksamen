/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import entities.DayPlan;
import entities.MenuPlan;
import entities.Recipe;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utils.EMF_Creator;
import entities.User;
import errors.NotFoundException;
import facades.MenuPlanFacade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;

/**
 *
 * @author Ludvig
 */
public class EksamenMain {
    
        private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/eksamen",
            "dev",
            "ax2",
            EMF_Creator.Strategy.DROP_AND_CREATE); //DROP_AND_CREATE
    private static final MenuPlanFacade FACADE = MenuPlanFacade.getMenuPlanFacade(EMF);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotFoundException {
        //Persistence.generateSchema("pu", null);
        EntityManager em = EMF.createEntityManager();
        
        User u1 = new User("Freja", "gylving");
        User u2 = new User("Frej", "Gyngre");
        User u3 = new User("Frig", "gungre");
        
        List<String> preparations1 = Arrays.asList("wef", "qefe", "dqw");
        List<String> preparations2 = Arrays.asList("wwfwef", "qefeerfe", "dfdqw");
        List<String> preparations3 = Arrays.asList("wewff", "sdfe", "sdfvcqw");
        List<String> preparations4 = Arrays.asList("wwfwef", "qefeerfe", "oooow");
        List<String> preparations5 = Arrays.asList("weiiif", "qefooooe", "pppdqw");
        List<String> preparations6 = Arrays.asList("wwiiiif", "qeiiiiife", "dinvofinqw");
        
        Recipe r1 = new Recipe("ris", "10", preparations1);
        Recipe r2 = new Recipe("ost", "100", preparations2);
        Recipe r3 = new Recipe("is", "120", preparations3);
        Recipe r4 = new Recipe("mel", "20", preparations4);
        Recipe r5 = new Recipe("citron", "100", preparations5);
        Recipe r6 = new Recipe("sukker", "12", preparations6);
                
        DayPlan dp1 = new DayPlan(r1, "mandag");
        DayPlan dp2 = new DayPlan(r2, "tirsdag");
        DayPlan dp3 = new DayPlan(r3, "onsdag");
        DayPlan dp4 = new DayPlan(r4, "torsdag");
        DayPlan dp5 = new DayPlan(r5, "fredag");
        DayPlan dp6 = new DayPlan(r6, "lørdag");
        DayPlan dp7 = new DayPlan(r6, "søndag");        
        
        MenuPlan mp1 = new MenuPlan();
        mp1.setMessage("Sund mad er godt");
        MenuPlan mp2 = new MenuPlan();
        mp2.setMessage("Lav dejlig mad");
        MenuPlan mp3 = new MenuPlan();
        mp3.setMessage("Nam nam nam");
        
        mp1.addDayPlan(dp1);
        mp1.addDayPlan(dp2);
        mp1.addDayPlan(dp3);
        mp1.addDayPlan(dp4);
        
        mp2.addDayPlan(dp4);
        mp2.addDayPlan(dp5);
        mp2.addDayPlan(dp6);
        
        mp3.addDayPlan(dp7);
        
        u1.addMenuPlan(mp1);
        u2.addMenuPlan(mp2);
        u3.addMenuPlan(mp3);
        
         try {
            em.getTransaction().begin();
            em.persist(u1);
            em.persist(u2);
            em.persist(u3);
            
            em.persist(r1);
            em.persist(r2);
            em.persist(r3);
            em.persist(r4);
            em.persist(r5);
            em.persist(r6);
            
            em.persist(dp1);
            em.persist(dp2);
            em.persist(dp3);
            em.persist(dp4);
            em.persist(mp1);
            
            em.persist(dp4);
            em.persist(dp5);
            em.persist(dp6);
            em.persist(mp2);
            
            em.persist(dp7);
            em.persist(mp3);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
