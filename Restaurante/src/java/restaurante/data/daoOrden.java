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
import restaurante.logic.Usuario;

/**
 *
 * @author Álvaro
 */
public class daoOrden {

    RelDatabase db;

    public daoOrden() {
        db = new RelDatabase();
    }

    public List<Orden> ordenesSearch(String nombre) throws Exception {
        List<Orden> resultado = new ArrayList<Orden>();
        try {
            String sql = "select * "
                    + "from orden where usuario='%s'";
            sql = String.format(sql, nombre);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(orden(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public int OrdenAdd(Orden d) throws Exception {
        int generatedKey = 0;
        try {

            String sql = "insert into orden(tipo,fechahora,estado,metodo_pago,total,direccion,envio,usuario) "
                    + "values('%s','%s','%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, d.getTipo(), d.getFechahora(), d.getEstado(), d.getMetodoPago(), d.getTotal(), d.getDireccion(), d.getEnvio(), d.getUsuario().getNombreUsuario());

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

    public List<Orden> ordenes() throws Exception {
        List<Orden> resultado = new ArrayList<Orden>();
        try {
            String sql = "select * "
                    + "from orden ";
            sql = String.format(sql);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(orden(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public Orden searchOrdenbyId(String id) throws Exception {
        Orden resultado = new Orden();
        try {
            String sql = "select * "
                    + "from orden where id='%s'";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if (rs.next()) {
                return orden(rs);
            } else {
                return null;
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    private Orden orden(ResultSet rs) throws Exception{
        try {
            Orden ord = new Orden();
            ord.setId(Integer.valueOf(rs.getString("id")));
            ord.setDireccion(rs.getString("direccion"));
            ord.setEstado(rs.getString("estado"));
            ord.setTotal(Float.valueOf(rs.getString("total")));
            ord.setTipo(rs.getString("tipo"));
            ord.setMetodoPago(rs.getString("metodo_pago"));
            ord.setUsuario(new Usuario(rs.getString("usuario")));
            
 
            DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");            
            java.util.Date fechahora= format.parse(rs.getString("fechahora"));          
            ord.setFechahora(fechahora);
            
            try{
            java.util.Date fechahoraentrega= format.parse(rs.getString("fechahoraentrega"));           
            ord.setFechahoraentrega(fechahoraentrega);
            }
            catch(Exception x){}
            
            return ord;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
