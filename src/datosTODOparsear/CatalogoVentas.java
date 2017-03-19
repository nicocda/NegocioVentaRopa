package datosTODOparsear;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexion.Conexion;
import constantes.EstadoProducto;
import entidades.Producto;
import entidades.Venta;
import excepciones.RespuestaServidor;

public class CatalogoVentas extends CatalogoBase
{
	//region Públicos
	public static void registrarVenta(Venta venta) throws RespuestaServidor
	{
		RespuestaServidor rs = validarVenta(venta);
		
		if (!rs.getStatus())
			throw rs;
		
		try
		{
			nuevaCon().setAutoCommit(false);
			guardarVenta(venta, rs);
			Conexion.getInstancia().commit();
		}
		catch (SQLException e)
		{
			try
			{
				nuevaCon().rollback();
			}
			catch (SQLException ex)
			{
				rs.addError(ex);
				throw rs;
			}
			
			rs.addError(e);
			throw rs;
		}
		finally 
		{
			Conexion.getInstancia().CloseConn();
		}
	}
	
	public static void guardarVenta(Venta venta, RespuestaServidor sr) throws RespuestaServidor
	{
		PreparedStatement sentencia = null;
		
		String sql = "INSERT INTO venta (fechaVenta, seña, tipoVenta, fechaCaducidad, formaPago, importe, idCliente) is VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			sentencia = prepareStatement(sql);
			
			sentencia.setTimestamp(1, venta.getFechaVenta());
			sentencia.setFloat(2, venta.getSeña());
			sentencia.setInt(3, venta.getTipoVenta());
			sentencia.setTimestamp(4, venta.getFechaCaducidad());
			sentencia.setInt(5, venta.getFormaPago());
			sentencia.setFloat(6, venta.getImporte()); // <- esto no es calculable?
			sentencia.setInt(7, venta.getCliente().getId());
			
			for (Producto p : venta.getProductos())
			{
				p.setEstado(EstadoProducto.VENDIDO);
				CatalogoProductos.guardarProducto(p);
			}
			
			sentencia.executeUpdate();
		}
		catch (SQLException e) 
		{
			sr.addError(e);
			throw sr;
		}
		finally
		{
			cerrarStatement(sentencia);
		}
	}
	//endregion
	
	//region Privados
	private static RespuestaServidor validarVenta (Venta venta)
	{
		RespuestaServidor rs = new RespuestaServidor();
		
		return rs;
	}
	//endregion
}
