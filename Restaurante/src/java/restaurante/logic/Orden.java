/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.logic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Álvaro
 */
@Entity
@Table(name = "orden")
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o")
    , @NamedQuery(name = "Orden.findById", query = "SELECT o FROM Orden o WHERE o.id = :id")
    , @NamedQuery(name = "Orden.findByTipo", query = "SELECT o FROM Orden o WHERE o.tipo = :tipo")
    , @NamedQuery(name = "Orden.findByFechahora", query = "SELECT o FROM Orden o WHERE o.fechahora = :fechahora")
    , @NamedQuery(name = "Orden.findByFechahoraentrega", query = "SELECT o FROM Orden o WHERE o.fechahoraentrega = :fechahoraentrega")
    , @NamedQuery(name = "Orden.findByEstado", query = "SELECT o FROM Orden o WHERE o.estado = :estado")
    , @NamedQuery(name = "Orden.findByMetodoPago", query = "SELECT o FROM Orden o WHERE o.metodoPago = :metodoPago")
    , @NamedQuery(name = "Orden.findByTotal", query = "SELECT o FROM Orden o WHERE o.total = :total")
    , @NamedQuery(name = "Orden.findByDireccion", query = "SELECT o FROM Orden o WHERE o.direccion = :direccion")})
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechahoraentrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraentrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "metodo_pago")
    private String metodoPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private float total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "direccion")
    private String direccion;
    @JoinColumn(name = "usuario", referencedColumnName = "nombre_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden")
    private List<Detalle> detalleList;

    public Orden() {
    }

    public Orden(Integer id) {
        this.id = id;
    }

    public Orden(Integer id, String tipo, Date fechahora, Date fechahoraentrega, String estado, String metodoPago, float total, String direccion) {
        this.id = id;
        this.tipo = tipo;
        this.fechahora = fechahora;
        this.fechahoraentrega = fechahoraentrega;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.total = total;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public Date getFechahoraentrega() {
        return fechahoraentrega;
    }

    public void setFechahoraentrega(Date fechahoraentrega) {
        this.fechahoraentrega = fechahoraentrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.logic.Orden[ id=" + id + " ]";
    }
    
}
