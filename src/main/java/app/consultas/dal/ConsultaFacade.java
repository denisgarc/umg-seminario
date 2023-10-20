/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.Cita;
import app.consultas.entities.Consulta;
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
public class ConsultaFacade extends AbstractFacade<Consulta> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaFacade() {
        super(Consulta.class);
    }
    
    public Long generateNewId(){
        try {
            Query q = em.createNativeQuery("SELECT CONSULTA_SEQ.NEXTVAL FROM DUAL");
            Long result = Long.parseLong(q.getSingleResult().toString());
            return result;
        } catch(Exception e) {
            return Long.parseLong("0");
        }
    }
    
    public List<Consulta> findByPacienteDate(Long idPaciente, Date fecDesde, Date fecHasta){
        try {
            em.flush();
            Query query = em.createNativeQuery("SELECT con.* FROM CONSULTA con INNER JOIN CITA c ON c.ID_CITA = con.ID_CITA WHERE (c.ID_PACIENTE = ? OR ? = 0) AND con.FECHA_CONSULTA BETWEEN ? AND ?", Consulta.class);
            query.setParameter(1, idPaciente);
            query.setParameter(2, idPaciente);
            query.setParameter(3, fecDesde);
            query.setParameter(4, fecHasta);
            List<Consulta> result = query.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Consulta>();
        }
    }
    
    public List<Consulta> findByPaciente(Long idPaciente) {
        try {
            em.flush();
            Query query = em.createNativeQuery("SELECT con.* FROM CONSULTA con INNER JOIN CITA c ON c.ID_CITA = con.ID_CITA WHERE (c.ID_PACIENTE = ? OR ? = 0)", Consulta.class);
            query.setParameter(1, idPaciente);
            query.setParameter(2, idPaciente);
            List<Consulta> result = query.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Consulta>();
        }
    }
    
    public long createWithId(Consulta entity) {
        em.persist(entity);
        em.flush();
        return entity.getIdConsulta();
    }
    
}
