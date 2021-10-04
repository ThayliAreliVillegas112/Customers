package mx.edu.utez.model;

import mx.edu.utez.database.ConnectionMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao {

    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    PreparedStatement pstm;

    public List<Customers> findAll(){
        List<Customers> listEmployees = new ArrayList<>();

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("SELECT * FROM customers;");
            rs = cstm.executeQuery();

            while(rs.next()){
                Customers customers = new Customers();

                customers.setCustomerNumber(rs.getInt("customerNumber"));
                customers.setCustomerName(rs.getString("customerName"));
                customers.setContactLastName(rs.getString("contactLastName"));
                customers.setContactFirstName(rs.getString("contactFirstName"));
                customers.setPhone(rs.getString("phone"));
                customers.setAddressLine1(rs.getString("addressLine1"));
                customers.setAddressLine2(rs.getString("addressLine2"));
                customers.setCity(rs.getString("city"));
                customers.setState(rs.getString("state"));
                customers.setPostalCode(rs.getString("postalCode"));
                customers.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                customers.setCreditLim(rs.getDouble("creditLimit"));

                listEmployees.add(customers);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return listEmployees;
    }

        public Customers findByCustomerNumber(int customerNumber){
            Customers customers = null;

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("SELECT * FROM customers WHERE customerNumber = ?;");
            cstm.setInt(1, customerNumber);
            rs = cstm.executeQuery();

            if(rs.next()){
                customers = new Customers();

                customers.setCustomerNumber(rs.getInt("customerNumber"));
                customers.setCustomerName(rs.getString("customerName"));
                customers.setContactLastName(rs.getString("contactLastName"));
                customers.setContactFirstName(rs.getString("contactFirstName"));
                customers.setPhone(rs.getString("phone"));
                customers.setAddressLine1(rs.getString("addressLine1"));
                customers.setAddressLine2(rs.getString("addressLine2"));
                customers.setCity(rs.getString("city"));
                customers.setState(rs.getString("state"));
                customers.setPostalCode(rs.getString("postalCode"));
                customers.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                customers.setCreditLim(rs.getDouble("creditLimit"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return customers;
    }

    public boolean save(Customers customers, boolean isCreate){
        boolean flag = false;

        try{
            con = ConnectionMysql.getConnection();
            if(isCreate){
                cstm = con.prepareCall("INSERT INTO customers (customerNumber,customerName, contacLastName, contacFirstName, phone, addressLine1, addressLine2, city, state , postalCode, country, salesRepEmployeeNumber, creditLimit)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");

                cstm.setInt(1, customers.getCustomerNumber());
                cstm.setString(2, customers.getCustomerName());
                cstm.setString(3, customers.getContactLastName());
                cstm.setString(4, customers.getContactFirstName());
                cstm.setString(5, customers.getPhone());
                cstm.setString(6, customers.getAddressLine1());
                cstm.setString(7, customers.getAddressLine2());
                cstm.setString(8,customers.getCity());
                cstm.setString(9,customers.getState());
                cstm.setString(10,customers.getPostalCode());
                cstm.setString(11,customers.getCountry());
                cstm.setInt(12, customers.getSalesRepEmployeeNumber());
                cstm.setDouble(13, customers.getCreditLim());
            } else {
                cstm = con.prepareCall("UPDATE customers SET customerName = ?, contacLastName = ?, contacFirstName = ?, phone = ?, addressLine1 = ?, addressLine2 = ?, city = ?, state = ?, postalCode = ?, country = ?, salesRepEmployeeNumber = ?, creditLimit = ? WHERE customerNumber = ?;");

                cstm.setString(1, customers.getCustomerName());
                cstm.setString(2, customers.getContactLastName());
                cstm.setString(3, customers.getContactFirstName());
                cstm.setString(4, customers.getPhone());
                cstm.setString(5, customers.getAddressLine1());
                cstm.setString(6, customers.getAddressLine2());
                cstm.setString(7,customers.getCity());
                cstm.setString(8,customers.getState());
                cstm.setString(9,customers.getPostalCode());
                cstm.setString(10,customers.getCountry());
                cstm.setInt(11, customers.getSalesRepEmployeeNumber());
                cstm.setDouble(12, customers.getCreditLim());
                cstm.setInt(13, customers.getCustomerNumber());
            }
            flag = cstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }

    public boolean delete(int customerNumber){
        boolean flag = false;

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("DELETE FROM customers WHERE customerNumber = ?;");
            cstm.setInt(1, customerNumber);
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
