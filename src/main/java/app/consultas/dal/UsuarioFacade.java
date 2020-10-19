package app.consultas.dal;

import app.consultas.entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario GetUsuarioByCode(String code){
        try {
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUsuario", Usuario.class);
            query.setParameter("usuario", code);
            return query.getSingleResult();
        } catch(NoResultException nr){
            return null;
        }
    }
}
