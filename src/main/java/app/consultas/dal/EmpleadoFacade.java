/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.Empleado;
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
            Query q = em.createNativeQuery("SELECT m.* FROM EMPLEADO m INNER JOIN PERSONA p ON p.ID_PERSONA = m.ID_PERSONA WHERE UPPER(p.NOMBRES || NVL(' ' || p.APELLIDOS,'')) LIKE '%' || UPPER(?) || '%'", Empleado.class);
            q.setParameter(1, filter.replace(' ', '%'));
            List<Empleado> result = q.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Empleado>();
        }
    }
}
