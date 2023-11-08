/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.Consulta;
import app.consultas.entities.ConsultaDiagnostico;
import app.consultas.entities.ConsultaTratamiento;
import app.consultas.entities.Receta;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author DOxlaj
 */
@Stateless
public class RecetaFacade extends AbstractFacade<Receta> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecetaFacade() {
        super(Receta.class);
    }
    
    public Receta findByIdConsulta(Long idConsulta){
        try {
            Query query = em.createNativeQuery("SELECT R.* FROM RECETA R WHERE R.ID_CONSULTA = ?", Receta.class);
            query.setParameter(1, idConsulta);
            Receta result = (Receta)query.getSingleResult();
            return result;
        } catch(NoResultException nr){
            return null;
        }
    }
    
    public List<Receta> findListByIdConsulta(Long idConsulta){
        try {
            em.flush();
            TypedQuery<Receta> query = em.createNamedQuery("Receta.findByIdConsulta", Receta.class);
            query.setParameter("idConsulta", new Consulta(idConsulta));
            List<Receta> result = query.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Receta>();
        }
    }
    
    public Long generateNewId(){
        try {
            Query q = em.createNativeQuery("SELECT RECETA_SEQ.NEXTVAL FROM DUAL");
            Long result = Long.parseLong(q.getSingleResult().toString());
            return result;
        } catch(Exception e) {
            return Long.parseLong("0");
        }
    }
    
    public long createWithId(Receta entity) {
        em.persist(entity);
        em.flush();
        return entity.getIdReceta();
    }
}
