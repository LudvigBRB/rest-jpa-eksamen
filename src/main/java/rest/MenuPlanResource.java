package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.DayPlan;
import entities.MenuPlan;
import entities.User;
import errors.MissingInputException;
//import entities.RenameMe;
import utils.EMF_Creator;
import facades.DayPlanFacade;
import facades.MenuPlanFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("menu")
public class MenuPlanResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/eksamen",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final MenuPlanFacade FACADE = MenuPlanFacade.getMenuPlanFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Menu Plan\"}";
    }

    @Path("make")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String makeMenuPlan(String dayplans) throws MissingInputException {
        List<DayPlan> dayplanning = (List<DayPlan>) GSON.fromJson(dayplans, Object.class);
        MenuPlan  mp = FACADE.createMenuPlan(dayplanning);

        return GSON.toJson(mp);
    }
}
