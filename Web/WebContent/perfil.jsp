<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dto.UsuarioDTO"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>

<%
ArrayList<String> provincias = new ArrayList<String>();
provincias.add("Buenos Aires");
provincias.add("CABA");
provincias.add("Catamarca");
provincias.add("Chaco");
provincias.add("Chubut");
provincias.add("C�rdoba");
provincias.add("Corrientes");
provincias.add("Entre R�os");
provincias.add("Formosa");
provincias.add("Jujuy");
provincias.add("La Pampa");
provincias.add("La Rioja");
provincias.add("Mendoza");
provincias.add("Misiones");
provincias.add("Neuqu�n");
provincias.add("R�o Negro");
provincias.add("Salta");
provincias.add("San Juan");
provincias.add("Santa Cruz");
provincias.add("Santa Fe");
provincias.add("Santiago del Estero");
provincias.add("Tierra del Fuego");
provincias.add("Tucum�n");

%>


<% UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("perfil"); %>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Perfil de <%=usuario.getNombre() %></h6>
		</div>
		<div class="card-body">
			<div class="col-md-6 mb-3" align="center">
				<label for="fechaNacimiento">Fecha de nacimiento</label>
				<input type='date' class="form-control mb-3" id="fechaNacimiento" value="<% if (usuario.getVarFechaNac() != null) {out.print(usuario.getVarFechaNac().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));} %>"/>

				<label for="disponibilidad">Disponibilidad (Horas)</label> <input
					type="number" class="form-control mb-3" id="disponibilidad" min=1 max=250 value=<%=usuario.getVarDispHoraria() %>>
				<label for="ubicacion">Ubicaci�n</label> <select class="custom-select mb-3"
					id="ubicacion" data-live-search="true" value=<%=usuario.getVarUbicacion() %>>
					<option value="" disabled <% if (usuario.getVarUbicacion() == null) {out.print("selected");} %>>Ubicaci�n</option>
					<% for (int i = 0; i < provincias.size(); i++) { %>
					<option value=<%=i %> <% if (usuario.getVarUbicacion().equals(provincias.get(i))) {out.print("selected");} %>><%=provincias.get(i) %></option>
					<% } %>
				</select>
				<button
				onclick="loadDiv('contenedor-principal','Servlet','action=modificarPerfil&fechaNac='+$('#fechaNacimiento').val()+'&dispHoraria='+$('#disponibilidad').val()+'&ubicacion='+$('#ubicacion option:selected').text(), 'Modificar perfil')"
				class="btn btn-primary">Actualizar Perfil</button>
			</div>
		</div>
	</div>
</body>
</html>