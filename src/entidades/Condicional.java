package entidades;

import java.util.ArrayList;
import java.util.Date;

public class Condicional {
private Cliente cliente;
private ArrayList<Producto> productos;
private Date fecha;
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
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}


}
