package mx.edu.utez.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import mx.edu.utez.model.Offices;
import mx.edu.utez.model.OfficesDao;
import mx.edu.utez.model.ProductLines;
import mx.edu.utez.model.ProductLinesDao;

@Path("/productsLine")
public class ServiceProducLines {
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductLines> getProductLines(){
        return new ProductLinesDao().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductLines getProductLines(@PathParam("id") String productLine){
        return new ProductLinesDao().findByProductLines(productLine);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public ProductLines save(MultivaluedMap<String, String> formParams){
        String productLine = formParams.get("productLine").get(0);
        if(new ProductLinesDao().save(getParams(productLine, formParams), true))
            return new ProductLinesDao().findByProductLines(productLine);
        return null; 
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public ProductLines save(@PathParam("id") String productLine, MultivaluedMap<String, String> formParams){
        if(new ProductLinesDao().save(getParams(productLine, formParams), false))
            return new ProductLinesDao().findByProductLines(productLine);
        return null;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteProductLines(@PathParam("id") String productLine){
        return new ProductLinesDao().delete(productLine);
    }

    private ProductLines getParams(String productLine, MultivaluedMap<String, String> formParams){

        String textDescription = formParams.get("textDescription").get(0);
        String htmlDescription = formParams.get("htmlDescription").get(0);
        

        ProductLines productLines = new ProductLines( productLine, textDescription , htmlDescription );
        System.out.println(productLines);
        return productLines;
    }
}
