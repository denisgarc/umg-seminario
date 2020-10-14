/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author DOxlaj
 */
@Embeddable
public class HospitalClinicaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HOSPITAL")
    private short idHospital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CLINICA")
    private short idClinica;

    public HospitalClinicaPK() {
    }

    public HospitalClinicaPK(short idHospital, short idClinica) {
        this.idHospital = idHospital;
        this.idClinica = idClinica;
    }

    public short getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(short idHospital) {
        this.idHospital = idHospital;
    }

    public short getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(short idClinica) {
        this.idClinica = idClinica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idHospital;
        hash += (int) idClinica;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospitalClinicaPK)) {
            return false;
        }
        HospitalClinicaPK other = (HospitalClinicaPK) object;
        if (this.idHospital != other.idHospital) {
            return false;
        }
        if (this.idClinica != other.idClinica) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.HospitalClinicaPK[ idHospital=" + idHospital + ", idClinica=" + idClinica + " ]";
    }
    
}
