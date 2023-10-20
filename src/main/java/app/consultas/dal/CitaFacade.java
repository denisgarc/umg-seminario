/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.Cita;
import app.consultas.entities.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    
    public List<Cita> findByPacienteDate(Long idPaciente, Date fecDesde, Date fecHasta){
        try {
            TypedQuery<Cita> query = em.createNamedQuery("Cita.findByPacienteDate", Cita.class);
            query.setParameter("idPaciente", idPaciente);
            query.setParameter("fecDesde", fecDesde);
            query.setParameter("fecHasta", fecHasta);
            List<Cita> result = query.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Cita>();
        }
    }
    
    public List<Cita> findByPaciente(Long idPaciente){
        try {
            TypedQuery<Cita> query = em.createNamedQuery("Cita.findByPaciente", Cita.class);
            query.setParameter("idPaciente", idPaciente);
            List<Cita> result = query.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Cita>();
        }
    }
    
    public long createWithId(Cita entity) {
        em.persist(entity);
        em.flush();
        return entity.getIdCita();
    }
}
