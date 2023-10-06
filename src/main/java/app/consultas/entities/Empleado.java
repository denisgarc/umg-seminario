/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DOxlaj
 */
@Entity
@Table(name = "EMPLEADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "Empleado.findByFecIngreso", query = "SELECT e FROM Empleado e WHERE e.fecIngreso = :fecIngreso"),
    @NamedQuery(name = "Empleado.findByFecBaja", query = "SELECT e FROM Empleado e WHERE e.fecBaja = :fecBaja"),
    @NamedQuery(name = "Empleado.findByActivo", query = "SELECT e FROM Empleado e WHERE e.activo = :activo")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EMPLEADO")
    @Expose
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idEmpleado;
    @Column(name = "FEC_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    @Expose
    private Date fecIngreso;
    @Column(name = "FEC_BAJA")
    @Temporal(TemporalType.TIMESTAMP)
    @Expose
    private Date fecBaja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    @Expose
    private String activo;
    @JoinColumn(name = "ID_ESPECIALIZACION", referencedColumnName = "ID_ESPECIALIZACION")
    @ManyToOne(optional = false)
    @Expose
    private Especializacion idEspecializacion;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    @Expose
    private Persona idPersona;
    @JoinColumn(name = "ID_PUESTO", referencedColumnName = "ID_PUESTO")
    @ManyToOne(optional = false)
    @Expose
    private Puesto idPuesto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<Consulta> consultaList;

    public Empleado() {
    }

    public Empleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(Long idEmpleado, String activo) {
        this.idEmpleado = idEmpleado;
        this.activo = activo;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getFecIngreso() {
        return fecIngreso;
    }

    public void setFecIngreso(Date fecIngreso) {
        this.fecIngreso = fecIngreso;
    }

    public Date getFecBaja() {
        return fecBaja;
    }

    public void setFecBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Especializacion getIdEspecializacion() {
        return idEspecializacion;
    }

    public void setIdEspecializacion(Especializacion idEspecializacion) {
        this.idEspecializacion = idEspecializacion;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Puesto getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Puesto idPuesto) {
        this.idPuesto = idPuesto;
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
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.Empleado[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
