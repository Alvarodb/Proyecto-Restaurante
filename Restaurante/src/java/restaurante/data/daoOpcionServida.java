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



/**
 *
 * @author Álvaro
 */
public class daoOpcionServida {
    
    RelDatabase db;

    public daoOpcionServida() {
        db = new RelDatabase();
    }

    public int opcionServidaAdd(int opc,int det) throws Exception {        
        int generatedKey = 0;
        try {
            String sql="insert into opcionServida (opcion,detalle) "+
                "values('%s','%s')";
            sql = String.format(sql,opc,det);
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
