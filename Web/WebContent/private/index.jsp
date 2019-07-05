<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dto.MetasUsrDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.time.format.DateTimeFormatter"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shhh</title>
<link href="assets/css/far.css" rel="stylesheet">
<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
</head>

<%
	if (request.getAttribute("ListaMetasDTO") == null) {
		response.sendRedirect("../Servlet?action=todasLasMetas");
	}
	else {
		ArrayList<MetasUsrDTO> metas = (ArrayList<MetasUsrDTO>)request.getAttribute("ListaMetasDTO");
%>
<body>
	<div class="panel panel-default col-lg">
		<div class="panel-heading"></div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>
							<th>Descripción</th>
							<th>Sujeto</th>
							<th>Acción</th>
							<th>Nivel</th>
							<th>Fecha Nac</th>
							<th>Cant Procs</th>
						</tr>
					</thead>
					<tbody>
						<% for (MetasUsrDTO m : metas) { %>
						<tr class="odd gradeX">
							<td><%=m.getMeta().getDescripcion() %></td>
							<td><%=m.getMeta().getVarSujeto() %></td>
							<td><%=m.getMeta().getVarAccion() %></td>
							<td><%=m.getMeta().getVarNivel() %></td>
							<td><%=m.getUsuario().getVarFechaNac().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) %></td>
							<td><%=m.getMeta().getProcedimientos().size() %></td>
						</tr>
						<% } %>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script>
$(document).ready(function() {
    $('#dataTable').DataTable();
} );
</script>
<% } %>
</html>