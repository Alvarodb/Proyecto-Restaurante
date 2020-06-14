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

    public int OrdenAdd(Orden d) throws Exception {
        int generatedKey = 0;
        try {
           
            String sql = "insert into orden(tipo,fechahora,estado,metodo_pago,total,usuario) "
                    + "values('%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, d.getTipo(), d.getFechahora(), d.getEstado(), d.getMetodoPago(), d.getTotal(), d.getUsuario().getNombreUsuario());

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
}
