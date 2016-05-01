package util;

public class UtilidadesWeb {

	public static String pasajeAStringValido(String fecha){
		
		System.out.println(fecha);
		String dia = fecha.substring(3, 5);
		String mes = fecha.substring(0, 2);
		String anio = fecha.substring(6, 10);
		String fechaNueva = anio+"-"+mes+"-"+dia;
		return fechaNueva;
	}
	
	
}
