package com.dahelka.gbh.service;

import com.dahelka.gbh.domain.EFormatType;
import com.dahelka.gbh.domain.TextFormatter;
import com.dahelka.gbh.entity.Page;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("books")
public class PageFacadeREST extends AbstractFacade<Page> {

    @PersistenceContext(unitName = "LibraryPU")
    private EntityManager em;

    public PageFacadeREST() {
        super(Page.class);
    }

    @GET
    @Path("{idBook}/pages")
    @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
    public Response getNothinYet(@PathParam("idBook") Integer idBook) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("{idBook}/pages/{format}")
    @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
    public Response getHtmlPage(@PathParam("idBook") Integer idBook, @PathParam("format") EFormatType format) {
        Query query = em.createNativeQuery("SELECT * FROM Page p WHERE p.id_book = " + idBook, Page.class);
        List<Page> result = query.getResultList();
        TextFormatter tf = new TextFormatter(result, format);

        return Response.ok(tf.getFormattedText()).build();
    }

    @GET
    @Path("{idBook}/pages/{idPage}/{format}")
    @Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
    public Response findAllByBookId(@PathParam("idBook") Integer idBook, @PathParam("idPage") Integer idPage, @PathParam("format") EFormatType format) {
        Query query = em.createNativeQuery("SELECT * FROM Page p WHERE p.id_book = " + idBook + " AND p.id_page= " + idPage, Page.class);

        List<Page> pages = query.getResultList();
        TextFormatter tf = new TextFormatter(pages, format);
        return Response.ok(tf.getFormattedText()).build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
