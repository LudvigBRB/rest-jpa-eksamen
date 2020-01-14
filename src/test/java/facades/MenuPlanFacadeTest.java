package facades;

import entities.DayPlan;
import entities.MenuPlan;
import utils.EMF_Creator;
import entities.User;
import errors.MissingInputException;
import errors.NotFoundException;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MenuPlanFacadeTest {

    private static EntityManagerFactory emf;
    private static MenuPlanFacade facade;

    public MenuPlanFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MenuPlanFacade.getMenuPlanFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = MenuPlanFacade.getMenuPlanFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("MenuPlan.deleteAllRows").executeUpdate();
            em.persist(new MenuPlan());
            em.persist(new MenuPlan());

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
//    @Test
//    public void testAFacadeMethod() {
//        assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
//    }

    @Test
    public void testCreateMenuPlan() throws NotFoundException, MissingInputException {               
        DayPlan dp1 = new DayPlan();
        DayPlan dp2 = new DayPlan();
        DayPlan dp3 = new DayPlan();
        DayPlan dp4 = new DayPlan();
        DayPlan dp5 = new DayPlan();
        DayPlan dp6 = new DayPlan();
        DayPlan dp7 = new DayPlan();
        
        List<DayPlan> dpList = Arrays.asList(dp1, dp2, dp3, dp4, dp5, dp6, dp7);
        
        MenuPlan mp = facade.createMenuPlan(dpList);
        
        Assertions.assertNotNull(mp);
    }

}
