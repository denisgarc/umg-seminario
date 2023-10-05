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
@Table(name = "TIPO_TRATAMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTratamiento.findAll", query = "SELECT t FROM TipoTratamiento t"),
    @NamedQuery(name = "TipoTratamiento.findByIdTipoTratamiento", query = "SELECT t FROM TipoTratamiento t WHERE t.idTipoTratamiento = :idTipoTratamiento"),
    @NamedQuery(name = "TipoTratamiento.findByDescripcion", query = "SELECT t FROM TipoTratamiento t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoTratamiento.findByActivo", query = "SELECT t FROM TipoTratamiento t WHERE t.activo = :activo")})
public class TipoTratamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_TRATAMIENTO")
    @Expose
    private Short idTipoTratamiento;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoTratamiento")
    private List<ConsultaTratamiento> consultaTratamientoList;

    public TipoTratamiento() {
    }

    public TipoTratamiento(Short idTipoTratamiento) {
        this.idTipoTratamiento = idTipoTratamiento;
    }

    public TipoTratamiento(Short idTipoTratamiento, String descripcion, String activo) {
        this.idTipoTratamiento = idTipoTratamiento;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Short getIdTipoTratamiento() {
        return idTipoTratamiento;
    }

    public void setIdTipoTratamiento(Short idTipoTratamiento) {
        this.idTipoTratamiento = idTipoTratamiento;
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
    public List<ConsultaTratamiento> getConsultaTratamientoList() {
        return consultaTratamientoList;
    }

    public void setConsultaTratamientoList(List<ConsultaTratamiento> consultaTratamientoList) {
        this.consultaTratamientoList = consultaTratamientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoTratamiento != null ? idTipoTratamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTratamiento)) {
            return false;
        }
        TipoTratamiento other = (TipoTratamiento) object;
        if ((this.idTipoTratamiento == null && other.idTipoTratamiento != null) || (this.idTipoTratamiento != null && !this.idTipoTratamiento.equals(other.idTipoTratamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.TipoTratamiento[ idTipoTratamiento=" + idTipoTratamiento + " ]";
    }
    
}
