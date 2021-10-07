package mx.edu.utez.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import mx.edu.utez.database.ConnectionMysql;

public class ProductsDao {
    
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    PreparedStatement pstm;

    public List<Products> findAll(){
        List<Products> listProducts = new ArrayList<>();

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("SELECT * FROM products;");
            rs = cstm.executeQuery();

            while(rs.next()){
                Products products = new Products();

                products.setProductCode(rs.getString("productCode"));
                products.setProductName(rs.getString("productName"));
                products.setProductLine(rs.getString("productLine"));
                products.setProductScale(rs.getString("productScale"));
                products.setProductVendor(rs.getString("productVendor"));
                products.setProductDescription(rs.getString("productDescription"));
                products.setQuantityInStock(rs.getInt("quantityInStock"));
                products.setBuyPrice(rs.getDouble("buyPrice"));
                products.setMRSP(rs.getDouble("MRSP"));
                
                listProducts.add(products);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return listProducts;
    }

        public Products findByProductCode(String productCode){
            Products products = null;

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("SELECT * FROM products WHERE productCode = ?;");
            cstm.setString(1, productCode);
            rs = cstm.executeQuery();

            if(rs.next()){
                products = new Products();

                products.setProductCode(rs.getString("productCode"));
                products.setProductName(rs.getString("productName"));
                products.setProductLine(rs.getString("productLine"));
                products.setProductScale(rs.getString("productScale"));
                products.setProductVendor(rs.getString("productVendor"));
                products.setProductDescription(rs.getString("productDescription"));
                products.setQuantityInStock(rs.getInt("quantityInStock"));
                products.setBuyPrice(rs.getDouble("buyPrice"));
                products.setMRSP(rs.getDouble("MRSP"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return products;
    }

    public boolean save(Products products, boolean isCreate){
        boolean flag = false;

        try{
            con = ConnectionMysql.getConnection();
            if(isCreate){
                cstm = con.prepareCall("INSERT INTO products (productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP)VALUES(?,?,?,?,?,?,?,?,?);");

                cstm.setString(1, products.getProductCode());
                cstm.setString(2, products.getProductName());
                cstm.setString(3, products.getProductLine());
                cstm.setString(4, products.getProductScale());
                cstm.setString(5, products.getProductVendor());
                cstm.setString(6, products.getProductDescription());
                cstm.setInt(7, products.getQuantityInStock());
                cstm.setDouble(8, products.getQuantityInStock());
                cstm.setDouble(9, products.getMRSP());
            } else {
                cstm = con.prepareCall("UPDATE products SET productName = ?, productLine = ?, productScale = ?, productVendor = ?, productDescription = ?, quantityInStock = ?, buyPrice = ?,  MSRP = ? where productCode = ?;");

                cstm.setString(1, products.getProductName());
                cstm.setString(2, products.getProductLine());
                cstm.setString(3, products.getProductScale());
                cstm.setString(4, products.getProductVendor());
                cstm.setString(5, products.getProductDescription());
                cstm.setInt(6, products.getQuantityInStock());
                cstm.setDouble(7, products.getQuantityInStock());
                cstm.setDouble(8, products.getMRSP());
                cstm.setString(9, products.getProductCode());
            }
            flag = cstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }

    public boolean delete(String productCode){
        boolean flag = false;

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("DELETE FROM products WHERE productCode = ?;");
            cstm.setString(1, productCode);
            flag = cstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }

    public void closeConnection(){
        try{
            if(con != null){
                con.close();
            }
            if(pstm != null){
                pstm.close();
            }
            if(rs != null){
                rs.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
