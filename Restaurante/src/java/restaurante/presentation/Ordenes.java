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
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import restaurante.logic.Orden;


@Path("/ordenes")
public class Ordenes {
       @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Orden> list() throws Exception{ 
        return Model.instance().buscarOrdenes();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Orden get(@PathParam("id") String id) {
        try {
            Orden o = Model.instance().ordenEdit(id);
            return o;
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public List<Orden> actualizar(Orden o){
        try{
        
            Model.instance().ActualizarOrden(o);
            return Model.instance().buscarOrdenes();
        }catch (Exception ex){
            throw new NotFoundException(); 
        }
         
        
    }
}
