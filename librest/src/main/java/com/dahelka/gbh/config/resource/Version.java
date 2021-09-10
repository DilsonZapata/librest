package com.dahelka.gbh.config.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("version")
public class Version {
    @GET
    public Response ping(){
        return Response.ok("v1.0.1").build();
    }
}

