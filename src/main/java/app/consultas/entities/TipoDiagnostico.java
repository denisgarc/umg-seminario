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
@Table(name = "TIPO_DIAGNOSTICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDiagnostico.findAll", query = "SELECT t FROM TipoDiagnostico t"),
    @NamedQuery(name = "TipoDiagnostico.findByIdTipoDiagnostico", query = "SELECT t FROM TipoDiagnostico t WHERE t.idTipoDiagnostico = :idTipoDiagnostico"),
    @NamedQuery(name = "TipoDiagnostico.findByDescripcion", query = "SELECT t FROM TipoDiagnostico t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoDiagnostico.findByActivo", query = "SELECT t FROM TipoDiagnostico t WHERE t.activo = :activo")})
public class TipoDiagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_DIAGNOSTICO")
    @Expose
    private Short idTipoDiagnostico;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDiagnostico")
    private List<ConsultaDiagnostico> consultaDiagnosticoList;

    public TipoDiagnostico() {
    }

    public TipoDiagnostico(Short idTipoDiagnostico) {
        this.idTipoDiagnostico = idTipoDiagnostico;
    }

    public TipoDiagnostico(Short idTipoDiagnostico, String descripcion, String activo) {
        this.idTipoDiagnostico = idTipoDiagnostico;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Short getIdTipoDiagnostico() {
        return idTipoDiagnostico;
    }

    public void setIdTipoDiagnostico(Short idTipoDiagnostico) {
        this.idTipoDiagnostico = idTipoDiagnostico;
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
    public List<ConsultaDiagnostico> getConsultaDiagnosticoList() {
        return consultaDiagnosticoList;
    }

    public void setConsultaDiagnosticoList(List<ConsultaDiagnostico> consultaDiagnosticoList) {
        this.consultaDiagnosticoList = consultaDiagnosticoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDiagnostico != null ? idTipoDiagnostico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDiagnostico)) {
            return false;
        }
        TipoDiagnostico other = (TipoDiagnostico) object;
        if ((this.idTipoDiagnostico == null && other.idTipoDiagnostico != null) || (this.idTipoDiagnostico != null && !this.idTipoDiagnostico.equals(other.idTipoDiagnostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.TipoDiagnostico[ idTipoDiagnostico=" + idTipoDiagnostico + " ]";
    }
    
}
