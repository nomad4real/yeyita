package cl.pilina.servicio;

import java.util.List;

import cl.pilina.entidades.Usuario;

public interface IUsuarioServicio {

    
    List<Usuario> buscarUsuarios();
    Usuario buscarUsuarioPorId(int usuarioId);
    boolean crearUsuario(Usuario usuario);
    void updatearUsuario(Usuario usuario);
    void eliminarUsuario(int usuarioId);

}
