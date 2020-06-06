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
import restaurante.logic.Categoria;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;

@Path("/categorias")
public class Categorias {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> list() throws Exception{ 
        return Model.instance().buscarCategorias();
    } 
   
}