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
provincias.add("Córdoba");
provincias.add("Corrientes");
provincias.add("Entre Ríos");
provincias.add("Formosa");
provincias.add("Jujuy");
provincias.add("La Pampa");
provincias.add("La Rioja");
provincias.add("Mendoza");
provincias.add("Misiones");
provincias.add("Neuquén");
provincias.add("Río Negro");
provincias.add("Salta");
provincias.add("San Juan");
provincias.add("Santa Cruz");
provincias.add("Santa Fe");
provincias.add("Santiago del Estero");
provincias.add("Tierra del Fuego");
provincias.add("Tucumán");

%>


<% 
UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("loggedUsr");
String error = (String) request.getAttribute("error");
%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Perfil de <%=usuario.getNombre() %></h6>
		</div>
		<div class="card-body" align="center">
			<div class="col-md-12">
				<% if (error != null && error.equals("faltaPerfil"))  {%>
				<h6 class="errores" id="faltaPerfil">Para crear una meta, primero tenes que definir tu perfil.</h6>
				<input type="hidden" id="crearMeta" value="true">
				<% } %>
				<label for="fechaNacimiento">Fecha de nacimiento</label>
				<input type='date' class="form-control mb-3" id="fechaNacimiento" value="<% if (usuario.getVarFechaNac() != null) {out.print(usuario.getVarFechaNac().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));} %>"/>
				<h6 class="errores desaparecer" id="errorEdad">La edad debe estar entre 18 y 100 años</h6>
				<label for="disponibilidad">Disponibilidad (Horas)</label> <input
					type="number" class="form-control mb-3" id="disponibilidad" min=1 max=250 value=<%=usuario.getVarDispHoraria() %>>
				<h6 class="errores desaparecer" id="errorDispo">La disponibilidad debe estar entre 1 y 250 horas.</h6>
				<label for="ubicacion">Ubicación</label> <select class="custom-select mb-3"
					id="ubicacion" data-live-search="true" value=<% if (usuario.getVarUbicacion() != null) out.print(usuario.getVarUbicacion()); %>>
					<option value="" disabled <% if (usuario.getVarUbicacion() == null) {out.print("selected");} %>>Ubicación</option>
					<% for (int i = 0; i < provincias.size(); i++) { %>
					<option value=<%=i %> <% if (usuario.getVarUbicacion() != null && usuario.getVarUbicacion().equals(provincias.get(i))) {out.print("selected");} %>><%=provincias.get(i) %></option>
					<% } %>
				</select>
				<h6 class="errores desaparecer" id="errorUbi">Se debe seleccionar una ubicación de la lista.</h6>
				<button
				onclick="cargarPerfil()"
				class="btn btn-primary">Actualizar Perfil</button>
				<% if (request.getAttribute("success") != null) { %>
				<h6 class="confirmacion" id="confirmacion">El perfil se actualizo correctamente.</h6>
				<% } %>
				
			</div>
		</div>
	</div>
</body>
</html>