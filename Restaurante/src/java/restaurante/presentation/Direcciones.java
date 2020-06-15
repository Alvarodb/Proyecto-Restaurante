/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.presentation;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import restaurante.logic.Direccion;
import restaurante.logic.Usuario;
import restaurante.logic.Model;


@Path("/direcciones")
public class Direcciones {

    @Context
    HttpServletRequest request;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Direccion> listDirecciones() {
        HttpSession session = request.getSession(true);
        Usuario user  = (Usuario) session.getAttribute("usuario");
        List<Direccion> result = user.getDireccionList();
        return result;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})    
    public List<Direccion> add(Direccion dir) { 
        HttpSession session = request.getSession(true);
        Usuario user  = (Usuario) session.getAttribute("usuario");
        try {
            Model.instance().agregarDireccion(dir,user.getNombreUsuario());
            user.getDireccionList().add(dir);
            return Model.instance().buscarDirecciones(user.getNombreUsuario());
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})    
    public List<Direccion> update(Direccion dir) { 
        HttpSession session = request.getSession(true);
        Usuario user  = (Usuario) session.getAttribute("usuario");
        try {
            Model.instance().actualizarDireccion(dir);
            return Model.instance().buscarDirecciones(user.getNombreUsuario());
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }
    }
    
}