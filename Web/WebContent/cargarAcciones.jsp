<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.MetasDTO"%>
<%@ page import="dto.ProcedimientoDTO"%>

<%
ArrayList<ProcedimientoDTO> procs = (ArrayList<ProcedimientoDTO>) request.getAttribute("procs");
MetasDTO meta = (MetasDTO) request.getAttribute("meta");
%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
	<div class="card shadow">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary"><span id="desc"><%=meta.getDescripcion() %></span></br>
			<span class="badge badge-primary" id="accion"><%=meta.getVarAccion() %></span>
			<span class="badge badge-primary" id="sujeto"><%=meta.getVarSujeto() %></span>
			<span class="badge badge-primary" id="nivel"><%=meta.getVarNivel() %></span></h6>
		</div>
		<div class="card-body ml-2" id="procs">
			<% for (ProcedimientoDTO p : procs) { %>
			<div class="form-check">
				<input class="form-check-input" type="checkbox" value=""
					id=<%=p.getId() %>> <label class="form-check-label"
					for=<%=p.getId() %>> <%=p.getDescripcion() %> (<%=p.getDuracion() %> hora<% if(p.getDuracion() != 1) out.print("s"); %>) </label>
			</div>
			<% } %>
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