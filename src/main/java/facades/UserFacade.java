/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.User;
import entities.MenuPlan;
import errors.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ludvig
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User userGetMenuPlan(String username, long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        User user;
        MenuPlan menuPlan;

        try {
            em.getTransaction().begin();
            user = em.find(User.class, username);
            menuPlan = em.find(MenuPlan.class, id);
            user.addMenuPlan(menuPlan);
            em.merge(user);
            em.getTransaction().commit();
            if (user == null || menuPlan == null) {
                throw new NotFoundException("User or menuplan not found");
            }
            return user;
        } finally {
            em.close();
        }
    }

}
