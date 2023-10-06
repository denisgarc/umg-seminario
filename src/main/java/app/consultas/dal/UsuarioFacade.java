package app.consultas.dal;

import app.consultas.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
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
    
    public List<Usuario> GetUsuarioByFilter(String filter){
        try {
            Query q = em.createNativeQuery("SELECT u.ID_USUARIO, u.ID_PERSONA, u.USUARIO, u.CONTRASENA, u.FEC_VTO_CONTRASENA, u.ACTIVO FROM USUARIO u INNER JOIN PERSONA p ON p.ID_PERSONA = u.ID_PERSONA WHERE UPPER(CONCAT(u.USUARIO,' - ',p.NOMBRES,IFNULL(CONCAT(' ',p.APELLIDOS),''))) LIKE CONCAT('%',UPPER(?),'%')", Usuario.class);
            //q.setParameter("filter", filter.replace(' ', '%'));
            q.setParameter(1, filter.replace(' ', '%'));
            List<Usuario> result = q.getResultList();
            return result;
        } catch(NoResultException nr){
            return new ArrayList<Usuario>();
        }
    }
    
    /*
    public List<Usuario> GetUsuarioByFilter(String filter){
        try {
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.search", Usuario.class);
            query.setParameter("filter", filter);
            return query.getResultList();
        } catch(NoResultException nr){
            return new ArrayList<Usuario>();
        }
    }
    */
}
