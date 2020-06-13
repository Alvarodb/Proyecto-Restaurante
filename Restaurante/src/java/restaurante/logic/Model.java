/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.logic;

import java.util.List;
import restaurante.data.daoCategorias;
import restaurante.data.daoOpcion;
import restaurante.data.daoPlatillos;
import restaurante.data.daoUsuario;
import restaurante.data.daoOrden;
/**
 *
 * @author Álvaro
 */
public class Model {
    
    daoCategorias categorias;
    daoPlatillos platillos;
    daoOpcion opciones;
    daoUsuario usuarios;
    daoOrden ordenes;
    
    public Model(){
        categorias = new daoCategorias();
        platillos = new daoPlatillos();
        opciones = new daoOpcion();
        usuarios = new daoUsuario();
        ordenes = new daoOrden();
    }
    //categorias
     public List<Categoria> buscarCategorias(){ 
       List<Categoria> result = categorias.categoriasSearch();
       return result;
    }
    //platillos
    public List<Platillo> buscarPlatillos() throws Exception{ 
       List<Platillo> result = platillos.platillosSearch();
       return result;
    }
    //platillos por categoria
    public List<Platillo> buscarPlatillosCategoria(String nombre) throws Exception{
        List<Platillo> result = platillos.platillosSearchCategorias(nombre);
        return result;
    }
    //opciones
    
    //opciones(adicionales en la lista de atributos) por platillo
    public List<Opcion> buscarOpcionesPlatillo(String nombre) throws Exception{
        List<Opcion> result = opciones.opcionesSearch(nombre);
        return result;
    }
    
    //usuarios
    
    
    //consultar usuario
    public Usuario buscarUsuario(String email, String clave) throws Exception{
        Usuario result = usuarios.usuarioSearch(email,clave);
        return result;
    }
    
    //ordenes
    
    //agregar ordenes
    
    public void registrarOrden(Orden o) throws Exception{
        ordenes.OrdenAdd(o);
    }
   
    
    
    static Model the_instance;
    public static Model instance(){
        if(the_instance == null){
            the_instance = new Model();
        }
        return the_instance;
    }   
}
