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
      public void categoriaUpdate(Categoria c) throws Exception{
        String sql="update categoria set nombre='%s' where id='%s'";
        sql=String.format(sql,c.getNombre(),c.getId());        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Categoria no existe");
        }
    }
    public void CategoriaAdd(Categoria c) throws Exception {
        String sql = "insert into categoria (nombre) "
                + "values('%s')";
        sql = String.format(sql, c.getNombre());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Categoria ya existe");
        }
    }
    public void CategoriaDelete(Categoria p) throws Exception{
        String sql="delete from categoria where id='%s'";
        sql = String.format(sql,p.getId());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Categoria no existe");
        }
    }
     public Categoria categoriasFindbyName(String name) throws Exception{
        String sql="select * "+
                "from categoria where nombre='%s'";
        sql = String.format(sql,name);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return  categoria(rs);
        }
        else{
            return null;
        }
    }    
    private Categoria categoria(ResultSet rs){
        try {
            Categoria cat= new Categoria();
            cat.setId(Integer.valueOf(rs.getString("id")));
            cat.setNombre(rs.getString("nombre"));
            return cat;
        } catch (SQLException ex) {
            return null;
        }
    }
}
