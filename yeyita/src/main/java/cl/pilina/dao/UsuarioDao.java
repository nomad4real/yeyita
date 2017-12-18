package cl.pilina.dao;

import java.util.List;
import cl.pilina.dao.IUsuarioDao;
import cl.pilina.entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


//import com.querydsl.jpa.impl.JPAQuery;

//@Resource(mappedName="java/yeyita/unidadDePesistencia") 
@Transactional //no se deberia instancia ya que en el datasource tengo definido como JTA
@Repository
public class UsuarioDao implements IUsuarioDao {

	@PersistenceContext(unitName = "unidadDePesistencia", type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	// JPAQuery query = new JPAQuery(entityManager);

	@Override
	public List<Usuario> buscarUsuarios() {
		String sql = "SELECT idusuario,nombre,password FROM usuario ";
		return (List<Usuario>) entityManager.createQuery(sql).getResultList();

	}

	@Override
	public Usuario buscarUsuarioPorId(int usuarioId) {
		
		return entityManager.find(Usuario.class, usuarioId);
	}

	@Override
	public void crearUsuario(Usuario usuario) {
		
		entityManager.persist(usuario);

		entityManager.clear(); //permite obviar la entidad repetida en session
	}

	@Override
	public void updatearUsuario(Usuario usuario) {
		Usuario user = buscarUsuarioPorId(usuario.getIdusuario());
		user.setNombre(usuario.getNombre());
		user.setPassword(usuario.getPassword());
		entityManager.flush();

	}

	@Override
	public void eliminarUsuario(int usuarioId) {
		entityManager.remove(buscarUsuarioPorId(usuarioId));

	}
	
	@Override
	public boolean usuarioExiste(String nombre, String password) {
		
		String sql = "FROM usuario as us WHERE us.nombre = ?";
		int count = entityManager.createQuery(sql).setParameter(1, nombre).getResultList().size();

		return count > 0 ? true : false;
	}

}
