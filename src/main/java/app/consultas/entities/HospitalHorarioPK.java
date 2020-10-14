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
public class HospitalHorarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HOSPITAL")
    private short idHospital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HORARIO")
    private short idHorario;

    public HospitalHorarioPK() {
    }

    public HospitalHorarioPK(short idHospital, short idHorario) {
        this.idHospital = idHospital;
        this.idHorario = idHorario;
    }

    public short getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(short idHospital) {
        this.idHospital = idHospital;
    }

    public short getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(short idHorario) {
        this.idHorario = idHorario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idHospital;
        hash += (int) idHorario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospitalHorarioPK)) {
            return false;
        }
        HospitalHorarioPK other = (HospitalHorarioPK) object;
        if (this.idHospital != other.idHospital) {
            return false;
        }
        if (this.idHorario != other.idHorario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.HospitalHorarioPK[ idHospital=" + idHospital + ", idHorario=" + idHorario + " ]";
    }
    
}
