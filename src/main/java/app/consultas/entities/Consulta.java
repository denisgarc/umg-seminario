/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "CONSULTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c"),
    @NamedQuery(name = "Consulta.findByIdConsulta", query = "SELECT c FROM Consulta c WHERE c.idConsulta = :idConsulta"),
    @NamedQuery(name = "Consulta.findByFechaConsulta", query = "SELECT c FROM Consulta c WHERE c.fechaConsulta = :fechaConsulta"),
    @NamedQuery(name = "Consulta.findByDescripcion", query = "SELECT c FROM Consulta c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Consulta.findByObservaciones", query = "SELECT c FROM Consulta c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "Consulta.findByActivo", query = "SELECT c FROM Consulta c WHERE c.activo = :activo")})
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CONSULTA")
    private Long idConsulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONSULTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConsulta;
    @Size(max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    private String activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsulta")
    private List<ConsultaDiagnostico> consultaDiagnosticoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsulta")
    private List<ConsultaTratamiento> consultaTratamientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsulta")
    private List<Receta> recetaList;
    @JoinColumn(name = "ID_CITA", referencedColumnName = "ID_CITA")
    @ManyToOne
    private Cita idCita;
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID_EMPLEADO")
    @ManyToOne(optional = false)
    private Empleado idEmpleado;
    @JoinColumn(name = "ID_TIPO_CONSULTA", referencedColumnName = "ID_TIPO_CONSULTA")
    @ManyToOne(optional = false)
    private TipoConsulta idTipoConsulta;

    public Consulta() {
    }

    public Consulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Consulta(Long idConsulta, Date fechaConsulta, String activo) {
        this.idConsulta = idConsulta;
        this.fechaConsulta = fechaConsulta;
        this.activo = activo;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<ConsultaDiagnostico> getConsultaDiagnosticoList() {
        return consultaDiagnosticoList;
    }

    public void setConsultaDiagnosticoList(List<ConsultaDiagnostico> consultaDiagnosticoList) {
        this.consultaDiagnosticoList = consultaDiagnosticoList;
    }

    @XmlTransient
    public List<ConsultaTratamiento> getConsultaTratamientoList() {
        return consultaTratamientoList;
    }

    public void setConsultaTratamientoList(List<ConsultaTratamiento> consultaTratamientoList) {
        this.consultaTratamientoList = consultaTratamientoList;
    }

    @XmlTransient
    public List<Receta> getRecetaList() {
        return recetaList;
    }

    public void setRecetaList(List<Receta> recetaList) {
        this.recetaList = recetaList;
    }

    public Cita getIdCita() {
        return idCita;
    }

    public void setIdCita(Cita idCita) {
        this.idCita = idCita;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public TipoConsulta getIdTipoConsulta() {
        return idTipoConsulta;
    }

    public void setIdTipoConsulta(TipoConsulta idTipoConsulta) {
        this.idTipoConsulta = idTipoConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsulta != null ? idConsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.idConsulta == null && other.idConsulta != null) || (this.idConsulta != null && !this.idConsulta.equals(other.idConsulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.Consulta[ idConsulta=" + idConsulta + " ]";
    }
    
}
