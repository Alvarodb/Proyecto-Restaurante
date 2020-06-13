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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Álvaro
 */
@Entity
@Table(name = "opcionservida")
@NamedQueries({
    @NamedQuery(name = "Opcionservida.findAll", query = "SELECT o FROM Opcionservida o")})
public class Opcionservida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "opcion")
    private Integer opcion;
    @JoinColumn(name = "detalle", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Detalle detalle;
    @JoinColumn(name = "opcion", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Opcion opcion1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcionservida")
    private List<Adicionalservido> adicionalservidoList;

    public Opcionservida() {
    }

    public Opcionservida(Integer opcion) {
        this.opcion = opcion;
    }

    public Integer getOpcion() {
        return opcion;
    }

    public void setOpcion(Integer opcion) {
        this.opcion = opcion;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public Opcion getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(Opcion opcion1) {
        this.opcion1 = opcion1;
    }

    public List<Adicionalservido> getAdicionalservidoList() {
        return adicionalservidoList;
    }

    public void setAdicionalservidoList(List<Adicionalservido> adicionalservidoList) {
        this.adicionalservidoList = adicionalservidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opcion != null ? opcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcionservida)) {
            return false;
        }
        Opcionservida other = (Opcionservida) object;
        if ((this.opcion == null && other.opcion != null) || (this.opcion != null && !this.opcion.equals(other.opcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.logic.Opcionservida[ opcion=" + opcion + " ]";
    }
    
}
