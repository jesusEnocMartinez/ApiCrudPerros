package service;

import DataDao.DaoPerros;
import DataDao.DaoStore;
import DataDao.IDao;
import DataDto.Perros;
import DataDto.Store;
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


@Path("perros")
public class PerrosRest
{
    @Context
    private UriInfo context;
    private final IDao<Perros, Integer> dao;

    public PerrosRest()
    {
        dao = new DaoPerros();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Perros> get()
    {
        return dao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Perros getbyId(@PathParam("id") int id)
    {
        return dao.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Perros post(Perros entity)
    {
        dao.insert(entity);

        return entity;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Perros put(Perros entity)
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
            return "{\"code\": 1, \"message\": \"No encontrado!\"}";
        }
        else
        {
            boolean res = dao.delete(id);

            if (res)
            {
                return "{\"code\": 1, \"message\": \"Borrado!\"}";
            }

            return "{\"code\": 0, \"message\": \"Error!\"}";
        }
    }
}
