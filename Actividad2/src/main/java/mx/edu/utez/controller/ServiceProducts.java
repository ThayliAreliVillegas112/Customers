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
import mx.edu.utez.model.Products;
import mx.edu.utez.model.ProductsDao;

@Path("/products")
public class ServiceProducts {
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> getProducts(){
        return new ProductsDao().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Products getCustomers(@PathParam("id") String productCode){
        return new ProductsDao().findByProductCode(productCode);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Products save(MultivaluedMap<String, String> formParams){
        String productCode = formParams.get("productCode").get(0);
        if(new ProductsDao().save(getParams(productCode, formParams), true))
            return new ProductsDao().findByProductCode(productCode);
        return null;
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Products save(@PathParam("id") String productCode, MultivaluedMap <String, String> formParams){
        if(new ProductsDao().save(getParams(productCode, formParams), false))
            return new ProductsDao().findByProductCode(productCode);
        return null;
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteProducts(@PathParam("id") String productCode){
        return new ProductsDao().delete(productCode);
    }

    private Products getParams(String productCode, MultivaluedMap<String, String> formParams){
        
        String productName = formParams.get("productName").get(0);
        String productLine = formParams.get("productLine").get(0);
        String productScale = formParams.get("productScale").get(0);
        String productVendor = formParams.get("productVendor").get(0);
        String productDescrption = formParams.get("productDescrption").get(0);
        int quantityInStock = Integer.parseInt(formParams.get("quantityInStock").get(0));
        double buyPrice = Double.parseDouble(formParams.get("buyPrice").get(0));
        double MSRP = Double.parseDouble(formParams.get("MSRP").get(0));

        Products products = new Products(productCode, productName,productLine, productScale,productVendor,productDescrption, quantityInStock, buyPrice,MSRP);
        System.out.println(products);
        return products;
    }
}
