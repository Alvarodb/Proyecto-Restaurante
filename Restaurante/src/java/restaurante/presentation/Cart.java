/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.presentation;

import java.util.ArrayList;
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
import restaurante.logic.Detalle;

@Path("/cart")
public class Cart {   
    @Context
    HttpServletRequest request;
        
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Detalle> listItems() { 
        HttpSession session = request.getSession(true);
        List<Detalle> result = (List<Detalle>) session.getAttribute("cart");
        if(result == null){         
            result = new ArrayList<Detalle>();
            session.setAttribute("cart", result);
        }
        return result;
    }  
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addItem(Detalle d) {  
        try {
           HttpSession session = request.getSession(true);
           List<Detalle> result = (List<Detalle>) session.getAttribute("cart");
           result.add(d);
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
    
    @DELETE
    @Path("{nombre}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Detalle> deleteItem(@PathParam("nombre") String nombre) {
        try {
            HttpSession session = request.getSession(true);
            List<Detalle> result = (List<Detalle>) session.getAttribute("cart");
            result.removeIf(i -> i.getPlatillo().getNombre().equals(nombre));
            return result;
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
    
}
