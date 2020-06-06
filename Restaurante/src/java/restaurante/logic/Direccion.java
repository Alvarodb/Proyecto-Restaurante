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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Álvaro
 */
@Entity
@Table(name = "direccion")
@NamedQueries({
    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d")
    , @NamedQuery(name = "Direccion.findByUsuario", query = "SELECT d FROM Direccion d WHERE d.usuario = :usuario")
    , @NamedQuery(name = "Direccion.findByAddress1", query = "SELECT d FROM Direccion d WHERE d.address1 = :address1")
    , @NamedQuery(name = "Direccion.findByAddress2", query = "SELECT d FROM Direccion d WHERE d.address2 = :address2")
    , @NamedQuery(name = "Direccion.findByCity", query = "SELECT d FROM Direccion d WHERE d.city = :city")
    , @NamedQuery(name = "Direccion.findByState", query = "SELECT d FROM Direccion d WHERE d.state = :state")
    , @NamedQuery(name = "Direccion.findByPostcode", query = "SELECT d FROM Direccion d WHERE d.postcode = :postcode")
    , @NamedQuery(name = "Direccion.findByCountry", query = "SELECT d FROM Direccion d WHERE d.country = :country")})
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "address1")
    private String address1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "address2")
    private String address2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "postcode")
    private String postcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "country")
    private String country;
    @JoinColumn(name = "Usuario", referencedColumnName = "nombre_usuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario1;

    public Direccion() {
    }

    public Direccion(String usuario) {
        this.usuario = usuario;
    }

    public Direccion(String usuario, String address1, String address2, String city, String state, String postcode, String country) {
        this.usuario = usuario;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restaurante.logic.Direccion[ usuario=" + usuario + " ]";
    }
    
}
