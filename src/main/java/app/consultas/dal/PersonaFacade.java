/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.Persona;
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
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
    
    public List<Persona> findBySearch(String filter){
        try {
            Query q = em.createNativeQuery("SELECT p.* FROM PERSONA p WHERE UPPER(CONCAT(p.NOMBRES,IFNULL(CONCAT(' ',p.APELLIDOS),''))) LIKE CONCAT('%',UPPER(?),'%') AND p.ID_PERSONA <> 1 AND p.ACTIVO = 'S';", Persona.class);
            q.setParameter(1, filter.replace(' ', '%'));
            List<Persona> result = q.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Persona>();
        }
    }
    
    public List<Persona> getAll(){
        try {
            Query q = em.createNativeQuery("SELECT p.* FROM PERSONA p WHERE p.ID_PERSONA <> 1;", Persona.class);
            List<Persona> result = q.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Persona>();
        }
    }
    
    public Long generateNewId(){
        try {
            Query q = em.createNativeQuery("SELECT PERSONA_SEQ.NEXTVAL FROM DUAL");
            Long result = Long.parseLong(q.getSingleResult().toString());
            return result;
        } catch(Exception e) {
            return Long.parseLong("0");
        }
    }
    
    public long createWithId(Persona entity) {
        em.persist(entity);
        em.flush();
        return entity.getIdPersona();
    }
}
