<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.RecomendacionesDTO"%>
<%@ page import="dto.MetasDTO"%>
<%@ page import="dto.ProcedimientoDTO"%>
<%@ page import="dto.UsuarioDTO"%>
<%@ page import="java.text.DecimalFormat"%>

<%
RecomendacionesDTO rec = (RecomendacionesDTO) request.getAttribute("procs");
MetasDTO meta = (MetasDTO) request.getAttribute("meta");
UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("loggedUsr");
DecimalFormat decimalFormat = new DecimalFormat("0.00");
String claseSuma = "input-sumadehoras-bajo";
if (rec.getSumaPromoProcs() > usuario.getVarDispHoraria()) claseSuma = "input-sumadehoras-alto";
else if (rec.getSumaPromoProcs() > usuario.getVarDispHoraria() * 0.8) claseSuma = "input-sumadehoras-medio";
%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
	<div class="card shadow">
		<div
			class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
			<h6 class="m-0 font-weight-bold text-primary">
				<span id="desc"><%=meta.getDescripcion() %></span></br> <span
					class="badge badge-secondary" id="accion"><%=meta.getVarAccion() %></span>
				<span class="badge badge-secondary" id="sujeto"><%=meta.getVarSujeto() %></span>
				<span class="badge badge-secondary" id="nivel"><%=meta.getVarNivel() %></span>
			</h6>
			<a href="#" class="small my-1 mr-2" data-toggle="modal"
				data-target="#metasModal">¿Qué hicieron otras personas?</a>
			<div class="row mr-2">
				<h6 class="m-0 font-weight-bold text-primary my-1 mr-2">Horas
					totales:</h6>
				<input type="text" disabled id="sumaHoras"
					class="input-sumadehoras <%=claseSuma %>"
					value=<%=rec.getSumaPromoProcs() %>> <input type="hidden"
					id="horasPerfil" value=<%=usuario.getVarDispHoraria() %>>
			</div>
		</div>
		<div class="card-body ml-2" id="procs">
			<table class="table table-hover">
				<%
					if (rec.getNormalProcs().size() == 0) {
				%>
				<tr colspan=3>
					<td>Parece que nadie intento hacer esto antes. Crea tu meta vacia y cargale tus experiencias para ayudar al proximo que lo intente.</td>
				</tr>
				<%
					} else {
				%>
				<tr>
					<!--<th></th> -->
					<th>Descripción</th>
					<th>Duración</th>
					<th>Calificación</th>
				</tr>
				<% } %>
				<% for (ProcedimientoDTO p : rec.getPromoProcs()) { %>
				<tr onClick="sumarHoras2($(this), <%=p.getDuracion() %>)"
					id=<%=p.getId() %> class="row-selected"
					duracion=<%=p.getDuracion() %> data-toggle="popover" data-placement="top" data-trigger="hover" data-content="<%=p.getUrl()%>">
					
					<!-- <tr> onclick="$('#<%=p.getId() %>').prop('checked', !$('#<%=p.getId() %>').prop('checked'))">  -->
					<!-- <td><input type="checkbox" value="" id=<%=p.getId() %> onClick="sumarHoras($(this), <%=p.getDuracion() %>)"></td> -->
					<td><%=p.getDescripcion() %></td>
					<td><%=p.getDuracion() %> hora<% if (p.getDuracion() != 1) out.print("s"); %></td>
					<td><%=decimalFormat.format(p.getCalificacion()) %></td>
				</tr>
				<% } %>
				<% for (ProcedimientoDTO p : rec.getNormalProcs()) { %>
				<tr onClick="sumarHoras2($(this), <%=p.getDuracion() %>)"
					id=<%=p.getId() %> duracion=<%=p.getDuracion() %> data-toggle="popover" data-placement="top" data-trigger="hover" data-content="<%=p.getUrl()%>">
					<!-- <tr> onclick="$('#<%=p.getId() %>').prop('checked', !$('#<%=p.getId() %>').prop('checked'))">  -->
					<!-- <td><input type="checkbox" value="" id=<%=p.getId() %> onClick="sumarHoras($(this), <%=p.getDuracion() %>)"></td> -->
					<td><%=p.getDescripcion()%></td>
					<td><%=p.getDuracion()%> hora<%
						if (p.getDuracion() != 1)
								out.print("s");
					%></td>
					<td><%=decimalFormat.format(p.getCalificacion())%></td>
				</tr>
				<%
					}
				%>
			</table>
			<div class="col-auto my-1" align="center">
				<h6 class="errores desaparecer" id="mensajeError">Tenés que
					seleccionar al menos uno de los procedimientos propuestos.</h6>
				<button onclick="crearMeta()" class="btn btn-primary">Crear
					meta</button>
			</div>
		</div>
	</div>
	<div class="modal fade" id="metasModal" tabindex="-1" role="dialog"
		aria-labelledby="tituloModal" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="tituloModal">¿Qué hicieron otras
						personas?</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<%
						for (MetasDTO m : rec.getMetas()) {
					%>
					<div class="card my-1">
						<div class="card-header" data-toggle="collapse"
							data-target="#collapse<%=m.getId()%>"
							aria-controls="collapse<%=m.getId()%>">
							<%=m.getUser()%>
							completo su meta:
							<%=m.getDescripcion()%>
						</div>
						<div class="card-body collapse multi-collapse"
							id="collapse<%=m.getId()%>">
							<%
								String procsStr = "";
									for (ProcedimientoDTO p : m.getProcedimientos()) {
										if (procsStr != "")
											procsStr = procsStr + ",";
										procsStr = procsStr + p.getId();
							%>
							<p class="card-text"><%=p.getDescripcion()%></p>
							<%
								}
							%>
							<a href="#" onClick="agregarProcedimientos('<%=procsStr%>')"
								class="btn btn-primary">Agregar estos procedimientos a mi meta</a>
						</div>
					</div>
					<%
						}
					%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>