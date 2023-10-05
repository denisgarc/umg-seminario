/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.ConsultaDiagnostico;
import app.consultas.entities.ConsultaTratamiento;
import java.util.ArrayList;
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
public class ConsultaTratamientoFacade extends AbstractFacade<ConsultaTratamiento> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaTratamientoFacade() {
        super(ConsultaTratamiento.class);
    }
    
    public List<ConsultaTratamiento> findByIdConsulta(Long idConsulta){
        try {
            Query query = em.createNativeQuery("SELECT CT.* FROM CONSULTA_TRATAMIENTO CT WHERE CT.ID_CONSULTA = ?", ConsultaTratamiento.class);
            query.setParameter(1, idConsulta);
            List<ConsultaTratamiento> result = query.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<ConsultaTratamiento>();
        }
    }
}
