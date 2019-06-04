<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.UsuarioDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.MetasDTO"%>
<%@ page import="dto.ProcedimientoDTO"%>
<html>
<head>
<meta charset="utf-8">
</head> 
<body>	<% ArrayList<MetasDTO> metas = (ArrayList<MetasDTO>) request.getAttribute("metas");
		if (metas.size() == 0) { %>
	<div class="card shadow mb-4">
		<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
			<h6 class="m-0 font-weight-bold text-primary">No tenes ninguna meta pendiente</h6>
		</div>
	</div>
	<% } %>
		<% for (MetasDTO meta : metas) { %>
	<!-- Dropdown Card Example -->
	<div class="card shadow mb-4">
		<!-- Card Header - Dropdown -->
		<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
			<h6 class="m-0 font-weight-bold text-primary"><%=meta.getDescripcion() %></br>
			<span class="badge badge-primary"><%=meta.getVarAccion() %></span>
			<span class="badge badge-primary"><%=meta.getVarSujeto() %></span>
			<span class="badge badge-primary"><%=meta.getVarNivel() %></span></h6>
			<div class="dropdown no-arrow">
				<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"	aria-expanded="false">
					<i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
				</a>
				<div class="dropdown-menu dropdown-menu-right shadow animated--fade-in dropdown-primary" aria-labelledby="dropdownMenuLink">
					<div class="dropdown-header">Opciones:</div>
					<a class="dropdown-item" href="#">Cargar nuevo procedimiento</a> 
					<a class="dropdown-item" href="#">Meta finalizada</a>
				</div>
			</div>
		</div>
		<!-- Card Body -->
		<div class="card-body">
			<% for (ProcedimientoDTO p : meta.getProcedimientos()) { %>
			<a class="list-group-item list-group-item-action"><%=p.getDescripcion() %> (<%=p.getDuracion() %> hora<% if(p.getDuracion() != 1) out.print("s"); %>)</a>
			<% } %>
    	</div>
	</div>		
		<% } %>
</body>
</html>