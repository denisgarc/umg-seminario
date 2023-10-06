/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import com.google.gson.annotations.Expose;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DOxlaj
 */
@Entity
@Table(name = "CONSULTA_TRATAMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultaTratamiento.findAll", query = "SELECT c FROM ConsultaTratamiento c"),
    @NamedQuery(name = "ConsultaTratamiento.findByIdTratamiento", query = "SELECT c FROM ConsultaTratamiento c WHERE c.idTratamiento = :idTratamiento"),
    @NamedQuery(name = "ConsultaTratamiento.findByDescripcion", query = "SELECT c FROM ConsultaTratamiento c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "ConsultaTratamiento.findByActivo", query = "SELECT c FROM ConsultaTratamiento c WHERE c.activo = :activo"),
    @NamedQuery(name = "ConsultaTratamiento.findByIdConsulta", query = "SELECT c FROM ConsultaTratamiento c WHERE c.idConsulta = :idConsulta")})
public class ConsultaTratamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRATAMIENTO")
    @Expose
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idTratamiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    @Expose
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    @Expose
    private String activo;
    @JoinColumn(name = "ID_CONSULTA", referencedColumnName = "ID_CONSULTA")
    @ManyToOne(optional = false)
    private Consulta idConsulta;
    @JoinColumn(name = "ID_TIPO_TRATAMIENTO", referencedColumnName = "ID_TIPO_TRATAMIENTO")
    @ManyToOne(optional = false)
    @Expose
    private TipoTratamiento idTipoTratamiento;

    public ConsultaTratamiento() {
    }

    public ConsultaTratamiento(Long idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public ConsultaTratamiento(Long idTratamiento, String descripcion, String activo) {
        this.idTratamiento = idTratamiento;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Long getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Long idTratamiento) {
        this.idTratamiento = idTratamiento;
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

    public Consulta getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Consulta idConsulta) {
        this.idConsulta = idConsulta;
    }

    public TipoTratamiento getIdTipoTratamiento() {
        return idTipoTratamiento;
    }

    public void setIdTipoTratamiento(TipoTratamiento idTipoTratamiento) {
        this.idTipoTratamiento = idTipoTratamiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTratamiento != null ? idTratamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaTratamiento)) {
            return false;
        }
        ConsultaTratamiento other = (ConsultaTratamiento) object;
        if ((this.idTratamiento == null && other.idTratamiento != null) || (this.idTratamiento != null && !this.idTratamiento.equals(other.idTratamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.ConsultaTratamiento[ idTratamiento=" + idTratamiento + " ]";
    }
    
}
