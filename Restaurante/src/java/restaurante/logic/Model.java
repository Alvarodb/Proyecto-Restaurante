/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.logic;

import java.util.List;
import restaurante.data.*;
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
    daoDetalles detalles;
    daoOpcionServida opcionesServidas;
    daoAdicionalServido adicionalesServidos;
    daoDirecciones direcciones;
    
    public Model(){
        categorias = new daoCategorias();
        platillos = new daoPlatillos();
        opciones = new daoOpcion();
        usuarios = new daoUsuario();
        ordenes = new daoOrden();
        detalles = new daoDetalles();
        opcionesServidas = new daoOpcionServida();
        adicionalesServidos =  new daoAdicionalServido();
        direcciones = new daoDirecciones();
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
    //obtener el id de un platillo
    public int idPlatillo(String platillo) throws Exception{
        return platillos.platilloIdSearch(platillo);
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
    //registrar  usuario
    public void registrarUsuario(Usuario u) throws Exception{
        usuarios.usuarioAdd(u);
    }
    
    
    //ordenes
    
    //agregar ordenes
    
    public int registrarOrden(Orden o) throws Exception{
        return ordenes.OrdenAdd(o);
    }
    //ordenes por usuario
    public List<Orden> buscarOrdenes(String nombre) throws Exception{
        List<Orden> result = ordenes.ordenesSearch(nombre);
        return result;
    }
    

    
    //detalles
    
    //Agregar detalles a orden
    
    public int agregarDetalles(Detalle d) throws Exception{
        return detalles.DetallesAdd(d);
    }
    
    //opciones servidas
    
    //Agregar opciones servidas (seleccionadas para cada orden)
    
    public int agregarOpcionServida(int opcion, int detalle) throws Exception{
        return opcionesServidas.opcionServidaAdd(opcion,detalle);
    }
    //adicional servido
    
    //Agregar adicionales para cada opcion seleccionada en una orden
    
    public void agregarAdicionalServido(int adicional,int opcion) throws Exception{
        adicionalesServidos.adicionalServidoAdd(adicional,opcion);
    }
    
    
    //direcciones
    
    //Agregar direccion a un usuario
    
    public void agregarDireccion(Direccion dir, String nombre) throws Exception{
        direcciones.direccionAdd(dir,nombre);
    }
    //buscar direcciones de un usuario
    public List<Direccion> buscarDirecciones(String nombre) throws Exception{
        return direcciones.direccionesSearch(nombre);
    }
    //actualizar direccion de un usuario
    public void actualizarDireccion(Direccion dir) throws Exception{
        direcciones.direccionUpdate(dir);
    }
    static Model the_instance;
    public static Model instance(){
        if(the_instance == null){
            the_instance = new Model();
        }
        return the_instance;
    }   
}
