/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.HospitalSala;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author DOxlaj
 */
@Stateless
public class HospitalSalaFacade extends AbstractFacade<HospitalSala> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HospitalSalaFacade() {
        super(HospitalSala.class);
    }
    
    public List<HospitalSala> findByIdHospital(short idHospital){
        try {
            TypedQuery<HospitalSala> query = em.createNamedQuery("HospitalSala.findByIdHospital", HospitalSala.class);
            query.setParameter("idHospital", idHospital);
            return query.getResultList();
        } catch(Exception ex) {
            return new ArrayList<HospitalSala>();
        }
    }
}
