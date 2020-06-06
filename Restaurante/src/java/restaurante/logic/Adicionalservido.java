/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Álvaro
 */
@Entity
@Table(name = "adicionalservido")
@NamedQueries({
    @NamedQuery(name = "Adicionalservido.findAll", query = "SELECT a FROM Adicionalservido a")
    , @NamedQuery(name = "Adicionalservido.findByAdicional", query = "SELECT a FROM Adicionalservido a WHERE a.adicional = :adicional")})
public class Adicionalservido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Adicional")
    private Integer adicional;
    @JoinColumn(name = "Adicional", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Adicional adicional1;
    @JoinColumn(name = "opcionservida", referencedColumnName = "opcion")
    @ManyToOne(optional = false)
    private Opcionservida opcionservida;

    public Adicionalservido() {
    }

    public Adicionalservido(Integer adicional) {
        this.adicional = adicional;
    }

    public Integer getAdicional() {
        return adicional;
    }

    public void setAdicional(Integer adicional) {
        this.adicional = adicional;
    }

    public Adicional getAdicional1() {
        return adicional1;
    }

    public void setAdicional1(Adicional adicional1) {
        this.adicional1 = adicional1;
    }

    public Opcionservida getOpcionservida() {
        return opcionservida;
    }

    public void setOpcionservida(Opcionservida opcionservida) {
        this.opcionservida = opcionservida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adicional != null ? adicional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adicionalservido)) {
            return false;
        }
        Adicionalservido other = (Adicionalservido) object;
        if ((this.adicional == null && other.adicional != null) || (this.adicional != null && !this.adicional.equals(other.adicional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.logic.Adicionalservido[ adicional=" + adicional + " ]";
    }
    
}
