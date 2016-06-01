<%@page import="negocio.ControladorABM"%>
<select id="cbTipo">
	<optgroup label="Tipo">
		<option value="R">Ropa</option>
		<option value="M">Marroquineria</option>
		<option value="B">Bijouterie</option>
		<option value="Z">Zapatos</option>
	</optgroup>
</select>
<select id="cbSubTipo" >
	<optgroup label="Subtipo">
		<option value="H">Hombre</option>
		<option value="M">Mujer</option>
		<option value="N">Niño</option>
	</optgroup>
</select>
<button id="btnRestaurar">x</button>
<input type="text" id="txtID" placeholder="ID" class="textInputs" disabled="disabled" value = <%=ControladorABM.obtenerIdCompleto('R', 'H')%>>
<input type="text" id="txtDescripcion" class="textInputs" placeholder="Descripción">
<input type="text" id="txtPrecio" class="textInputs" placeholder="Precio">
<button type="submit" id="btnAceptar" class="btn btn-primary">Guardar</button>
<button type="reset" id="btnCancelarCreate" class="btn btn-default">Cancelar</button>