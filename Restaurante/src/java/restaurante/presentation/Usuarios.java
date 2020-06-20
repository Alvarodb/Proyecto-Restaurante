/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.presentation;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import restaurante.logic.Model;
import restaurante.logic.Usuario;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;


@Path("/usuarios")
public class Usuarios {   
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})  
    public List<Usuario> usuarioAdd(Usuario u) throws Exception{ 
        if(Model.instance().buscarUsuarioporUsername(u.getNombreUsuario())==null){
            Model.instance().registrarUsuario(u);
        }
        else{
         Model.instance().actualizarUsuario(u);
        }
        return Model.instance().buscarStaff();
    } 
    
    
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> list() throws Exception{ 
        return Model.instance().buscarUsuarios();
    } 
    
     @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> del( List<Usuario> usuarios) {
        try {
            for(Usuario c: usuarios){                
                 Model.instance().usuarioDelete(c);
            }
            return Model.instance().buscarStaff();
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }

    @GET
    @Path("{nombreUsuario}")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario get(@PathParam("nombreUsuario") String nombreUsuario) {
        try {
            return Model.instance().usuarioEdit(nombreUsuario);
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
}