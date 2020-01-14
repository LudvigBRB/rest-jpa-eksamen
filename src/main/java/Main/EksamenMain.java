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
import java.util.Arrays;
import java.util.List;
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
    //private static final UserFacade FACADE = InformationFacade.getInformationFacade(EMF);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Persistence.generateSchema("pu", null);
        EntityManager em = EMF.createEntityManager();
        
        User u1 = new User("Freja", "gylving");
        User u2 = new User("Frej", "Gyngre");
        User u3 = new User("Frig", "gungre");
        
        List<String> preparations1 = Arrays.asList("wef", "qefe", "2dqw");
        List<String> preparations2 = Arrays.asList("wwfwef", "qefeerfe", "df2dqw");
        
        Recipe r1 = new Recipe("ris", "10", preparations1);
        Recipe r2 = new Recipe("ost", "100", preparations1);
        Recipe r3 = new Recipe("is", "120", preparations1);
        Recipe r4 = new Recipe("mel", "20", preparations2);
        Recipe r5 = new Recipe("citron", "100", preparations2);
        Recipe r6 = new Recipe("sukker", "12", preparations2);
        
        
        DayPlan dp1 = new DayPlan(r1, "mandag");
        DayPlan dp2 = new DayPlan(r2, "tirsdag");
        DayPlan dp3 = new DayPlan(r3, "onsdag");
        DayPlan dp4 = new DayPlan(r4, "torsdag");
        DayPlan dp5 = new DayPlan(r5, "fredag");
        DayPlan dp6 = new DayPlan(r6, "lrødag");
        
        MenuPlan mp1 = new MenuPlan();
        MenuPlan mp2 = new MenuPlan();
        
        mp1.setMessage("hello");
        mp1.addDayPlan(dp1);
        mp1.addDayPlan(dp2);
        mp1.addDayPlan(dp3);
        
        mp2.setMessage("yipee");
        mp2.addDayPlan(dp4);
        mp2.addDayPlan(dp5);
        mp2.addDayPlan(dp6);
        
         try {
            em.getTransaction().begin();
            em.persist(u1);
            em.persist(u2);
            em.persist(u3);
            
            em.persist(dp1);
            em.persist(dp2);
            em.persist(dp3);
            em.persist(mp1);
            
            em.persist(dp4);
            em.persist(dp5);
            em.persist(dp6);
            em.persist(mp2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
