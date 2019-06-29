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
		<div class="card-body m-1 my-2 mb-0">
			<div class="col-md-12">
				<div class="form-group row">
					<label for="accion" class="col-lg-5 my-2">Contanos cual es tu meta:</label>
					<input type="text" class="form-control col-lg-7" id="meta">
				</div>
				<div class="form-group row">
					<label for="accion" class="col-lg-5 my-2">¿De que tipo es
						tu meta?</label> <select class="custom-select col-lg-7" id="accion">
						<option value="" disabled selected></option>
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
						asociada?</label> <select class="custom-select col-lg-7" id="sujeto">
						<option value="" disabled selected></option>
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
				<div class="form-group row mb-0">
					<label for="nivel" class="col-lg-5 my-2">¿Como es tu
						relación con esta meta?</label> <select
						class="custom-select col-lg-7" id="nivel">
						<option value="" disabled selected></option>
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
		<div class="col-auto mb-4" align="center">
			<h6 class="errores desaparecer" id="mensajeError">Todos los
				campos son obligatorios.</h6>
			<button onclick="listarAcciones()" class="btn btn-primary">Obtener recomendaciones</button>
		</div>
	</div>
</body>

</html>