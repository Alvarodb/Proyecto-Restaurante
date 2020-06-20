/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.presentation;

import java.util.ArrayList;
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
import restaurante.logic.Platillo;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import restaurante.logic.Categoria;
import restaurante.logic.Opcion;

@Path("/platillos")
public class Platillos {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Platillo> list() throws Exception {
        return Model.instance().buscarPlatillos();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Platillo get(@PathParam("id") String id) {
        try {
            return Model.instance().platilloEdit(id);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public List<Platillo> add(Platillo p) {
        try {

            Categoria c = Model.instance().buscarCategoriaporNombre(p.getCategoria().getNombre());
            List<Opcion> opciones = new ArrayList<>();
            for (Opcion o : p.getOpcionList()) {

                Opcion extraida = Model.instance().buscarOpcionporNombre(o.getNombre());
                opciones.add(extraida);
            }

            p.setCategoria(c);
            p.setOpcionList(opciones);

            Model.instance().agregarPlatillo(p);
            Platillo aux = Model.instance().buscarPlatilloporNombre(p.getNombre());
            p.setId(aux.getId());

            for (Opcion o : p.getOpcionList()) {
                o.setPlatillo(p);
                Model.instance().ActualizarOpcion(o);
            }

            Model.instance().actualizarPlatillo(p);

            return Model.instance().buscarPlatillos();

        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public List<Platillo> del(List<Platillo> platillos) {
        try {
            for (Platillo p : platillos) {
                for (Opcion o : Model.instance().buscarOpcionesPlatilloId(String.valueOf(p.getId()))) {
                    Model.instance().opcionDelete(o);
                }
                Model.instance().platilloDelete(p);
            }
            return Model.instance().buscarPlatillos();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
