package cl.pilina.controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cl.pilina.entidades.Usuario;
import cl.pilina.servicio.IUsuarioServicio;


@Controller
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/")
public class ControladorUsuario {
	@Autowired
	private IUsuarioServicio servicioUsuario;

	@RequestMapping(value = "/usuario/{idusuario}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable("idusuario") String id) {
		Usuario user = servicioUsuario.buscarUsuarioPorId(Integer.parseInt(id));
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/all-usuarios/", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> buscarAllUsuario() {
		List<Usuario> lista = servicioUsuario.buscarUsuarios();
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/", method = RequestMethod.POST)
	public ResponseEntity<Void> crearUsuario(@RequestBody Usuario user, UriComponentsBuilder builder) {
        boolean alerta = servicioUsuario.crearUsuario(user);
        if (alerta == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/usuario?id={usuarioId}").buildAndExpand(user.getIdusuario()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/usuario/{usuarioId}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> updatearUsuario(@PathVariable("usuarioId") int id, @RequestBody Usuario user) {
	    servicioUsuario.updatearUsuario(user);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{usuarioId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminarUsuario(@PathVariable("usuarioId") int id) {
	    servicioUsuario.eliminarUsuario(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 