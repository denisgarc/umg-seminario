/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DOxlaj
 */
@Entity
@Table(name = "PACIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByIdPaciente", query = "SELECT p FROM Paciente p WHERE p.idPaciente = :idPaciente"),
    @NamedQuery(name = "Paciente.findByFecAlta", query = "SELECT p FROM Paciente p WHERE p.fecAlta = :fecAlta"),
    @NamedQuery(name = "Paciente.findByNumeroSeguro", query = "SELECT p FROM Paciente p WHERE p.numeroSeguro = :numeroSeguro"),
    @NamedQuery(name = "Paciente.findByTipoSangre", query = "SELECT p FROM Paciente p WHERE p.tipoSangre = :tipoSangre"),
    @NamedQuery(name = "Paciente.findByFuma", query = "SELECT p FROM Paciente p WHERE p.fuma = :fuma"),
    @NamedQuery(name = "Paciente.findByActivo", query = "SELECT p FROM Paciente p WHERE p.activo = :activo")})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PACIENTE")
    @Expose
    private Long idPaciente;
    @Column(name = "FEC_ALTA")
    @Temporal(TemporalType.TIMESTAMP)
    @Expose
    private Date fecAlta;
    @Size(max = 25)
    @Column(name = "NUMERO_SEGURO")
    @Expose
    private String numeroSeguro;
    @Size(max = 25)
    @Column(name = "TIPO_SANGRE")
    @Expose
    private String tipoSangre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "FUMA")
    @Expose
    private String fuma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    @Expose
    private String activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<Cita> citaList;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    @Expose
    private Persona idPersona;
    @JoinColumn(name = "ID_PERSONA_CONTACTO", referencedColumnName = "ID_PERSONA")
    @ManyToOne
    @Expose
    private Persona idPersonaContacto;

    public Paciente() {
    }

    public Paciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Paciente(Long idPaciente, String fuma, String activo) {
        this.idPaciente = idPaciente;
        this.fuma = fuma;
        this.activo = activo;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Date getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    public String getNumeroSeguro() {
        return numeroSeguro;
    }

    public void setNumeroSeguro(String numeroSeguro) {
        this.numeroSeguro = numeroSeguro;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getFuma() {
        return fuma;
    }

    public void setFuma(String fuma) {
        this.fuma = fuma;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Persona getIdPersonaContacto() {
        return idPersonaContacto;
    }

    public void setIdPersonaContacto(Persona idPersonaContacto) {
        this.idPersonaContacto = idPersonaContacto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.Paciente[ idPaciente=" + idPaciente + " ]";
    }
    
}
