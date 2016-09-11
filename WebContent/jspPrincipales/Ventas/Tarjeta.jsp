<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="entidades.TipoTarjeta"
    import="negocio.ControladorABM"%>
    
<div class="form-group">
	<label>Número de Tarjeta: </label>
	<input id="txtNroTarjetaTrj" class="form-control">
</div>
<div class="form-group">
	<label>Nombre : </label>
	<input id="txtNombreTrj" class="form-control">
</div>
<div class="form-group">
	<label>Apellido: </label>
	<input id="txtApellidoTrj" class="form-control">
</div>
<div class="form-group">
	<label>Nro de Cuotas: </label>
	<input id="txtCuotasTrj" class="form-control">
</div>
<div class="form-group">
	<label>Numero de Cupón: </label>
	<input id="txtCuponTrj" class="form-control">
</div>
<div class="form-group">
	<label>Tipo de Tarjeta: </label>
	<select id="cbTipoTarjetaTrj" class="form-control">
							<%for(TipoTarjeta tp : ControladorABM.getTipoTarjetas()) 
							{%>
								<option value="<%=tp.getId()%>"><%=tp.getDescripcion()%></option>
							<%}%>
	</select>
</div>
