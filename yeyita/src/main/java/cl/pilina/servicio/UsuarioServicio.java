package cl.pilina.servicio;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cl.pilina.dao.IUsuarioDao;
import cl.pilina.entidades.Usuario;

@Service
public class UsuarioServicio implements IUsuarioServicio {
	
    @Autowired
    private IUsuarioDao usuarioDao;
    @Override
    public List<Usuario> buscarUsuarios() {
  
        return usuarioDao.buscarUsuarios();
    }

    @Override
    public Usuario buscarUsuarioPorId(int usuarioId) {
        Usuario obj = usuarioDao.buscarUsuarioPorId(usuarioId);
        return obj;
    }
   
    @Override
    public synchronized boolean crearUsuario(Usuario usuario) {
        if (usuarioDao.usuarioExiste(usuario.getNombre(), usuario.getPassword())) {
            return false;
        } else {
            usuarioDao.crearUsuario(usuario);
            return true;
        }
    }

    @Override
    public void updatearUsuario(Usuario usuario) {
       usuarioDao.updatearUsuario(usuario);

    }

    @Override
    public void eliminarUsuario(int usuarioId) {
      usuarioDao.eliminarUsuario(usuarioId);

    }

}
