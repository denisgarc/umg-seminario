/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DOxlaj
 */
@Entity
@Table(name = "RECETA_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecetaDetalle.findAll", query = "SELECT r FROM RecetaDetalle r"),
    @NamedQuery(name = "RecetaDetalle.findByIdReceta", query = "SELECT r FROM RecetaDetalle r WHERE r.recetaDetallePK.idReceta = :idReceta"),
    @NamedQuery(name = "RecetaDetalle.findByIdMedicamento", query = "SELECT r FROM RecetaDetalle r WHERE r.recetaDetallePK.idMedicamento = :idMedicamento"),
    @NamedQuery(name = "RecetaDetalle.findByCantidad", query = "SELECT r FROM RecetaDetalle r WHERE r.cantidad = :cantidad"),
    @NamedQuery(name = "RecetaDetalle.findByIndicaciones", query = "SELECT r FROM RecetaDetalle r WHERE r.indicaciones = :indicaciones"),
    @NamedQuery(name = "RecetaDetalle.findByDuracion", query = "SELECT r FROM RecetaDetalle r WHERE r.duracion = :duracion")})
public class RecetaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecetaDetallePK recetaDetallePK;
    @Column(name = "CANTIDAD")
    private Short cantidad;
    @Size(max = 100)
    @Column(name = "INDICACIONES")
    private String indicaciones;
    @Column(name = "DURACION")
    private Short duracion;
    @JoinColumn(name = "ID_MEDICAMENTO", referencedColumnName = "ID_MEDICAMENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Medicamento medicamento;
    @JoinColumn(name = "ID_RECETA", referencedColumnName = "ID_RECETA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receta receta;
    @JoinColumn(name = "ID_UNIDAD", referencedColumnName = "ID_UNIDAD")
    @ManyToOne(optional = false)
    private Unidad idUnidad;

    public RecetaDetalle() {
    }

    public RecetaDetalle(RecetaDetallePK recetaDetallePK) {
        this.recetaDetallePK = recetaDetallePK;
    }

    public RecetaDetalle(long idReceta, int idMedicamento) {
        this.recetaDetallePK = new RecetaDetallePK(idReceta, idMedicamento);
    }

    public RecetaDetallePK getRecetaDetallePK() {
        return recetaDetallePK;
    }

    public void setRecetaDetallePK(RecetaDetallePK recetaDetallePK) {
        this.recetaDetallePK = recetaDetallePK;
    }

    public Short getCantidad() {
        return cantidad;
    }

    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public Short getDuracion() {
        return duracion;
    }

    public void setDuracion(Short duracion) {
        this.duracion = duracion;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Unidad getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Unidad idUnidad) {
        this.idUnidad = idUnidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recetaDetallePK != null ? recetaDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaDetalle)) {
            return false;
        }
        RecetaDetalle other = (RecetaDetalle) object;
        if ((this.recetaDetallePK == null && other.recetaDetallePK != null) || (this.recetaDetallePK != null && !this.recetaDetallePK.equals(other.recetaDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.RecetaDetalle[ recetaDetallePK=" + recetaDetallePK + " ]";
    }
    
}
