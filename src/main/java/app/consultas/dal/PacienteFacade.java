/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.Paciente;
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
public class PacienteFacade extends AbstractFacade<Paciente> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteFacade() {
        super(Paciente.class);
    }
    
    public List<Paciente> findBySearch(String filter){
        try {
            Query q = em.createNativeQuery("SELECT m.* FROM PACIENTE m INNER JOIN PERSONA p ON p.ID_PERSONA = m.ID_PERSONA WHERE UPPER(CONCAT(p.NOMBRES,IFNULL(CONCAT(' ',p.APELLIDOS),''))) LIKE CONCAT('%',UPPER(?),'%')", Paciente.class);
            q.setParameter(1, filter.replace(' ', '%'));
            List<Paciente> result = q.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Paciente>();
        }
    }
    
    public Long generateNewId(){
        try {
            Query q = em.createNativeQuery("SELECT PACIENTE_SEQ.NEXTVAL FROM DUAL");
            Long result = Long.parseLong(q.getSingleResult().toString());
            return result;
        } catch(Exception e) {
            return Long.parseLong("0");
        }
    }
    
    public long createWithId(Paciente entity) {
        em.persist(entity);
        em.flush();
        return entity.getIdPaciente();
    }
}
