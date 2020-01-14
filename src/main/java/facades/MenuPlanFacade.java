/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.DayPlan;
import entities.MenuPlan;
import errors.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Ludvig
 */
public class MenuPlanFacade {

    private static MenuPlanFacade instance;
    private static EntityManagerFactory emf;

    public static MenuPlanFacade getMenuPlanFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MenuPlanFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<MenuPlan> getAllMenus() throws NotFoundException {
        EntityManager em = getEntityManager();
        List<MenuPlan> allMenus = new ArrayList<MenuPlan>();
        try {
            allMenus = em.createQuery("select mp from MenuPlan mp", MenuPlan.class).getResultList();

            if (allMenus.size() == 0 || allMenus == null) {
                throw new NotFoundException("No menus found");
            }
        } finally {
            em.close();
        }
        return allMenus;
    }

    public MenuPlan createMenuPlan(List<DayPlan> dayplans) {
        EntityManager em = emf.createEntityManager();

        MenuPlan mp = new MenuPlan();

        mp.setWeekPlan(dayplans);

        try {
            em.getTransaction().begin();
            em.persist(mp);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return mp;
    }

    public MenuPlan getMenuPlan(int id) throws NotFoundException {
        EntityManager em = getMenuPlanFacade(emf).getEntityManager();

        try {
            //Person p = em.find(Person.class, id);
            MenuPlan mp = em.createQuery("select mp FROM MenuPlan mp where mp.id = " + id, MenuPlan.class).getSingleResult();

            if (mp == null) {
                throw new NotFoundException("No MenuPlan found with the provided id");
            }

            return mp;
        } finally {
            em.close();
        }
    }

    public MenuPlan editMenuPlan(MenuPlan mp) throws NotFoundException {
        EntityManager em = getMenuPlanFacade(emf).getEntityManager();

        if (mp == null) {
            throw new NotFoundException("No menuplan found");
        }

        try {
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE MenuPlan mp SET mp.id = '" + mp.getId() + "', mp.message = '" + mp.getMessage()
                    + "' WHERE mp.id = :id", MenuPlan.class);
            query.setParameter("id", mp.getId());
            query.executeUpdate();
            em.getTransaction().commit();
            return mp;
        } finally {
            em.close();
        }
    }

    public MenuPlan deleteMenuPlan(int id) throws NotFoundException {
        EntityManager em = getMenuPlanFacade(emf).getEntityManager();

        try {
            //Person checkPerson = em.find(Person.class, id);
            em.getTransaction().begin();
            MenuPlan mp = em.createQuery("select mp FROM MenuPlan mp where mp.id = :id", MenuPlan.class).setParameter("id", id).getSingleResult();

            if (mp == null) {
                throw new NotFoundException("No menuplan found with provided id");
            }

            em.createQuery("DELETE FROM MenuPlan mp where mp.id = :id").setParameter("id", id).executeUpdate();
            em.getTransaction().commit();

            return mp;
        } finally {
            em.close();
        }
    }
}
