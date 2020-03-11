package service;

import DataDao.DaoFood;
import DataDao.DaoPerros;
import DataDao.IDao;
import DataDto.Food;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("food")
public class FoodRest
{
    @Context
    private UriInfo context;
    private final IDao<Food, Integer> dao;

    public FoodRest()
    {
        dao = new DaoFood();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Food> get()
    {
        return dao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Food getbyId(@PathParam("id") int id)
    {
        return dao.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Food post(Food entity)
    {
        dao.insert(entity);

        return entity;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Food put(Food entity)
    {
        dao.update(entity);

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id") int id)
    {
        if (dao.find(id) == null)
        {
            return "{\n"
                    + "\"success\": 1,\n"
                    + "\"description\": \"Not found!"
                    + "\"\n}";
        }
        else
        {
            boolean res = dao.delete(id);

            if (res)
            {
                return "{\n"
                        + "\"success\": 1,\n"
                        + "\"description\": \"Deleted!"
                        + "\"\n}";
            }

            return "{\n"
                    + "\"success\": 0,\n"
                    + "\"description\": \"Error!"
                    + "\"\n}";
        }
    }
}
