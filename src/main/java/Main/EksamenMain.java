/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utils.EMF_Creator;
import entities.User;
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
            EMF_Creator.Strategy.CREATE); //DROP_AND_CREATE
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
        
         try {
            em.getTransaction().begin();
            em.persist(u1);
            em.persist(u2);
            em.persist(u3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
