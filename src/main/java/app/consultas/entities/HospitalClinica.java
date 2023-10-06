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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "HOSPITAL_CLINICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospitalClinica.findAll", query = "SELECT h FROM HospitalClinica h"),
    @NamedQuery(name = "HospitalClinica.findByIdHospital", query = "SELECT h FROM HospitalClinica h WHERE h.hospitalClinicaPK.idHospital = :idHospital"),
    @NamedQuery(name = "HospitalClinica.findByIdClinica", query = "SELECT h FROM HospitalClinica h WHERE h.hospitalClinicaPK.idClinica = :idClinica"),
    @NamedQuery(name = "HospitalClinica.findByDescripcion", query = "SELECT h FROM HospitalClinica h WHERE h.descripcion = :descripcion"),
    @NamedQuery(name = "HospitalClinica.findByActivo", query = "SELECT h FROM HospitalClinica h WHERE h.activo = :activo")})
public class HospitalClinica implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Expose
    protected HospitalClinicaPK hospitalClinicaPK;
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
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalClinica")
//    private List<Cita> citaList;
    @JoinColumn(name = "ID_HOSPITAL", referencedColumnName = "ID_HOSPITAL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Hospital hospital;

    public HospitalClinica() {
    }

    public HospitalClinica(HospitalClinicaPK hospitalClinicaPK) {
        this.hospitalClinicaPK = hospitalClinicaPK;
    }

    public HospitalClinica(HospitalClinicaPK hospitalClinicaPK, String descripcion, String activo) {
        this.hospitalClinicaPK = hospitalClinicaPK;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public HospitalClinica(short idHospital, short idClinica) {
        this.hospitalClinicaPK = new HospitalClinicaPK(idHospital, idClinica);
    }

    public HospitalClinicaPK getHospitalClinicaPK() {
        return hospitalClinicaPK;
    }

    public void setHospitalClinicaPK(HospitalClinicaPK hospitalClinicaPK) {
        this.hospitalClinicaPK = hospitalClinicaPK;
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

//    @XmlTransient
//    public List<Cita> getCitaList() {
//        return citaList;
//    }
//
//    public void setCitaList(List<Cita> citaList) {
//        this.citaList = citaList;
//    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hospitalClinicaPK != null ? hospitalClinicaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospitalClinica)) {
            return false;
        }
        HospitalClinica other = (HospitalClinica) object;
        if ((this.hospitalClinicaPK == null && other.hospitalClinicaPK != null) || (this.hospitalClinicaPK != null && !this.hospitalClinicaPK.equals(other.hospitalClinicaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.HospitalClinica[ hospitalClinicaPK=" + hospitalClinicaPK + " ]";
    }
    
}
