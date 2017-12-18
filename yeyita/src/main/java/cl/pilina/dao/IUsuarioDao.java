package cl.pilina.dao;

import java.util.List;



import cl.pilina.entidades.Usuario;


public interface IUsuarioDao {
    List<Usuario> buscarUsuarios();
    Usuario buscarUsuarioPorId(int usuarioId);
    void crearUsuario(Usuario usuario);
    void updatearUsuario(Usuario usuario);
    void eliminarUsuario(int usuarioId);
    boolean usuarioExiste(String nombre, String password);
}
