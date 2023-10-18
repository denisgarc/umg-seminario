/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.Paciente;
import app.consultas.entities.PacienteStatistics;
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
    
    public PacienteStatistics getPacienteStatistics() {
    try {
        Query query = em.createNativeQuery("SELECT COUNT(T1.SEXO) AS TOTAL, " +
                "SUM(CASE WHEN T1.SEXO = 'M' THEN 1 ELSE 0 END) AS HOMBRES, " +
                "SUM(CASE WHEN T1.SEXO = 'F' THEN 1 ELSE 0 END) AS MUJERES " +
                "FROM ErmitaCitasDB.PACIENTE T0 " +
                "INNER JOIN ErmitaCitasDB.PERSONA T1 ON T0.ID_PERSONA = T1.ID_PERSONA");
       
        Object[] result = (Object[]) query.getSingleResult();
        int total = ((Number) result[0]).intValue();
        int hombres = ((Number) result[1]).intValue();
        int mujeres = ((Number) result[2]).intValue();

        return new PacienteStatistics(total, hombres, mujeres);
        
        /*List<PacienteStatistics> result = query.getResultList();
        return result.get(0);*/
    } catch (NoResultException nr) {
        return new PacienteStatistics(0, 0, 0);
    }
}
    
}
