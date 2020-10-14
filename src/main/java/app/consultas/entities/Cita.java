/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DOxlaj
 */
@Entity
@Table(name = "CITA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
    @NamedQuery(name = "Cita.findByIdCita", query = "SELECT c FROM Cita c WHERE c.idCita = :idCita"),
    @NamedQuery(name = "Cita.findByFechaCita", query = "SELECT c FROM Cita c WHERE c.fechaCita = :fechaCita"),
    @NamedQuery(name = "Cita.findByHoraCita", query = "SELECT c FROM Cita c WHERE c.horaCita = :horaCita")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CITA")
    private Long idCita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CITA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_CITA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaCita;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne(optional = false)
    private Estado idEstado;
    @JoinColumn(name = "ID_HOSPITAL", referencedColumnName = "ID_HOSPITAL", 
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Hospital idHospital;
//    @JoinColumns({
//        @JoinColumn(name = "ID_HOSPITAL", referencedColumnName = "ID_HOSPITAL"),
//        @JoinColumn(name = "ID_CLINICA", referencedColumnName = "ID_CLINICA")})
//    @ManyToOne(optional = false)
//    private HospitalClinica hospitalClinica;
//    @JoinColumns({
//        @JoinColumn(name = "ID_HOSPITAL", referencedColumnName = "ID_HOSPITAL"),
//        @JoinColumn(name = "ID_SALA", referencedColumnName = "ID_SALA")})
//    @ManyToOne(optional = false)
//    private HospitalSala hospitalSala;
    @JoinColumn(name = "ID_PACIENTE", referencedColumnName = "ID_PACIENTE")
    @ManyToOne(optional = false)
    private Paciente idPaciente;
    @OneToMany(mappedBy = "idCita")
    private List<Consulta> consultaList;

    public Cita() {
    }

    public Cita(Long idCita) {
        this.idCita = idCita;
    }

    public Cita(Long idCita, Date fechaCita, Date horaCita) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(Date horaCita) {
        this.horaCita = horaCita;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public Hospital getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Hospital idHospital) {
        this.idHospital = idHospital;
    }

//    public HospitalClinica getHospitalClinica() {
//        return hospitalClinica;
//    }
//
//    public void setHospitalClinica(HospitalClinica hospitalClinica) {
//        this.hospitalClinica = hospitalClinica;
//    }
//
//    public HospitalSala getHospitalSala() {
//        return hospitalSala;
//    }
//
//    public void setHospitalSala(HospitalSala hospitalSala) {
//        this.hospitalSala = hospitalSala;
//    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
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
        hash += (idCita != null ? idCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.idCita == null && other.idCita != null) || (this.idCita != null && !this.idCita.equals(other.idCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.Cita[ idCita=" + idCita + " ]";
    }
    
}
