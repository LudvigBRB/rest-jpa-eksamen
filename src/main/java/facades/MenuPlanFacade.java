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
    
    public MenuPlan createMenuPlan(List<DayPlan> dayplans){
        EntityManager em = emf.createEntityManager();
        
        MenuPlan mp = new MenuPlan();
        
        mp.setWeekPlan(dayplans);
        
        try{
            em.getTransaction().begin();
            em.persist(mp);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return mp;
    }
    
}
