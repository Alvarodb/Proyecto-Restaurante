/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import restaurante.logic.Detalle;
import restaurante.logic.Model;


/**
 *
 * @author Álvaro
 */
public class daoDetalles {

    RelDatabase db;

    public daoDetalles() {
        db = new RelDatabase();
    }

    public int DetallesAdd(Detalle d) throws Exception {
        int generatedKey = 0;
        try {
            String sql = "insert into detalle(cantidad,orden,platillo) "
                    + "values('%s','%s','%s')";
            sql = String.format(sql, d.getCantidad(), d.getOrden().getId(), d.getPlatillo().getId());
            PreparedStatement stmt = db.cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return generatedKey;
    }
     public List<Detalle> detallesSearch(int id) throws Exception{
        List<Detalle> resultado = new ArrayList<>();
        try {
            String sql="select * from detalle where orden = '%s'";
            sql=String.format(sql,id);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(detalle(rs));
            }
        } catch (SQLException ex) { }
        return resultado;
    }
     private Detalle detalle(ResultSet rs) throws Exception {
        try {
            Detalle d = new Detalle();
            d.setId(Integer.valueOf(rs.getString("id")));
            d.setCantidad(Integer.valueOf(rs.getString("cantidad")));          
            d.setPlatillo(Model.instance().buscarPlatiiloId(rs.getString("Platillo")));
                       
            return d;            
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
