package com.dahelka.gbh.service;

import com.dahelka.gbh.dao.IBookDao;
import com.dahelka.gbh.entity.Book;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("books")
public class BookFacadeREST extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "LibraryPU")
    private EntityManager em;

    public BookFacadeREST() {
        super(Book.class);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Book find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Book> findAll() {
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
