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
@Table(name = "TIPO_CONSULTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoConsulta.findAll", query = "SELECT t FROM TipoConsulta t"),
    @NamedQuery(name = "TipoConsulta.findByIdTipoConsulta", query = "SELECT t FROM TipoConsulta t WHERE t.idTipoConsulta = :idTipoConsulta"),
    @NamedQuery(name = "TipoConsulta.findByDescripcion", query = "SELECT t FROM TipoConsulta t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoConsulta.findByActivo", query = "SELECT t FROM TipoConsulta t WHERE t.activo = :activo")})
public class TipoConsulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_CONSULTA")
    @Expose
    private Short idTipoConsulta;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoConsulta")
    private List<Consulta> consultaList;

    public TipoConsulta() {
    }

    public TipoConsulta(Short idTipoConsulta) {
        this.idTipoConsulta = idTipoConsulta;
    }

    public TipoConsulta(Short idTipoConsulta, String descripcion, String activo) {
        this.idTipoConsulta = idTipoConsulta;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Short getIdTipoConsulta() {
        return idTipoConsulta;
    }

    public void setIdTipoConsulta(Short idTipoConsulta) {
        this.idTipoConsulta = idTipoConsulta;
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
    public List<Consulta> getConsultaList() {
        return consultaList;
    }

    public void setConsultaList(List<Consulta> consultaList) {
        this.consultaList = consultaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoConsulta != null ? idTipoConsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoConsulta)) {
            return false;
        }
        TipoConsulta other = (TipoConsulta) object;
        if ((this.idTipoConsulta == null && other.idTipoConsulta != null) || (this.idTipoConsulta != null && !this.idTipoConsulta.equals(other.idTipoConsulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.TipoConsulta[ idTipoConsulta=" + idTipoConsulta + " ]";
    }
    
}
