package id.co.noviyan.controller;

import id.co.noviyan.model.Country;
import id.co.noviyan.service.CountryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/country")
public class CountryController {

    @Inject
    CountryService countryService;

    @GET
    public Response getAll() {
        return Response.ok(countryService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(countryService.getById(id)).build();
    }

    @POST
    public Response create(Country country) {
        return Response.status(Response.Status.CREATED)
                .entity(countryService.create(country))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Country country) {
        return Response.ok(countryService.update(id, country)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return Response.ok(countryService.delete(id)).build();
    }

}
