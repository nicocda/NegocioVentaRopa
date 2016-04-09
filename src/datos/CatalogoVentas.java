package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.naming.java.javaURLContextFactory;

import conexion.DataConnection;
import entidades.Cliente;
import entidades.Venta;

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
	
	public static void registrarVenta(Venta vta)
	{
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
			int rowsAfectadas = sentencia.executeUpdate();
			 if (rowsAfectadas == 0) 
				 throw new SQLException("Creating user failed, no rows affected.");
	        try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) 
	        {
	            if (generatedKeys.next()) 
	            {
	                idVenta = generatedKeys.getInt("id");
	            }
	            else 
	            {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
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
			//Deberíamos hacer un stored procedure con StartTransaction.
			CatalogoProductos.venderProducto(idVenta, vta.getProductos());
		}
		
		
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
}
