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
import restaurante.logic.Opcion;
/**
 *
 * @author Álvaro
 */
public class daoOpcion {
    RelDatabase db;
    
    public daoOpcion(){
        db = new RelDatabase();
    }
    public List<Opcion> opcionesSearch(String nombre){
        List<Opcion> resultado = new ArrayList<Opcion>();
        try {
            String sql="select * from opcion o, platillo p where o.platillo = p.id and p.nombre = '%s'";
            sql=String.format(sql,nombre);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(opcion(rs));
            }
        } catch (SQLException ex) { }
        return resultado;
    }
    private Opcion opcion(ResultSet rs){
        try {
            Opcion opc = new Opcion();
            opc.setId(Integer.valueOf(rs.getString("id")));
            opc.setNombre(rs.getString("nombre"));
            opc.setTipo(rs.getString("tipo"));
            opc.setRequerida(Short.valueOf(rs.getString("requerida")));
            opc.setAdicionalList(new restaurante.data.daoAdicionales().adicionalesSearch(Integer.valueOf(rs.getString("id")))); //adicionales para cada opcion
            return opc;
        } catch (SQLException ex) {
            return null;
        }
    }
}
