/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.Cita;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DOxlaj
 */
@Stateless
public class CitaFacade extends AbstractFacade<Cita> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaFacade() {
        super(Cita.class);
    }
 
    public Long generateNewId(){
        try {
            Query q = em.createNativeQuery("SELECT CITA_SEQ.NEXTVAL FROM DUAL");
            Long result = Long.parseLong(q.getSingleResult().toString());
            return result;
        } catch(Exception e) {
            return Long.parseLong("0");
        }
    }
}
