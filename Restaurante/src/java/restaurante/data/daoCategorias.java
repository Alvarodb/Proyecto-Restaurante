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
import restaurante.logic.Categoria;
/**
 *
 * @author Álvaro
 */
public class daoCategorias {
    RelDatabase db;
    
    public daoCategorias(){
        db = new RelDatabase();
    }
    public Categoria categoriasFind(Integer cat) throws Exception{
        String sql="select * "+
                "from categoria where id='%s'";
        sql = String.format(sql,cat);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return  categoria(rs);
        }
        else{
            throw new Exception ("Categoria no existe");
        }
    }
    public List<Categoria> categoriasSearch(){
        List<Categoria> resultado = new ArrayList<Categoria>();
        try {
            String sql="select * "+
                "from categoria";
            sql=String.format(sql);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(categoria(rs));
            }
        } catch (SQLException ex) { }
        return resultado;
    }
    private Categoria categoria(ResultSet rs){
        try {
            Categoria cat= new Categoria();
            cat.setNombre(rs.getString("nombre"));
            return cat;
        } catch (SQLException ex) {
            return null;
        }
    }
}
