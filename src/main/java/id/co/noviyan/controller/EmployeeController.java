package id.co.noviyan.controller;

import id.co.noviyan.model.Employee;
import id.co.noviyan.model.dto.request.EmployeeRequest;
import id.co.noviyan.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/employee")
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;

    @GET
    public Response getAll() {
        return Response.ok(employeeService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(employeeService.getById(id)).build();
    }

    @POST
    public Response create(EmployeeRequest employee) {
        return Response.status(Response.Status.CREATED)
                .entity(employeeService.create(employee))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Employee employee) {
        return Response.status(Response.Status.CREATED)
                .entity(employeeService.update(id, employee))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return Response.ok(employeeService.delete(id)).build();
    }

}
