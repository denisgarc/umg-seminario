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
public class HospitalSalaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HOSPITAL")
    private short idHospital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SALA")
    private short idSala;

    public HospitalSalaPK() {
    }

    public HospitalSalaPK(short idHospital, short idSala) {
        this.idHospital = idHospital;
        this.idSala = idSala;
    }

    public short getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(short idHospital) {
        this.idHospital = idHospital;
    }

    public short getIdSala() {
        return idSala;
    }

    public void setIdSala(short idSala) {
        this.idSala = idSala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idHospital;
        hash += (int) idSala;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospitalSalaPK)) {
            return false;
        }
        HospitalSalaPK other = (HospitalSalaPK) object;
        if (this.idHospital != other.idHospital) {
            return false;
        }
        if (this.idSala != other.idSala) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.HospitalSalaPK[ idHospital=" + idHospital + ", idSala=" + idSala + " ]";
    }
    
}
