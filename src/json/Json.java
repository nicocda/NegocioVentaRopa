package json;

import java.util.ArrayList;

import entidades.Cliente;
import entidades.Usuario;

public class Json 
{
	//region clientes
	public static String cliente(Cliente cliente)
	{
		String output = "{";
		output = output + "\"apellido\":\"" + cliente.getApellido() + "\"";
		output = output + ", ";
		output = output + "\"nombre\":\"" + cliente.getNombre() + "\"";
		output = output + ", ";
		output = output + "\"telefono\":\"" + cliente.getTelefono() + "\"";
		output = output + ", ";
		output = output + "\"domicilio\":\"" + cliente.getDireccion() + "\"";
		output = output + "}";
		
		return output;
	}
	
	public static String listarClientes(ArrayList<Cliente> clientes) 
	{
		String output = "{[";
		
		int count = 0;
		
		for (Cliente cliente : clientes)
		{
			output = output + cliente(cliente);
			
			count ++;
			
			if (count < clientes.size())
				output = output + ", ";
		}
		
		output = output + "]}";
		
		return output;
	}
	//endregion
	
	//region usuarios
	public static String usuario(Usuario usuario)
	{
		String output = "{";
		output = output + "\"usuario\":\"" + usuario.getUsuario() + "\"";
		output = output + ", ";
		output = output + "\"nombreYApellido\":\"" + usuario.getNombreYApellido() + "\"";
		output = output + ", ";
		output = output + "\"email\":\"" + usuario.getEmail() + "\"";
		output = output + ", ";
		output = output + "\"password\":\"" + usuario.getPassword() + "\"";
		output = output + ", ";
		output = output + "\"sucursal\":\"" + usuario.getSucursal() + "\"";
		output = output + ", ";
		output = output + "\"tipoUsuario\":\"" + usuario.getTipoUsuario() + "\"";
		output = output + "}";
		
		return output;
	}
	
	public static String listarUsuarios(ArrayList<Usuario> usuarios)
	{
		String output = "{[";
		
		int count = 0;
		
		for (Usuario usuario : usuarios)
		{
			output = output + usuario(usuario);
			
			count ++;
			
			if (count < usuarios.size())
				output = output + ", ";
		}
		
		output = output + "]}";
		
		return output;
	}
	//endregion
}
