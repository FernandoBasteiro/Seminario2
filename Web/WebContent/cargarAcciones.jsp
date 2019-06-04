<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.MetasDTO"%>
<%@ page import="dto.ProcedimientoDTO"%>
<%@ page import="dto.UsuarioDTO"%>

<%
ArrayList<ProcedimientoDTO> procs = (ArrayList<ProcedimientoDTO>) request.getAttribute("procs");
MetasDTO meta = (MetasDTO) request.getAttribute("meta");
UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("loggedUsr");
%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
	<div class="card shadow">
		<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
			<h6 class="m-0 font-weight-bold text-primary"><span id="desc"><%=meta.getDescripcion() %></span></br>
			<span class="badge badge-primary" id="accion"><%=meta.getVarAccion() %></span>
			<span class="badge badge-primary" id="sujeto"><%=meta.getVarSujeto() %></span>
			<span class="badge badge-primary" id="nivel"><%=meta.getVarNivel() %></span></h6>
			<div class="row mr-2">
				<h6 class="m-0 font-weight-bold text-primary my-1 mr-2">Horas totales:</h6>
				<input type="text" disabled id="sumaHoras" class="input-sumadehoras input-sumadehoras-bajo" value=0>
				<input type="hidden" id="horasPerfil" value=<%=usuario.getVarDispHoraria() %>>
			</div>
		</div>
		<div class="card-body ml-2" id="procs">
			<table class="table table-hover">
				<tr>
					<th></th><th>Descripción</th><th>Duración</th><th>Calificación</th>
				</tr>
				<% for (ProcedimientoDTO p : procs) { %>
				<tr onclick="$('#<%=p.getId() %>').prop('checked', !$('#<%=p.getId() %>').prop('checked'))">
					<td><input type="checkbox" value="" id=<%=p.getId() %> onClick="sumarHoras($(this), <%=p.getDuracion() %>)"></td>
					<td><%=p.getDescripcion() %></td>
					<td><%=p.getDuracion() %> hora<% if (p.getDuracion() != 1) out.print("s"); %></td>
					<td><%=p.getCalificacion() %></td>
				</tr>
				<% } %>
			</table>
			<div class="col-auto my-1" align="center">
				<h6 class="errores desaparecer" id="mensajeError">Tenés que seleccionar al menos uno de los procedimientos propuestos.</h6>
				<button
					onclick="crearMeta()"
					class="btn btn-primary">Cargar acciones</button>
			</div>
		</div>
	</div>
</body>
</html>