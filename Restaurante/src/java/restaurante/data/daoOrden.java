/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.data;
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

    public void OrdenAdd(Orden d) throws Exception {
        String sql = "insert into orden(tipo,fechahora,estado,metodo_pago,total,usuario) "
                + "values('%s','%s','%s','%s','%s','%s')";
        sql = String.format(sql,d.getTipo(),d.getFechahora(),d.getEstado(),d.getMetodoPago(),d.getTotal(),d.getUsuario().getNombreUsuario());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Orden ya existe");
        }
    }
}
