/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.data;



/**
 *
 * @author Álvaro
 */
public class daoOpcionServida {
    
    RelDatabase db;

    public daoOpcionServida() {
        db = new RelDatabase();
    }

    public void opcionServidaAdd(int opc,int det) throws Exception {
        String sql="insert into opcionServida (opcion,detalle) "+
                "values('%s','%s')";
        sql=String.format(sql,opc,det);
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Opcion Servida ya existe");
        }
    }
}
