/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DOxlaj
 */
@Entity
@Table(name = "HOSPITAL_HORARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospitalHorario.findAll", query = "SELECT h FROM HospitalHorario h"),
    @NamedQuery(name = "HospitalHorario.findByIdHospital", query = "SELECT h FROM HospitalHorario h WHERE h.hospitalHorarioPK.idHospital = :idHospital"),
    @NamedQuery(name = "HospitalHorario.findByIdHorario", query = "SELECT h FROM HospitalHorario h WHERE h.hospitalHorarioPK.idHorario = :idHorario"),
    @NamedQuery(name = "HospitalHorario.findByDescripcion", query = "SELECT h FROM HospitalHorario h WHERE h.descripcion = :descripcion"),
    @NamedQuery(name = "HospitalHorario.findByHoraInicio", query = "SELECT h FROM HospitalHorario h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "HospitalHorario.findByHoraFin", query = "SELECT h FROM HospitalHorario h WHERE h.horaFin = :horaFin"),
    @NamedQuery(name = "HospitalHorario.findByActivo", query = "SELECT h FROM HospitalHorario h WHERE h.activo = :activo")})
public class HospitalHorario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HospitalHorarioPK hospitalHorarioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    private String activo;
    @JoinColumn(name = "ID_HOSPITAL", referencedColumnName = "ID_HOSPITAL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Hospital hospital;

    public HospitalHorario() {
    }

    public HospitalHorario(HospitalHorarioPK hospitalHorarioPK) {
        this.hospitalHorarioPK = hospitalHorarioPK;
    }

    public HospitalHorario(HospitalHorarioPK hospitalHorarioPK, String descripcion, Date horaInicio, Date horaFin, String activo) {
        this.hospitalHorarioPK = hospitalHorarioPK;
        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.activo = activo;
    }

    public HospitalHorario(short idHospital, short idHorario) {
        this.hospitalHorarioPK = new HospitalHorarioPK(idHospital, idHorario);
    }

    public HospitalHorarioPK getHospitalHorarioPK() {
        return hospitalHorarioPK;
    }

    public void setHospitalHorarioPK(HospitalHorarioPK hospitalHorarioPK) {
        this.hospitalHorarioPK = hospitalHorarioPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hospitalHorarioPK != null ? hospitalHorarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospitalHorario)) {
            return false;
        }
        HospitalHorario other = (HospitalHorario) object;
        if ((this.hospitalHorarioPK == null && other.hospitalHorarioPK != null) || (this.hospitalHorarioPK != null && !this.hospitalHorarioPK.equals(other.hospitalHorarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.HospitalHorario[ hospitalHorarioPK=" + hospitalHorarioPK + " ]";
    }
    
}
