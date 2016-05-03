package datos;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.naming.java.javaURLContextFactory;

import conexion.DataConnection;
import entidades.Cliente;
import entidades.Prestamo;
import entidades.Producto;
import entidades.Reserva;
import entidades.Venta;
import excepciones.RespuestaServidor;

public class CatalogoVentas {

	
	public static ArrayList<Venta> buscarVentas()
	{
		String sql="select * from venta v "
				+ "inner join cliente cl on v.idCliente=cl.id "
				+ "order by fechaVenta desc, idCliente";
		PreparedStatement sentencia = null;
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs =sentencia.executeQuery();
			
			while(rs.next())
			{
				Venta v = new Venta();
				Cliente cliente = new Cliente();
				//creo cliente 
				cliente.setId(rs.getInt("id"));
				cliente.setNombreApellido(rs.getString("nombreApellido"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(rs.getString("telefono"));
				//seteo el cliente en la venta
				v.setCliente(cliente);
				v.setFechaVenta(rs.getDate("fechaVenta"));
				v.setFormaPago(rs.getInt("formaPago"));
				v.setId(rs.getInt("id"));
				v.setImporte(rs.getFloat("importe"));
				//busco los productos en el catalogo de productos y los seteo en la vento
				v.setProductos(CatalogoProductos.buscarProductosVenta(v.getId()));
				ventas.add(v);
			}
			
		}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
		finally
			{
				try
				{
					if(sentencia!=null && !sentencia.isClosed())
					{
						sentencia.close();
					}
					DataConnection.getInstancia().CloseConn();
				}
				catch (SQLException sqle)
				{
					sqle.printStackTrace();
				}
			}
		return ventas;
	}
	
	public static ArrayList<Venta> buscarVentas(Cliente cl)
	{
		String sql="select * from venta "
				+ "where idCliente=? "
				+ "order by fechaVenta desc";
		PreparedStatement sentencia = null;
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		Connection con = DataConnection.getInstancia().getConn();
		
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, cl.getId());
			ResultSet rs =sentencia.executeQuery();
			
			while(rs.next())
			{
				Venta v = new Venta();
				Cliente cliente = new Cliente();
				//creo cliente 
				cliente.setId(rs.getInt("id"));
				cliente.setNombreApellido(rs.getString("nombreApellido"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(rs.getString("telefono"));
				//seteo el cliente en la venta
				v.setCliente(cliente);
				v.setFechaVenta(rs.getDate("fechaVenta"));
				v.setFormaPago(rs.getInt("formaPago"));
				v.setId(rs.getInt("id"));
				v.setImporte(rs.getFloat("importe"));
				//busco los productos en el catalogo de productos y los seteo en la vento
				int id=rs.getInt("id");
				v.setProductos(CatalogoProductos.buscarProductosVenta(id));
				ventas.add(v);
			}
			
		}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
		finally
			{
				try
				{
					if(sentencia!=null && !sentencia.isClosed())
					{
						sentencia.close();
					}
					DataConnection.getInstancia().CloseConn();
				}
				catch (SQLException sqle)
				{
					sqle.printStackTrace();
				}
			}
		return ventas;
	}
	
	public static void registrarVenta(Venta vta) throws RespuestaServidor
	{
		RespuestaServidor sr = new RespuestaServidor();
		sr = validarVenta(vta);
		if(!sr.getStatus())
			throw sr;
		int idVenta = -1;
		String sql="insert into venta (fechaVenta,idCliente,formaPago, importe) values (?,?,?,?)";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setDate(1, new java.sql.Date(vta.getFechaVenta().getTime()));
			sentencia.setInt(2, vta.getCliente().getId());
			sentencia.setInt(3, vta.getFormaPago());
			sentencia.setFloat(4, vta.getImporte());
			//obtengo el id generado para modificar el idVenta de los productos relacionados
			//el modificar idVenta de los productos esta hecho en el finnally porque se tiene que ejecutar despues de cerrar la conexion
			sentencia.executeUpdate();
		}
		catch(SQLException e)
		{
			sr.addError(e);
			throw sr;
			//e.printStackTrace();
		}
		finally
		{
			try
			{
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				DataConnection.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sr.addError(sqle);
				throw sr;
				//sqle.printStackTrace();
			}
			idVenta = ultimoNroVenta();
			//Deberíamos hacer un stored procedure con StartTransaction.
			CatalogoProductos.venderProducto(idVenta, vta.getProductos());
		}
		
		
	}
	
