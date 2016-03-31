package entidades;

import java.util.Date;

public class Reserva {
private Date fechaReserva;
private int monto;
private Cliente cliente;
private Producto producto;

public Date getFechaReserva() {
	return fechaReserva;
}
public void setFechaReserva(Date fechaReserva) {
	this.fechaReserva = fechaReserva;
}
public int getMonto() {
	return monto;
}
public void setMonto(int monto) {
	this.monto = monto;
}
public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
public Producto getProducto() {
	return producto;
}
public void setProducto(Producto producto) {
	this.producto = producto;
}



}
