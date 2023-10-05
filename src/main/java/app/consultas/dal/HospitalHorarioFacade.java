/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.dal;

import app.consultas.entities.HospitalHorario;
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
public class HospitalHorarioFacade extends AbstractFacade<HospitalHorario> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HospitalHorarioFacade() {
        super(HospitalHorario.class);
    }
    
    public List<HospitalHorario> findByIdHospital(short idHospital){
        try {
            TypedQuery<HospitalHorario> query = em.createNamedQuery("HospitalHorario.findByIdHospital", HospitalHorario.class);
            query.setParameter("idHospital", idHospital);
            return query.getResultList();
        } catch(Exception ex) {
            return new ArrayList<HospitalHorario>();
        }
    }
}
