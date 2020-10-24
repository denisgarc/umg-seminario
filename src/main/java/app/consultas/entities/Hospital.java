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
@Table(name = "HOSPITAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hospital.findAll", query = "SELECT h FROM Hospital h"),
    @NamedQuery(name = "Hospital.findByIdHospital", query = "SELECT h FROM Hospital h WHERE h.idHospital = :idHospital"),
    @NamedQuery(name = "Hospital.findByNombre", query = "SELECT h FROM Hospital h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "Hospital.findByDireccion", query = "SELECT h FROM Hospital h WHERE h.direccion = :direccion"),
    @NamedQuery(name = "Hospital.findByTelefonos", query = "SELECT h FROM Hospital h WHERE h.telefonos = :telefonos"),
    @NamedQuery(name = "Hospital.findByActivo", query = "SELECT h FROM Hospital h WHERE h.activo = :activo")})
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HOSPITAL")
    @Expose
    private Short idHospital;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    @Expose
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECCION")
    @Expose
    private String direccion;
    @Size(max = 100)
    @Column(name = "TELEFONOS")
    @Expose
    private String telefonos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    @Expose
    private String activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHospital")
    private List<Cita> citaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    @Expose
    private List<HospitalSala> hospitalSalaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    @Expose
    private List<HospitalHorario> hospitalHorarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    @Expose
    private List<HospitalClinica> hospitalClinicaList;

    public Hospital() {
    }

    public Hospital(Short idHospital) {
        this.idHospital = idHospital;
    }

    public Hospital(Short idHospital, String nombre, String direccion, String activo) {
        this.idHospital = idHospital;
        this.nombre = nombre;
        this.direccion = direccion;
        this.activo = activo;
    }

    public Short getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Short idHospital) {
        this.idHospital = idHospital;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
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

    @XmlTransient
    public List<HospitalSala> getHospitalSalaList() {
        return hospitalSalaList;
    }

    public void setHospitalSalaList(List<HospitalSala> hospitalSalaList) {
        this.hospitalSalaList = hospitalSalaList;
    }

    @XmlTransient
    public List<HospitalHorario> getHospitalHorarioList() {
        return hospitalHorarioList;
    }

    public void setHospitalHorarioList(List<HospitalHorario> hospitalHorarioList) {
        this.hospitalHorarioList = hospitalHorarioList;
    }

    @XmlTransient
    public List<HospitalClinica> getHospitalClinicaList() {
        return hospitalClinicaList;
    }

    public void setHospitalClinicaList(List<HospitalClinica> hospitalClinicaList) {
        this.hospitalClinicaList = hospitalClinicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHospital != null ? idHospital.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospital)) {
            return false;
        }
        Hospital other = (Hospital) object;
        if ((this.idHospital == null && other.idHospital != null) || (this.idHospital != null && !this.idHospital.equals(other.idHospital))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.Hospital[ idHospital=" + idHospital + " ]";
    }
    
}
