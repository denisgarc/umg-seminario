/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DOxlaj
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
 
    public List<Rol> findByidUser(Long idUsuario){
        try {
            Query q = em.createNativeQuery("SELECT r.ID_ROL, r.DESCRIPCION, r.ACTIVO FROM ROL r INNER JOIN USUARIO_ROL u ON u.ID_ROL = r.ID_ROL WHERE u.ID_USUARIO = ?", Rol.class);
            q.setParameter(1, idUsuario);
            List<Rol> result = q.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Rol>();
        }
    }
}
