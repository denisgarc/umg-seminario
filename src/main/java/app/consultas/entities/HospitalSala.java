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
@Table(name = "HOSPITAL_SALA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospitalSala.findAll", query = "SELECT h FROM HospitalSala h"),
    @NamedQuery(name = "HospitalSala.findByIdHospital", query = "SELECT h FROM HospitalSala h WHERE h.hospitalSalaPK.idHospital = :idHospital"),
    @NamedQuery(name = "HospitalSala.findByIdSala", query = "SELECT h FROM HospitalSala h WHERE h.hospitalSalaPK.idSala = :idSala"),
    @NamedQuery(name = "HospitalSala.findByDescripcion", query = "SELECT h FROM HospitalSala h WHERE h.descripcion = :descripcion"),
    @NamedQuery(name = "HospitalSala.findByActivo", query = "SELECT h FROM HospitalSala h WHERE h.activo = :activo")})
public class HospitalSala implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Expose
    protected HospitalSalaPK hospitalSalaPK;
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
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalSala")
    //private List<Cita> citaList;
    @JoinColumn(name = "ID_HOSPITAL", referencedColumnName = "ID_HOSPITAL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Hospital hospital;

    public HospitalSala() {
    }

    public HospitalSala(HospitalSalaPK hospitalSalaPK) {
        this.hospitalSalaPK = hospitalSalaPK;
    }

    public HospitalSala(HospitalSalaPK hospitalSalaPK, String descripcion, String activo) {
        this.hospitalSalaPK = hospitalSalaPK;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public HospitalSala(short idHospital, short idSala) {
        this.hospitalSalaPK = new HospitalSalaPK(idHospital, idSala);
    }

    public HospitalSalaPK getHospitalSalaPK() {
        return hospitalSalaPK;
    }

    public void setHospitalSalaPK(HospitalSalaPK hospitalSalaPK) {
        this.hospitalSalaPK = hospitalSalaPK;
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
        hash += (hospitalSalaPK != null ? hospitalSalaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospitalSala)) {
            return false;
        }
        HospitalSala other = (HospitalSala) object;
        if ((this.hospitalSalaPK == null && other.hospitalSalaPK != null) || (this.hospitalSalaPK != null && !this.hospitalSalaPK.equals(other.hospitalSalaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.HospitalSala[ hospitalSalaPK=" + hospitalSalaPK + " ]";
    }
    
}
