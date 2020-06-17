/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import restaurante.logic.Orden;

/**
 *
 * @author Álvaro
 */
public class daoOrden {

    RelDatabase db;

    public daoOrden() {
        db = new RelDatabase();
    }
    public List<Orden> ordenesSearch(String nombre) throws Exception{
        List<Orden> resultado = new ArrayList<Orden>();
        try {
            String sql="select * "+
                "from orden where usuario='%s'";
            sql=String.format(sql,nombre);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(orden(rs));
            }
        } catch (SQLException ex) { }
        return resultado;
    }

    public int OrdenAdd(Orden d) throws Exception {
        int generatedKey = 0;
        try {
           
            String sql = "insert into orden(tipo,fechahora,estado,metodo_pago,total,direccion,envio,usuario) "
                    + "values('%s','%s','%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, d.getTipo(), d.getFechahora(), d.getEstado(), d.getMetodoPago(), d.getTotal(), d.getDireccion(),d.getEnvio(),d.getUsuario().getNombreUsuario());

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
    private Orden orden(ResultSet rs) throws Exception{
        try {
            Orden ord = new Orden();
            ord.setId(Integer.valueOf(rs.getString("id")));
            ord.setDireccion(rs.getString("direccion"));
            ord.setEstado(rs.getString("estado"));
            ord.setTotal(Float.valueOf(rs.getString("total")));
            return ord;
        } catch (SQLException ex) {
            return null;
        }
    }
}
