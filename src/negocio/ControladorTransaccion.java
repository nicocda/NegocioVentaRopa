package negocio;
import entidades.Producto;
import entidades.Usuario;
import datos.CatalogoProductos;
import datos.CatalogoUsuarios;

public class ControladorTransaccion {

	public Usuario buscarUsuario(String id, String pass) {
		
		return CatalogoUsuarios.buscarUsuario(id, pass);
		
	}
	
	public static Producto buscarProducto (String id)
	{
		return CatalogoProductos.buscarProducto(id);
	}

}
