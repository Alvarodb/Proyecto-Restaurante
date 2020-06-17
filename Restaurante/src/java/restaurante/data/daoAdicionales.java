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
import restaurante.logic.Adicional;
/**
 *
 * @author Álvaro
 */
public class daoAdicionales {
    //select * from adicional a, opcion o, platillo p where a.opcion = o.id and o.platillo = p.id and p.nombre = 'PUFF-PUFF';
    RelDatabase db;
    
    public daoAdicionales(){
        db = new RelDatabase();
    }
    public List<Adicional> adicionalesSearch(Integer id){
        List<Adicional> resultado = new ArrayList<Adicional>();
        try {
            String sql="select a.id,a.nombre,a.precio from adicional a where a.opcion = '%s'";
            sql=String.format(sql,id);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(adicional(rs));
            }
        } catch (SQLException ex) { }
        return resultado;
    }
    private Adicional adicional(ResultSet rs){
        try {
            Adicional add= new Adicional();
            add.setId(Integer.valueOf(rs.getString("id")));
            add.setNombre(rs.getString("nombre"));
            add.setPrecio(Float.valueOf(rs.getString("precio")));
            return add;
        } catch (SQLException ex) {
            return null;
        }
    }
}
