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

    public daoPlatillos() {
        db = new RelDatabase();
    }

    public Platillo platilloGet(String id) throws Exception {
        String sql = "select * "
                + "from platillo where id='%s' ";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return platillo(rs);
        } else {
            throw new Exception("Avion no Existe");
        }
    }

    public void PlatilloDelete(Platillo p) throws Exception {
        String sql = "delete from platillo where id='%s'";
        sql = String.format(sql, p.getId());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Platillo no existe");
        }
    }

    public Platillo platilloGetbyName(String name) throws Exception {
        String sql = "select * "
                + "from platillo where nombre='%s' ";
        sql = String.format(sql, name);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return platillo(rs);
        } else {
            return null;
        }
    }

    public void platilloUpdate(Platillo p) throws Exception {
        String sql = "update platillo set nombre='%s', precio='%s', descripcion='%s', categoria='%s' where id='%s'";
        sql = String.format(sql, p.getNombre(), p.getPrecio(), p.getDescripcion(), p.getCategoria().getId(), p.getId());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Platillo no existe");
        }
    }

    public void PlatilloAdd(Platillo p) throws Exception {
        String sql = "insert into platillo (nombre, precio, descripcion, categoria) "
                + "values('%s','%s','%s','%s')";
        sql = String.format(sql, p.getNombre(), p.getPrecio(), p.getDescripcion(), p.getCategoria().getId());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Platillo ya existe");
        }
    }

    public List<Platillo> platillosSearch() throws Exception {
        List<Platillo> resultado = new ArrayList<Platillo>();
        try {
            String sql = "select * "
                    + "from platillo";
            sql = String.format(sql);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(platillo(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public Platillo platillosSearchbyName(String nombre) throws Exception {

        String sql = "select * "
                + "from platillo where nombre='%s'";
        sql = String.format(sql, nombre);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return platillo(rs);
        } else {
            throw new Exception("Platillo no existe");
        }
    }

    public int platilloIdSearch(String platillo) throws Exception {
        int id = 0;
        try {
            String sql = "select id "
                    + "from platillo where nombre='%s'";
            sql = String.format(sql, platillo);
            ResultSet rs = db.executeQuery(sql);
            if (rs.next()) {
                id = Integer.valueOf(rs.getString("id"));
            } else {
                throw new Exception("Platillo no existe");
            }
        } catch (SQLException ex) {
        }
        return id;
    }

    public List<Platillo> platillosSearchCategorias(String nombre) throws Exception {
        List<Platillo> resultado = new ArrayList<Platillo>();
        try {
            String sql = "select * "
                    + "from platillo p, categoria c where p.categoria=c.id and c.nombre='%s'";
            sql = String.format(sql, nombre);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(platillo(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    private Platillo platillo(ResultSet rs) throws Exception {
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
