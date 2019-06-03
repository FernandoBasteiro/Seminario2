 function loadDiv(divName, url, postInfo, titulo) {
	 	var w = window.innerWidth;
		if (w < 768) {
			document.getElementById("accordionSidebar").className += " toggled";
			document.getElementById("page-top").className += " sidebar-toggled";
			document.getElementById("collapse1").className = "collapse";
		}
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById(divName).innerHTML = this.responseText;
			document.getElementById("titulo-pagina").innerHTML  = titulo;
			document.getElementById("titulo-pagina-chica").innerHTML  = titulo;
			}
		else if (this.readyState == 4 && this.status == 401) {window.location.href = "/Web/login.jsp";}};
	    xmlhttp.open("POST", url, true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
	    xmlhttp.send(postInfo);
}
 
 function listarAcciones() {
	 if ($('#meta').val() === "" || $('#accion option:selected').val() === "" || $('#sujeto option:selected').val() === "" || $('#nivel option:selected').val() === "") {
		 $("#mensajeError").removeClass("desaparecer");
	 }
	 else {
		nombre = $('#meta').val()
		accion = $('#accion option:selected').text()
		sujeto = $('#sujeto option:selected').text()
		nivel = $('#nivel option:selected').text()
		loadDiv('contenedor-principal','Servlet','action=listarAcciones&nombre='+nombre+'&accion='+accion+'&sujeto='+sujeto+'&nivel='+nivel, 'Seleccionar acciones');			 
	 }

}
 
 function crearMeta() {
	if ($("#procs input:checked").length == 0) {
		$("#mensajeError").removeClass("desaparecer");
	}
	else {
		procs=[];
		$("#procs input:checked").each(function() {procs.push($(this).attr('id'));});
		tags=[$("#accion").text(), $("#sujeto").text(), $("#nivel").text()];
		nombre=$("#desc").text();
		loadDiv('contenedor-principal','Servlet','action=crearMeta&nombre='+nombre+'&tags='+tags+'&procs='+procs, 'Mis metas');			
	}
}
 
function cargarPerfil() {
	ahora = new Date();
	minimo = Date.parse(new Date(ahora.getFullYear() - 100, ahora.getMonth(), ahora.getDate(), 0, 0, 0, 0));
	maximo = Date.parse(new Date(ahora.getFullYear() - 18, ahora.getMonth(), ahora.getDate(), 0, 0, 0, 0));
	fechaNac = Date.parse($("#fechaNacimiento").val());
	error = false;
	if (fechaNac < minimo || fechaNac > maximo) {
		$("#errorEdad").removeClass("desaparecer");
		$("#confirmacion").addClass("desaparecer");
		$("#fechaNacimiento").addClass("is-invalid");
		error = true;
	}
	else {
		$("#fechaNacimiento").removeClass("is-invalid");
		$("#errorEdad").addClass("desaparecer");
	}
	dispo = $("#disponibilidad").val()
	if (dispo < 1 || dispo > 250) {
		$("#errorDispo").removeClass("desaparecer");
		$("#disponibilidad").addClass("is-invalid");
		$("#confirmacion").addClass("desaparecer");
		error = true;
	}
	else {
		$("#disponibilidad").removeClass("is-invalid");
		$("#errorDispo").addClass("desaparecer");
	}
	if ($("#ubicacion option:selected").val() === "") {
		$("#errorUbi").removeClass("desaparecer");
		$("#ubicacion").addClass("is-invalid");
		$("#confirmacion").addClass("desaparecer");
		error = true;
	}
	else {
		$("#errorUbi").addClass("desaparecer");
		$("#ubicacion").removeClass("is-invalid");
	}
	if (! error) {
		loadDiv('contenedor-principal','Servlet','action=modificarPerfil&fechaNac='+$('#fechaNacimiento').val()+'&dispHoraria='+dispo+'&ubicacion='+$('#ubicacion option:selected').text(), 'Modificar perfil');
	}
}