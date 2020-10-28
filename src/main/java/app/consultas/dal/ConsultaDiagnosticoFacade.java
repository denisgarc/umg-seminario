/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.ConsultaDiagnostico;
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
public class ConsultaDiagnosticoFacade extends AbstractFacade<ConsultaDiagnostico> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaDiagnosticoFacade() {
        super(ConsultaDiagnostico.class);
    }
    
    public List<ConsultaDiagnostico> findByIdConsulta(Long idConsulta){
        try {
            Query query = em.createNativeQuery("SELECT CD.* FROM CONSULTA_DIAGNOSTICO CD WHERE CD.ID_CONSULTA = ?", ConsultaDiagnostico.class);
            query.setParameter(1, idConsulta);
            List<ConsultaDiagnostico> result = query.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<ConsultaDiagnostico>();
        }
    }
}
