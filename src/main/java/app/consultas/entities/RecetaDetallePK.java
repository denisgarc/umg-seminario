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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author DOxlaj
 */
@Embeddable
public class RecetaDetallePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RECETA")
    @Expose
    private long idReceta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MEDICAMENTO")
    @Expose
    private int idMedicamento;

    public RecetaDetallePK() {
    }

    public RecetaDetallePK(long idReceta, int idMedicamento) {
        this.idReceta = idReceta;
        this.idMedicamento = idMedicamento;
    }

    public long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(long idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idReceta;
        hash += (int) idMedicamento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaDetallePK)) {
            return false;
        }
        RecetaDetallePK other = (RecetaDetallePK) object;
        if (this.idReceta != other.idReceta) {
            return false;
        }
        if (this.idMedicamento != other.idMedicamento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.RecetaDetallePK[ idReceta=" + idReceta + ", idMedicamento=" + idMedicamento + " ]";
    }
    
}
