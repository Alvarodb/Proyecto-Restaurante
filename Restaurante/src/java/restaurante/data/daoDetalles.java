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
import restaurante.logic.Detalle;

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
}
