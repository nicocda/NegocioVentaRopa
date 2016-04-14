package negocio;
import entidades.Usuario;
import datos.CatalogoUsuarios;

public class ControladorTransaccion {

	public Usuario buscarUsuario(String id, String pass) {
		
		return CatalogoUsuarios.buscarUsuario(id, pass);
		
	}

}
