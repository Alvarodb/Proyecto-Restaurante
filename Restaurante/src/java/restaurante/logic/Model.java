/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.logic;

import java.util.ArrayList;
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

    public Model() {
        categorias = new daoCategorias();
        platillos = new daoPlatillos();
        opciones = new daoOpcion();
        usuarios = new daoUsuario();
        ordenes = new daoOrden();
        detalles = new daoDetalles();
        opcionesServidas = new daoOpcionServida();
        adicionalesServidos = new daoAdicionalServido();
        direcciones = new daoDirecciones();
    }

    //categorias
    public List<Categoria> buscarCategorias() {
        List<Categoria> result = categorias.categoriasSearch();
        return result;
    }

    //platillos
    public List<Platillo> buscarPlatillos() throws Exception {
        List<Platillo> result = platillos.platillosSearch();
        return result;
    }

    //platillos por categoria
    public List<Platillo> buscarPlatillosCategoria(String nombre) throws Exception {
        List<Platillo> result = platillos.platillosSearchCategorias(nombre);
        return result;
    }

    //obtener el id de un platillo
    public int idPlatillo(String platillo) throws Exception {
        return platillos.platilloIdSearch(platillo);
    }

    //opciones
    //opciones(adicionales en la lista de atributos) por platillo
    public List<Opcion> buscarOpcionesPlatillo(String nombre) throws Exception {
        List<Opcion> result = opciones.opcionesSearch(nombre);
        return result;
    }

    //usuarios
    //consultar usuario
    public Usuario buscarUsuario(String email, String clave) throws Exception {
        Usuario result = usuarios.usuarioSearch(email, clave);
        return result;
    }

    //registrar  usuario
    public void registrarUsuario(Usuario u) throws Exception {
        usuarios.usuarioAdd(u);
    }

    //ordenes
    //agregar ordenes
    public int registrarOrden(Orden o) throws Exception {
        return ordenes.OrdenAdd(o);
    }

    //ordenes por usuario
    public List<Orden> buscarOrdenes(String nombre) throws Exception {
        List<Orden> result = ordenes.ordenesSearch(nombre);
        return result;
    }

    //detalles
    //Agregar detalles a orden
    public int agregarDetalles(Detalle d) throws Exception {
        return detalles.DetallesAdd(d);
    }

    //opciones servidas
    //Agregar opciones servidas (seleccionadas para cada orden)
    public int agregarOpcionServida(int opcion, int detalle) throws Exception {
        return opcionesServidas.opcionServidaAdd(opcion, detalle);
    }
    //adicional servido

    //Agregar adicionales para cada opcion seleccionada en una orden
    public void agregarAdicionalServido(int adicional, int opcion) throws Exception {
        adicionalesServidos.adicionalServidoAdd(adicional, opcion);
    }

    //direcciones
    //Agregar direccion a un usuario
    public void agregarDireccion(Direccion dir, String nombre) throws Exception {
        direcciones.direccionAdd(dir, nombre);
    }

    //buscar direcciones de un usuario
    public List<Direccion> buscarDirecciones(String nombre) throws Exception {
        return direcciones.direccionesSearch(nombre);
    }

    //actualizar direccion de un usuario
    public void actualizarDireccion(Direccion dir) throws Exception {
        direcciones.direccionUpdate(dir);
    }

    public Categoria categoriaEdit(String id) throws Exception {
        return categorias.categoriasFind(Integer.valueOf(id));
    }

    public Categoria buscarCategoriaporNombre(String nombre) throws Exception {
        Categoria result = categorias.categoriasFindbyName(nombre);
        return result;
    }

    public void agregarCategoria(Categoria c) throws Exception {
        if (categorias.categoriasFindbyName(c.getNombre()) == null) {
            categorias.CategoriaAdd(c);
        }
    }

    public void categoriaDelete(Categoria p) throws Exception {
        categorias.CategoriaDelete(p);
    }

    public void actualizarCategoria(Categoria c) throws Exception {
        categorias.categoriaUpdate(c);
    }

    public List<Categoria> categoriaSearch(String nombre) {
        List<Categoria> result = new ArrayList<>();
        for (Categoria p : Model.instance().buscarCategorias()) {
            if (p.getNombre().contains(nombre)) {
                result.add(p);
            }
        }
        return result;
    }

    //platillos
    public Platillo platilloEdit(String id) throws Exception {
        return platillos.platilloGet(id);
    }

    public void agregarPlatillo(Platillo p) throws Exception {
        if (platillos.platilloGetbyName(p.getNombre()) == null) {
            platillos.PlatilloAdd(p);
        }
    }

    public void actualizarPlatillo(Platillo p) throws Exception {
        platillos.platilloUpdate(p);
    }

    public Platillo buscarPlatilloporNombre(String nombre) throws Exception {
        Platillo result = platillos.platillosSearchbyName(nombre);
        return result;
    }
    public Platillo buscarPlatiiloId(String id) throws Exception{
        return platillos.platilloGet(id);
    }

    public void platilloDelete(Platillo p) throws Exception {
        platillos.PlatilloDelete(p);
    }

    //opciones
    public List<Opcion> buscarOpciones() {
        List<Opcion> result = opciones.opcionesSearchAll();
        return result;
    }

    public void opcionDelete(Opcion p) throws Exception {
        opciones.OpcionDelete(p);
    }

    public void ActualizarOpcion(Opcion o) throws Exception {
        opciones.OpcionUpdate(o);
    }

    public List<Opcion> buscarOpcionesPlatilloId(String id) throws Exception {
        List<Opcion> result = opciones.opcionesSearchId(id);
        return result;
    }

    public Opcion buscarOpcionporNombre(String nombre) throws Exception {
        Opcion result = opciones.opcionSearchbyName(nombre);
        return result;
    }

    //usuarios
    public List<Usuario> buscarStaff() throws Exception {
        return usuarios.staffSearch();
    }

    public List<Usuario> buscarClientes() throws Exception {
        return usuarios.clientSearch();
    }
   public List<Usuario> buscarUsuarios() throws Exception{
        List<Usuario> result = usuarios.usuarioSearchAll();
        return result;
    }
    public Usuario buscarUsuarioporUsername(String username) throws Exception {
        Usuario result = usuarios.usuarioSearchbyUsername(username);
        return result;
    }

    public void actualizarUsuario(Usuario u) throws Exception {
        usuarios.usuarioUpdate(u);
    }

    public void usuarioDelete(Usuario p) throws Exception {
        usuarios.UsuarioDelete(p);
    }

    public Usuario usuarioEdit(String nombreUsuario) throws Exception {
        return usuarios.usuarioSearchbyUsername(nombreUsuario);
    }

    public List<Orden> buscarOrdenes() throws Exception {
        return ordenes.ordenes();
    }
     public Orden buscarOrdenId(String id) throws Exception {
        return ordenes.searchOrdenbyId(id);
    }
    public Orden ordenEdit(String id) throws Exception {
        return ordenes.searchOrdenbyId(id);
    }
    public void ActualizarOrden(Orden o) throws Exception{
        ordenes.ordenUpdate(o);
    }
    
    public List<Detalle> buscarDetalleporOrden(int id) throws Exception{
        return detalles.detallesSearch(id);
    }

    static Model the_instance;

    public static Model instance() {
        if (the_instance == null) {
            the_instance = new Model();
        }
        return the_instance;
    }
}