	private static RespuestaServidor validarVenta(Venta vta) {
		RespuestaServidor sr = new RespuestaServidor();
		if(vta.getProductos().isEmpty())
		{
			sr.addError("La venta no posee ningún producto");
		}
		if(vta.getFormaPago() == 0)
		{
			sr.addError("No seleccionó la forma de pago");
		}
		if(vta.getCliente() == null)
		{
			sr.addError("El cliente ingresado no es válido");
		}
		return sr;
	}

	private static int ultimoNroVenta()
	{
		String sql = "select MAX(id) from venta";
		PreparedStatement sentencia = null;
		int nroVenta = 0;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = sentencia.executeQuery();
			if(rs.next())
			{
				nroVenta = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return nroVenta;
	}

	public static Venta buscarVenta(int idVenta) 
	{
		String sql="select * from venta v "
				+ "inner join cliente cl on v.idCliente=cl.id "
				+ "where v.id=?";
		PreparedStatement sentencia = null;
		Venta venta = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, idVenta);
			ResultSet rs =sentencia.executeQuery();
			
			if(rs.next())
			{
				venta = new Venta();
				Cliente cliente = new Cliente();
				//creo un cliente 
				cliente.setId(rs.getInt("v.id"));
				cliente.setNombreApellido(rs.getString("nombreApellido"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(rs.getString("telefono"));
				//seteo el cliente en la venta
				venta.setCliente(cliente);
				venta.setFechaVenta(rs.getDate("fechaVenta"));
				venta.setFormaPago(rs.getInt("formaPago"));
				venta.setId(rs.getInt("v.id"));
				venta.setImporte(rs.getFloat("importe"));
				//busco los productos en el catalogo de productos y los seteo en la vento
				int id=rs.getInt("v.id");
				venta.setProductos(CatalogoProductos.buscarProductosVenta(id));
			}
			
		}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
		finally
			{
				try
				{
					if(sentencia!=null && !sentencia.isClosed())
					{
						sentencia.close();
					}
					DataConnection.getInstancia().CloseConn();
				}
				catch (SQLException sqle)
				{
					sqle.printStackTrace();
				}
			}
		return venta;
	}

	public static ArrayList<Venta> buscarVentasPorDia(Date fechaMin, Date fechaMax) throws RespuestaServidor
	{
		String sql="select * from venta where fechaVenta BETWEEN ? AND ? order by fechaVenta desc";
		PreparedStatement sentencia = null;
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setDate(1, new java.sql.Date(fechaMin.getTime()));
			sentencia.setDate(2, new java.sql.Date(fechaMax.getTime()));
			ResultSet rs =sentencia.executeQuery();
			while(rs.next())
			{
				Venta v = new Venta();
				//v.setCliente(CatalogoClientes.buscarCliente(id));
				v.setFechaVenta(rs.getDate("fechaVenta"));
				v.setFormaPago(rs.getInt("formaPago"));
				v.setId(rs.getInt("id"));
				v.setCliente(new Cliente());
				v.getCliente().setId(rs.getInt("idCliente"));
				v.setImporte(rs.getFloat("importe"));
				
				ventas.add(v);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				DataConnection.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
		for(Venta v : ventas)
		{
			v.setCliente(CatalogoClientes.buscarCliente(v.getCliente().getId()));
			v.setProductos(CatalogoProductos.buscarProductosVenta(v.getCliente().getId()));
		}
		return ventas;
		
	}
}
