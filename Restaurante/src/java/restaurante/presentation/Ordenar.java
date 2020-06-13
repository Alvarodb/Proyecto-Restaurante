/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.presentation;


import java.util.Date;
import java.sql.Timestamp;
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
import restaurante.logic.Orden;
import restaurante.logic.Usuario;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

@Path("/ordenar")
public class Ordenar {
    
    @Context
    HttpServletRequest request;
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void ordenar(Orden ord) throws Exception{ 
        HttpSession session = request.getSession(true);
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        ord.setFechahora(timestamp);
        ord.setTotal((float) session.getAttribute("total"));
        ord.setUsuario((Usuario) session.getAttribute("usuario"));
        Model.instance().registrarOrden(ord);
    } 
}