<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dto.TagMetaDTO"%>
<%@ page import="enumeraciones.TipoTagsMetas"%>


<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<TagMetaDTO> tags = (ArrayList<TagMetaDTO>) request.getAttribute("tags");
%>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
	<div class="card">
		<div class="form-row m-1 my-2 mb-2">
			<div class="col-md-12">
				<input type="text" class="form-control mb-3" id="meta"
					placeholder="Contanos cual es tu meta">
				<div class="form-group row">
					<label for="accion" class="col-lg-5 my-2">¿De que tipo es
						tu meta?</label> <select class="custom-select mb-3 col-lg-7" id="accion">
						<option value="" disabled selected>Acción</option>
						<%
							int i = 0;
							for (TagMetaDTO t : tags) {
								if (t.getTipo() == TipoTagsMetas.Accion) {
						%>
						<option value=<%=++i%>><%=t.getNombre()%></option>
						<%
							}
							}
						%>
					</select>
				</div>
				<div class="form-group row">
					<label for="sujeto" class="col-lg-5 my-2">¿A que está
						asociada?</label> <select class="custom-select mb-3 col-lg-7" id="sujeto">
						<option value="" disabled selected>Sujeto</option>
						<%
							i = 0;
							for (TagMetaDTO t : tags) {
								if (t.getTipo() == TipoTagsMetas.Sujeto) {
						%>
						<option value=<%=++i%>><%=t.getNombre()%></option>
						<%
							}
							}
						%>
					</select>
				</div>
				<div class="form-group row"">
					<label for="nivel" class="col-lg-5 my-2">¿Como es tu
						relación con esta meta?</label> <select
						class="custom-select mb-3 col-lg-7" id="nivel">
						<option value="" disabled selected>Nivel</option>
						<%
							i = 0;
							for (TagMetaDTO t : tags) {
								if (t.getTipo() == TipoTagsMetas.Nivel) {
						%>
						<option value=<%=++i%>><%=t.getNombre()%></option>
						<%
							}
							}
						%>
					</select>
				</div>
			</div>
		</div>
		<div class="col-auto my-1" align="center">
			<h6 class="errores desaparecer" id="mensajeError">Todos los
				campos son obligatorios.</h6>
			<button onclick="listarAcciones()" class="btn btn-primary">Cargar
				Meta</button>
		</div>
	</div>
</body>

</html>