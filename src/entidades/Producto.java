package entidades;

public class Producto {
private String codProducto, descripcion;
private int estado;

public static enum estado{
	VENDIDO,
	STOCK,
	SEÑADO
}

public String getCodProducto() {
	return codProducto;
}
public void setCodProducto(String codigo) {
	this.codProducto = codigo;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public int getEstado() {
	return estado;
}
public void setEstado(int estado) {
	this.estado = estado;
}
}
