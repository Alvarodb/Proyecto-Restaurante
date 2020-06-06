package restaurante.data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import restaurante.logic.Platillo;

/**
 *
 * @author Álvaro
 */
public class daoPlatillos {
    RelDatabase db;
    
    public daoPlatillos(){
        db = new RelDatabase();
    }
    public List<Platillo> platillosSearch() throws Exception{
        List<Platillo> resultado = new ArrayList<Platillo>();
        try {
            String sql="select * "+
                "from platillo";
            sql=String.format(sql);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(platillo(rs));
            }
        } catch (SQLException ex) { }
        return resultado;
    }
    public List<Platillo> platillosSearchCategorias(String nombre) throws Exception{
        List<Platillo> resultado = new ArrayList<Platillo>();
        try {
            String sql="select * "+
                "from platillo p, categoria c where p.categoria=c.id and c.nombre='%s'";
            sql=String.format(sql,nombre);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(platillo(rs));
            }
        } catch (SQLException ex) { }
        return resultado;
    }
    private Platillo platillo(ResultSet rs) throws Exception{
        try {
            Platillo plat = new Platillo();
            plat.setId(Integer.valueOf(rs.getString("id")));
            plat.setNombre(rs.getString("nombre"));
            plat.setPrecio(Float.valueOf(rs.getString("precio")));
            plat.setDescripcion(rs.getString("descripcion"));
            plat.setCategoria(new restaurante.data.daoCategorias().categoriasFind(Integer.valueOf(rs.getString("categoria"))));
            //ordenes
            //adicionales
            return plat;
        } catch (SQLException ex) {
            return null;
        }
    }
}
