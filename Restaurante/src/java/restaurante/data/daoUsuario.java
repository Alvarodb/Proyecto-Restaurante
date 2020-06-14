/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import restaurante.logic.Usuario;

/**
 *
 * @author Álvaro
 */
public class daoUsuario {

    RelDatabase db;

    public daoUsuario() {
        db = new RelDatabase();
    }

    public Usuario usuarioSearch(String email, String clave) throws Exception {
        Usuario resultado = new Usuario();
        try {
            String sql = "select * "
                    + "from usuario where email='%s' and clave='%s'";
            sql = String.format(sql, email,clave);
            ResultSet rs = db.executeQuery(sql);
            if (rs.next()) {
                return usuario(rs);
            } else {
                throw new Exception("Usuario no existe");
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    public void usuarioAdd(Usuario u) throws Exception {
        String sql="insert into usuario(nombre_usuario,clave,nombre,apellidos,email,administrador,telefono) "+
                "values('%s','%s','%s','%s','%s','%s','%s')";
        sql=String.format(sql,u.getNombreUsuario(),u.getClave(),u.getNombre(),u.getApellidos(),u.getEmail(),0,u.getTelefono());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Usuario ya existe");
        }
    }

    private Usuario usuario(ResultSet rs) throws Exception {
        try {
            Usuario u = new Usuario();
            u.setNombre(rs.getString("nombre"));
            u.setAdministrador(Short.valueOf(rs.getString("administrador")));
            u.setApellidos(rs.getString("apellidos"));
            u.setClave(rs.getString("clave"));
            u.setDireccionList(new restaurante.data.daoDirecciones().direccionesSearch(rs.getString("nombre_usuario")));
            u.setEmail(rs.getString("email"));
            u.setNombreUsuario(rs.getString("nombre_usuario"));
            u.setTelefono(rs.getString("telefono"));
            //ordenes recientes de un usuario
            return u;
        } catch (SQLException ex) {
            return null;
        }
    }
}
