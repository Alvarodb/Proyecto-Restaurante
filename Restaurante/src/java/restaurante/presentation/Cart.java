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
        if (result == null) {
            result = new ArrayList<Detalle>();
            session.setAttribute("cart", result);
            session.setAttribute("total", 0.0f);
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
            float nuevoTotal = (float) session.getAttribute("total") + d.getPlatillo().getPrecio();
            session.setAttribute("total", nuevoTotal);

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
            float nuevoTotal = (float) session.getAttribute("total");
            float precioArt;

            for (Iterator<Detalle> iterator = result.iterator(); iterator.hasNext();) {
                Detalle d = iterator.next();
                if (d.getPlatillo().getNombre().equals(nombre) && d.getCantidad() > 1) {
                    precioArt = d.getPlatillo().getPrecio() / d.getCantidad();
                    d.setCantidad(d.getCantidad() - 1);
                    d.getPlatillo().setPrecio(precioArt * d.getCantidad());
                    session.setAttribute("total", nuevoTotal - precioArt);
                } else if (d.getPlatillo().getNombre().equals(nombre) && d.getCantidad() <= 1) {
                    session.setAttribute("total", nuevoTotal - (d.getPlatillo().getPrecio()));
                    iterator.remove();
                }
            }

            return result;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    public float total() {
        HttpSession session = request.getSession(true);
        float nuevoTotal = (float) session.getAttribute("total");
        return nuevoTotal;
    }
}
