/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DOxlaj
 */
@Entity
@Table(name = "CONSULTA_DIAGNOSTICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultaDiagnostico.findAll", query = "SELECT c FROM ConsultaDiagnostico c"),
    @NamedQuery(name = "ConsultaDiagnostico.findByIdDiagnostico", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.idDiagnostico = :idDiagnostico"),
    @NamedQuery(name = "ConsultaDiagnostico.findByDescripcion", query = "SELECT c FROM ConsultaDiagnostico c WHERE c.descripcion = :descripcion")})
public class ConsultaDiagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DIAGNOSTICO")
    private Long idDiagnostico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_CONSULTA", referencedColumnName = "ID_CONSULTA")
    @ManyToOne(optional = false)
    private Consulta idConsulta;
    @JoinColumn(name = "ID_TIPO_DIAGNOSTICO", referencedColumnName = "ID_TIPO_DIAGNOSTICO")
    @ManyToOne(optional = false)
    private TipoDiagnostico idTipoDiagnostico;

    public ConsultaDiagnostico() {
    }

    public ConsultaDiagnostico(Long idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public ConsultaDiagnostico(Long idDiagnostico, String descripcion) {
        this.idDiagnostico = idDiagnostico;
        this.descripcion = descripcion;
    }

    public Long getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Long idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Consulta getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Consulta idConsulta) {
        this.idConsulta = idConsulta;
    }

    public TipoDiagnostico getIdTipoDiagnostico() {
        return idTipoDiagnostico;
    }

    public void setIdTipoDiagnostico(TipoDiagnostico idTipoDiagnostico) {
        this.idTipoDiagnostico = idTipoDiagnostico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiagnostico != null ? idDiagnostico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaDiagnostico)) {
            return false;
        }
        ConsultaDiagnostico other = (ConsultaDiagnostico) object;
        if ((this.idDiagnostico == null && other.idDiagnostico != null) || (this.idDiagnostico != null && !this.idDiagnostico.equals(other.idDiagnostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.ConsultaDiagnostico[ idDiagnostico=" + idDiagnostico + " ]";
    }
    
}
