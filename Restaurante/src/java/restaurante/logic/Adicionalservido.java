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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Álvaro
 */
@Entity
@Table(name = "adicionalservido")
@NamedQueries({
    @NamedQuery(name = "Adicionalservido.findAll", query = "SELECT a FROM Adicionalservido a")})
public class Adicionalservido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "Adicional", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Adicional adicional;
    @JoinColumn(name = "opcionservida", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Opcionservida opcionservida;

    public Adicionalservido() {
    }

    public Adicionalservido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adicional getAdicional() {
        return adicional;
    }

    public void setAdicional(Adicional adicional) {
        this.adicional = adicional;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adicionalservido)) {
            return false;
        }
        Adicionalservido other = (Adicionalservido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.logic.Adicionalservido[ id=" + id + " ]";
    }
    
}
