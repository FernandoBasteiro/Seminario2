<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dto.UsuarioDTO"%>

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
			<div class="col-md-6 mb-3">
				<label for="fechaNacimiento">Fecha de nacimiento</label>
				<div class='input-group date' id='datetimepicker'>
					<input type='text' class="form-control" id="fechaNacimiento" value="<%=usuario.getVarFechaNac().toString() %>>"/> 
					<span class="input-group-addon">
                    	<span class="fas fa-calendar-alt mt-1"></span>
					</span>
				</div>
			</div>
			<div class="col-md-6 mb-3">
				<label for="disponibilidad">Disponibilidad (Horas)</label> <input
					type="number" class="form-control" id="disponibilidad" min=18 max=100 value=<%=usuario.getVarDispHoraria() %>>
			</div>
			<div class="col-md-6 mb-3">
				<label for="ubicacion">Ubicación</label> <select class="custom-select"
					id="ubicacion" data-live-search="true" value=<%=usuario.getVarUbicacion() %>>
					<option value="" disabled selected>Ubicación</option>
					<option value="1">CABA</option>
					<option value="2">Buenos Aires</option>
					<option value="3">Tierra del Fuego</option>
					<option value="4">Jujuy</option>
				</select>
			</div>
		</div>
	</div>
</body>
</html>