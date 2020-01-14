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
        
        List<String> preparations = Arrays.asList("wef", "qefe", "2dqw");
        
        Recipe r1 = new Recipe("ris", "10", preparations);
        Recipe r2 = new Recipe("ost", "100", preparations);
        Recipe r3 = new Recipe("is", "120", preparations);
        
        DayPlan dp1 = new DayPlan(r1, "mandag");
        DayPlan dp2 = new DayPlan(r2, "tirsdag");
        DayPlan dp3 = new DayPlan(r3, "onsdag");
        
        MenuPlan mp = new MenuPlan();
        
        mp.setMessage("hello");
        mp.addDayPlan(dp1);
        mp.addDayPlan(dp2);
        mp.addDayPlan(dp3);
        
         try {
            em.getTransaction().begin();
            em.persist(u1);
            em.persist(u2);
            em.persist(u3);
            em.persist(dp1);
            em.persist(dp2);
            em.persist(dp3);
            em.persist(mp);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
