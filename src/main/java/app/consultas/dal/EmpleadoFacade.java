/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.Empleado;
import app.consultas.entities.PersonalStatistics;
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
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
 
    public List<Empleado> findBySearch(String filter){
        try {
            Query q = em.createNativeQuery("SELECT m.* FROM EMPLEADO m INNER JOIN PERSONA p ON p.ID_PERSONA = m.ID_PERSONA WHERE UPPER(CONCAT(p.NOMBRES,IFNULL(CONCAT(' ',p.APELLIDOS),''))) LIKE CONCAT('%',UPPER(?),'%')", Empleado.class);
            q.setParameter(1, filter.replace(' ', '%'));
            List<Empleado> result = q.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Empleado>();
        }
    }
    
    /* -------------------- Cambios de Francisco Lopez -------------------- */
    
            public PersonalStatistics getPersonalStatistics() {
    try {
        Query query = em.createNativeQuery("SELECT COUNT(T1.SEXO) AS TOTAL, " +
                "SUM(CASE WHEN T1.SEXO = 'M' THEN 1 ELSE 0 END) AS HOMBRES, " +
                "SUM(CASE WHEN T1.SEXO = 'F' THEN 1 ELSE 0 END) AS MUJERES " +
                "FROM DOXLAJ.EMPLEADO T0 " +
                "INNER JOIN DOXLAJ.PERSONA T1 ON T0.ID_PERSONA = T1.ID_PERSONA");
       
        Object[] result = (Object[]) query.getSingleResult();
        int total = ((Number) result[0]).intValue();
        int hombres = ((Number) result[1]).intValue();
        int mujeres = ((Number) result[2]).intValue();

        return new PersonalStatistics(total, hombres, mujeres);
        
        /*List<PacienteStatistics> result = query.getResultList();
        return result.get(0);*/
    } catch (NoResultException nr) {
        return new PersonalStatistics(0, 0, 0);
    }
}
            
            /* -------------------- Cambios de Francisco Lopez -------------------- */
}
