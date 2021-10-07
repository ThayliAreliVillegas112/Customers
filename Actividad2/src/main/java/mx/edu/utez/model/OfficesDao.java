package mx.edu.utez.model;

import mx.edu.utez.database.ConnectionMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfficesDao {

    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    PreparedStatement pstm;

    public List<Offices> findAll(){
        List<Offices> listOffices = new ArrayList<>();

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("SELECT * FROM offices;");
            rs = cstm.executeQuery();

            while(rs.next()){
                Offices offices = new Offices();

                offices.setOfficeCode(rs.getString("officeCode"));
                offices.setCity(rs.getString("city"));
                offices.setPhone(rs.getString("phone"));
                offices.setAddressLine1(rs.getString("addressLine1"));
                offices.setAddressLine2(rs.getString("addressLine2"));
                offices.setState(rs.getString("state"));
                offices.setCity(rs.getString("city"));
                offices.setPostalCode(rs.getString("postalCode"));
                offices.setTerritory(rs.getString("territory"));

                listOffices.add(offices);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return listOffices;
    }

    public Offices findByOfficeCode(String officeCode){
        Offices offices = null;

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("SELECT * FROM offices WHERE officeCode = ?;");
            cstm.setString(1, officeCode);
            rs = cstm.executeQuery();

            if(rs.next()){
                offices = new Offices();

                offices.setOfficeCode(rs.getString("officeCode"));
                offices.setCity(rs.getString("city"));
                offices.setPhone(rs.getString("phone"));
                offices.setAddressLine1(rs.getString("addressLine1"));
                offices.setAddressLine2(rs.getString("addressLine2"));
                offices.setState(rs.getString("state"));
                offices.setCity(rs.getString("city"));
                offices.setPostalCode(rs.getString("postalCode"));
                offices.setTerritory(rs.getString("territory"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return offices;
    }

    public boolean save(Offices offices, boolean isCreate){
        boolean flag = false;

        try{
            con = ConnectionMysql.getConnection();
            if(isCreate){
                cstm = con.prepareCall("INSERT INTO offices (officeCode,city, phone, addressLine1, addressLine2, state , country, postalCode, territory )VALUES(?,?,?,?,?,?,?,?,?);");

                cstm.setString(1, offices.getOfficeCode());
                cstm.setString(2,offices.getCity());
                cstm.setString(3, offices.getPhone());
                cstm.setString(4, offices.getAddressLine1());
                cstm.setString(5, offices.getAddressLine2());
                cstm.setString(6,offices.getState());
                cstm.setString(7,offices.getCountry());
                cstm.setString(8,offices.getPostalCode());
                cstm.setString(9, offices.getTerritory());
            } else {
                cstm = con.prepareCall("UPDATE offices SET OfficeCode = ?, city= ?, phone = ?, addressLine1 = ?, addressLine2 = ?, state = ?, country = ?,  postalCode = ?, territory = ? WHERE officeCode = ?;");

                
                cstm.setString(1,offices.getCity());
                cstm.setString(2, offices.getPhone());
                cstm.setString(3, offices.getAddressLine1());
                cstm.setString(4, offices.getAddressLine2());
                cstm.setString(5,offices.getState());
                cstm.setString(6,offices.getCountry());
                cstm.setString(7,offices.getPostalCode());
                cstm.setString(8, offices.getTerritory());
                cstm.setString(9, offices.getOfficeCode());
            }
            flag = cstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }

    public boolean delete(String officeCode){
        boolean flag = false;

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("DELETE FROM offices WHERE officeCode = ?;");
            cstm.setString(1, officeCode);
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
