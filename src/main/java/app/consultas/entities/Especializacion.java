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
@Table(name = "ESPECIALIZACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especializacion.findAll", query = "SELECT e FROM Especializacion e"),
    @NamedQuery(name = "Especializacion.findByIdEspecializacion", query = "SELECT e FROM Especializacion e WHERE e.idEspecializacion = :idEspecializacion"),
    @NamedQuery(name = "Especializacion.findByDescripcion", query = "SELECT e FROM Especializacion e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Especializacion.findByActivo", query = "SELECT e FROM Especializacion e WHERE e.activo = :activo")})
public class Especializacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESPECIALIZACION")
    @Expose
    private Short idEspecializacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    @Expose
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    @Expose
    private String activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEspecializacion")
    private List<Empleado> empleadoList;

    public Especializacion() {
    }

    public Especializacion(Short idEspecializacion) {
        this.idEspecializacion = idEspecializacion;
    }

    public Especializacion(Short idEspecializacion, String descripcion, String activo) {
        this.idEspecializacion = idEspecializacion;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Short getIdEspecializacion() {
        return idEspecializacion;
    }

    public void setIdEspecializacion(Short idEspecializacion) {
        this.idEspecializacion = idEspecializacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspecializacion != null ? idEspecializacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especializacion)) {
            return false;
        }
        Especializacion other = (Especializacion) object;
        if ((this.idEspecializacion == null && other.idEspecializacion != null) || (this.idEspecializacion != null && !this.idEspecializacion.equals(other.idEspecializacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.Especializacion[ idEspecializacion=" + idEspecializacion + " ]";
    }
    
}
