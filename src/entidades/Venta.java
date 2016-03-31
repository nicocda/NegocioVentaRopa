package entidades;

import java.util.ArrayList;
import java.util.Date;

public class Venta {
private int nroVenta, formaPago;
private float total;
private Date fechaVenta;
private Cliente cliente;
private ArrayList<Producto> productos;

public static enum formaPago{
	NOSESUSA,
	EFECTIVO,
	CTACTE,
	TARJETA
}

public int getNroVenta() {
	return nroVenta;
}

public void setNroVenta(int nroVenta) {
	this.nroVenta = nroVenta;
}

public int getFormaPago() {
	return formaPago;
}

public void setFormaPago(int formaPago) {
	this.formaPago = formaPago;
}

public float getTotal() {
	return total;
}

public void setTotal(float total) {
	this.total = total;
}

public Date getFechaVenta() {
	return fechaVenta;
}

public void setFechaVenta(Date fechaVenta) {
	this.fechaVenta = fechaVenta;
}

public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

public ArrayList<Producto> getProductos() {
	return productos;
}

public void setProductos(ArrayList<Producto> productos) {
	this.productos = productos;
}



}
