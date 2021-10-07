package mx.edu.utez.controller;

import mx.edu.utez.model.Customers;
import mx.edu.utez.model.CustomersDao;
import mx.edu.utez.model.Offices;
import mx.edu.utez.model.OfficesDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/offices")
public class ServiceOffices {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offices> getOffices(){
        return new OfficesDao().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Offices getOffices(@PathParam("id") String officeCode){
        return new OfficesDao().findByOfficeCode(officeCode);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Offices save(MultivaluedMap<String, String> formParams){
        String officeCode = formParams.get("officeCode").get(0);
        if(new OfficesDao().save(getParams(officeCode, formParams), true))
            return new OfficesDao().findByOfficeCode(officeCode);
        return null;
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Offices save(@PathParam("id") String officeCode, MultivaluedMap<String, String> formParams){
        if(new OfficesDao().save(getParams(officeCode, formParams), false))
            return new OfficesDao().findByOfficeCode(officeCode);
        return null;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteOffices(@PathParam("id") String officeCode){
        return new OfficesDao().delete(officeCode);
    }

    private Offices getParams(String officeCode, MultivaluedMap<String, String> formParams){

        String city = formParams.get("city").get(0);
        String phone = formParams.get("phone").get(0);
        String addressLine1 = formParams.get("addressLine1").get(0);
        String addressLine2 = formParams.get("addressLine2").get(0);
        String state = formParams.get("state").get(0);
        String country = formParams.get("country").get(0);
        String postalCode = formParams.get("postalCode").get(0);
        String territory = formParams.get("territory").get(0);

        Offices offices = new Offices( officeCode, city , phone,addressLine1, addressLine2,state,country,postalCode,territory  );
        System.out.println(offices);
        return offices;
    }
}
