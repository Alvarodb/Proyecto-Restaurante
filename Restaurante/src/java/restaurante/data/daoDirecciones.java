/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import restaurante.logic.Direccion;

/**
 *
 * @author Álvaro
 */
public class daoDirecciones {

    RelDatabase db;
    
    public daoDirecciones(){
        db = new RelDatabase();
    }
    
    public void direccionAdd(Direccion d, String nombre) throws Exception {
        String sql="insert into direccion(Usuario,address1,address2,city,state,postcode,country) "+
                "values('%s','%s','%s','%s','%s','%s','%s')";
        sql=String.format(sql,nombre,d.getAddress1(),d.getAddress2(),d.getCity(),d.getState(),d.getPostcode(),d.getCountry());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Direccion ya existe");
        }
    }
    public void direccionUpdate(Direccion dir) throws Exception{
        String sql="update direccion set address1='%s',address2='%s',city='%s',state='%s',postcode='%s',country='%s' "+
                "where id='%s'";
        sql=String.format(sql,dir.getAddress1(),dir.getAddress2(),dir.getCity(),dir.getState(),dir.getPostcode(),dir.getCountry(),dir.getId());   
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Direccion no existe");
        }
    }
    public List<Direccion> direccionesSearch(String usuario){
        List<Direccion> resultado = new ArrayList<Direccion>();
        try {
            String sql="select * from direccion where usuario = '%s'";
            sql=String.format(sql,usuario);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(direccion(rs));
            }
        } catch (SQLException ex) { }
        return resultado;
    }
    private Direccion direccion(ResultSet rs){
        try {
            Direccion dir = new Direccion();
            dir.setId(Integer.valueOf(rs.getString("id")));
            dir.setAddress1(rs.getString("address1"));
            dir.setAddress2(rs.getString("address2"));
            dir.setCity(rs.getString("city"));
            dir.setCountry(rs.getString("country"));
            dir.setPostcode(rs.getString("postcode"));
            dir.setState(rs.getString("state"));
            return dir;
        } catch (SQLException ex) {
            return null;
        }
    }
}
