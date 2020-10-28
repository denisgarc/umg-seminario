/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DOxlaj
 */
@Entity
@Table(name = "UNIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidad.findAll", query = "SELECT u FROM Unidad u"),
    //@NamedQuery(name = "Unidad.findByIdUnidad", query = "SELECT u FROM Unidad u WHERE u.idUnidad = :idUnidad"),
    @NamedQuery(name = "Unidad.findByDescripcion", query = "SELECT u FROM Unidad u WHERE u.descripcion = :descripcion"),
    @NamedQuery(name = "Unidad.findByAbreviatura", query = "SELECT u FROM Unidad u WHERE u.abreviatura = :abreviatura"),
    @NamedQuery(name = "Unidad.findByActivo", query = "SELECT u FROM Unidad u WHERE u.activo = :activo")})
public class Unidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UNIDAD")
    @Expose
    private Short idUnidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    @Expose
    private String descripcion;
    @Size(max = 10)
    @Column(name = "ABREVIATURA")
    @Expose
    private String abreviatura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    @Expose
    private String activo;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidad")
    private List<RecetaDetalle> recetaDetalleList;*/

    public Unidad() {
    }

    public Unidad(Short idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Unidad(Short idUnidad, String descripcion, String activo) {
        this.idUnidad = idUnidad;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Short getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Short idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    /*@XmlTransient
    public List<RecetaDetalle> getRecetaDetalleList() {
        return recetaDetalleList;
    }

    public void setRecetaDetalleList(List<RecetaDetalle> recetaDetalleList) {
        this.recetaDetalleList = recetaDetalleList;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidad != null ? idUnidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidad)) {
            return false;
        }
        Unidad other = (Unidad) object;
        if ((this.idUnidad == null && other.idUnidad != null) || (this.idUnidad != null && !this.idUnidad.equals(other.idUnidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.Unidad[ idUnidad=" + idUnidad + " ]";
    }
    
}
