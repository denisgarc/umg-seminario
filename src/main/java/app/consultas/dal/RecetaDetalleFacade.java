/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.ConsultaTratamiento;
import app.consultas.entities.RecetaDetalle;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author DOxlaj
 */
@Stateless
public class RecetaDetalleFacade extends AbstractFacade<RecetaDetalle> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecetaDetalleFacade() {
        super(RecetaDetalle.class);
    }
    
    public List<RecetaDetalle> findByIdReceta(Long IdReceta){
        try {
            TypedQuery<RecetaDetalle> query = em.createNamedQuery("RecetaDetalle.findByIdReceta", RecetaDetalle.class);
            query.setParameter("idReceta", IdReceta);
            List<RecetaDetalle> result = query.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<RecetaDetalle>();
        }
    }
}
