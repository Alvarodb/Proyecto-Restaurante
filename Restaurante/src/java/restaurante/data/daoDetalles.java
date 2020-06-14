/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.data;
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
     public void DetallesAdd(Detalle d) throws Exception {
        String sql = "insert into detalle(cantidad,orden,platillo) "
                + "values('%s','%s','%s')";
        sql = String.format(sql,d.getCantidad(),d.getOrden().getId(),d.getPlatillo().getId());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Detalle ya existe");
        }
    }
}
