package mx.edu.utez.controller;

import mx.edu.utez.model.Customers;
import mx.edu.utez.model.CustomersDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;

@Path("/customers")
public class Service {
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customers> getCustomers(){
        return new CustomersDao().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customers getCustomers(@PathParam("id") int customerNumber){
        return new CustomersDao().findByCustomerNumber(customerNumber);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Customers save(MultivaluedMap<String, String> formParams){
        int customerNumber = Integer.parseInt(formParams.get("customerNumber").get(0));
        if(new CustomersDao().save(getParams(customerNumber, formParams), true))
            return new CustomersDao().findByCustomerNumber(customerNumber);
        return null;
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Customers save(@PathParam("id") int customerNumber, MultivaluedMap<String, String> formParams){
        if(new CustomersDao().save(getParams(customerNumber, formParams), false))
            return new CustomersDao().findByCustomerNumber(customerNumber);
        return null;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCustomer(@PathParam("id") int customerNumber){
        return new CustomersDao().delete(customerNumber);
    }

    private Customers getParams(int customerNumber, MultivaluedMap<String, String> formParams){
        
        String customerName = formParams.get("customerName").get(0);
        String contacLastName = formParams.get("contacLastName").get(0);
        String contacFirstName = formParams.get("contacFirstName").get(0);
        String phone = formParams.get("phone").get(0);
        String addressLine1 = formParams.get("addressLine1").get(0);
        String addressLine2 = formParams.get("addressLine2").get(0);
        String city = formParams.get("city").get(0);
        String state = formParams.get("state").get(0);
        String postalCode = formParams.get("postalCode").get(0);
        String country = formParams.get("country").get(0);
        int salesRepEmployeeNumber = Integer.parseInt(formParams.get("salesRepEmployeeNumber").get(0));
        double creditLimit = Double.parseDouble(formParams.get("creditLimit").get(0));

        Customers customer = new Customers(customerNumber, customerName,contacLastName, contacFirstName,phone,addressLine1, addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit  );
        System.out.println(customer);
        return customer;
    }
}
