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
public class daoAdicionalServido {
    RelDatabase db;

    public daoAdicionalServido() {
        db = new RelDatabase();
    }

    public void adicionalServidoAdd(int adicional,int opcion) throws Exception { 
        String sql="insert into adicionalServido (adicional,opcionservida) "+
                "values('%s','%s')";
        sql=String.format(sql,adicional,opcion);
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Adicional servida ya existe");
        }
    }
}
