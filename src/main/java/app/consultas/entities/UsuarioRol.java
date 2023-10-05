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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DOxlaj
 */
@Entity
@Table(name = "USUARIO_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u"),
    @NamedQuery(name = "UsuarioRol.findByIdUsuario", query = "SELECT u FROM UsuarioRol u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuarioRol.findByIdRol", query = "SELECT u FROM UsuarioRol u WHERE u.idRol = :idRol")})
public class UsuarioRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    @Expose
    private Long idUsuario;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROL")
    @Expose
    private Short idRol;
    
    public UsuarioRol(){
        
    }
    
    public UsuarioRol(Long idUsuario, Short idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public Short getIdRol() {
        return idRol;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdRol(Short idRol) {
        this.idRol = idRol;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.consultas.entities.UsuarioRol[ idUsuario=" + idUsuario + ", idRol=" + idRol + "]";
    }
}
