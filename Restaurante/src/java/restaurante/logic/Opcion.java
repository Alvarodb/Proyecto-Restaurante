/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.logic;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Álvaro
 */
@Entity
@Table(name = "opcion")
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o")})
public class Opcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "requerida")
    private short requerida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcion")
    private List<Opcionservida> opcionservidaList;
    @JoinColumn(name = "platillo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Platillo platillo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcion")
    private List<Adicional> adicionalList;

    public Opcion() {
    }

    public Opcion(Integer id) {
        this.id = id;
    }

    public Opcion(Integer id, String nombre, String tipo, short requerida) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.requerida = requerida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public short getRequerida() {
        return requerida;
    }

    public void setRequerida(short requerida) {
        this.requerida = requerida;
    }

    public List<Opcionservida> getOpcionservidaList() {
        return opcionservidaList;
    }

    public void setOpcionservidaList(List<Opcionservida> opcionservidaList) {
        this.opcionservidaList = opcionservidaList;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }

    public List<Adicional> getAdicionalList() {
        return adicionalList;
    }

    public void setAdicionalList(List<Adicional> adicionalList) {
        this.adicionalList = adicionalList;
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
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.logic.Opcion[ id=" + id + " ]";
    }
    
}
