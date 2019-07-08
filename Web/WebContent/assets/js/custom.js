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
			$('[data-toggle="popover"]').popover();
		}
		else if (this.readyState == 4 && this.status == 401) {window.location.href = "/Web/login.jsp";}};
	    xmlhttp.open("POST", url, true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
	    xmlhttp.send(postInfo);
}
 
 function listarAcciones() {
	 $('#meta').removeClass("is-invalid");
	 $('#accion').removeClass("is-invalid");
	 $('#sujeto').removeClass("is-invalid");
	 $('#nivel').removeClass("is-invalid");
	 if ($('#meta').val() === "" || $('#accion option:selected').val() === "" || $('#sujeto option:selected').val() === "" || $('#nivel option:selected').val() === "") {
		 $("#mensajeError").removeClass("desaparecer");
		 if ($('#meta').val() === "") $('#meta').addClass("is-invalid");
		 if ($('#accion option:selected').val() === "") $('#accion').addClass("is-invalid");
		 if ($('#sujeto option:selected').val() === "") $('#sujeto').addClass("is-invalid");
		 if ($('#nivel option:selected').val() === "") $('#nivel').addClass("is-invalid");
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
	 if ((parseInt($("#sumaHoras").val()) > parseInt($("#horasPerfil").val()) && 
				confirm("Los procedimientos seleccionados superan tu disponibilidad horaria.\n¿Estás seguro que queres seguir con esta selección?")) || 
				(parseInt($("#sumaHoras").val()) <= parseInt($("#horasPerfil").val()))) {
			procs=[];
			//$("#procs input:checked").each(function() {procs.push($(this).attr('id'));});
			$(".row-selected").each(function() {procs.push($(this).attr('id'));});
			tags=[$("#accion").text(), $("#sujeto").text(), $("#nivel").text()];
			nombre=$("#desc").text();
			loadDiv('contenedor-principal','Servlet','action=crearMeta&nombre='+nombre+'&tags='+tags+'&procs='+procs, 'Mis metas');
	}
	/* 
	if ($("#procs input:checked").length == 0) {
		$("#mensajeError").removeClass("desaparecer");
	}
	else {
		$("#mensajeError").addClass("desaparecer");
		if ((parseInt($("#sumaHoras").val()) > parseInt($("#horasPerfil").val()) && 
				confirm("Los procedimientos seleccionados superan tu disponibilidad horaria.\n¿Estás seguro que queres seguir con esta selección?")) || 
				(parseInt($("#sumaHoras").val()) <= parseInt($("#horasPerfil").val()))) {
			procs=[];
			$("#procs input:checked").each(function() {procs.push($(this).attr('id'));});
			tags=[$("#accion").text(), $("#sujeto").text(), $("#nivel").text()];
			nombre=$("#desc").text();
			loadDiv('contenedor-principal','Servlet','action=crearMeta&nombre='+nombre+'&tags='+tags+'&procs='+procs, 'Mis metas');
		}
	}
	*/
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
		if ($("#crearMeta").length > 0) {
			crearMetas = "&crearMeta=true";
		}
		else {
			crearMetas = "";
		}
		loadDiv('contenedor-principal','Servlet','action=modificarPerfil&fechaNac='+$('#fechaNacimiento').val()+'&dispHoraria='+dispo+'&ubicacion='+$('#ubicacion option:selected').text()+crearMetas, 'Modificar perfil');
	}
}

function sumarHoras(checkbox, cant) {
	var horas = cant;
	if (! checkbox.prop("checked")) {
		horas = horas * -1;
	}
	var anterior = parseInt($("#sumaHoras").val());
	var perfil = parseInt($("#horasPerfil").val());
	$("#sumaHoras").val(horas + anterior);
	if ((horas+anterior) < perfil * 0.8) {
		$("#sumaHoras").addClass("input-sumadehoras-bajo");
		$("#sumaHoras").removeClass("input-sumadehoras-medio");
		$("#sumaHoras").removeClass("input-sumadehoras-alto");
	}
	else if ((horas+anterior) <= perfil) {
		$("#sumaHoras").removeClass("input-sumadehoras-bajo");
		$("#sumaHoras").addClass("input-sumadehoras-medio");
		$("#sumaHoras").removeClass("input-sumadehoras-alto");
	}
	else {
		$("#sumaHoras").removeClass("input-sumadehoras-bajo");
		$("#sumaHoras").removeClass("input-sumadehoras-medio");
		$("#sumaHoras").addClass("input-sumadehoras-alto");
	}
}

function sumarHoras2(row, cant) {
	row.toggleClass("row-selected");
	suma=0;
	$(".row-selected").each(function () {suma = suma + parseInt($(this).attr('duracion'));})
	$("#sumaHoras").val(suma);
	semaforoHoras(suma);
}

function semaforoHoras(suma) {
	var perfil = parseInt($("#horasPerfil").val());
	if ((suma) < perfil * 0.8) {
		$("#sumaHoras").addClass("input-sumadehoras-bajo");
		$("#sumaHoras").removeClass("input-sumadehoras-medio");
		$("#sumaHoras").removeClass("input-sumadehoras-alto");
	}
	else if ((suma) <= perfil) {
		$("#sumaHoras").removeClass("input-sumadehoras-bajo");
		$("#sumaHoras").addClass("input-sumadehoras-medio");
		$("#sumaHoras").removeClass("input-sumadehoras-alto");
	}
	else {
		$("#sumaHoras").removeClass("input-sumadehoras-bajo");
		$("#sumaHoras").removeClass("input-sumadehoras-medio");
		$("#sumaHoras").addClass("input-sumadehoras-alto");
	}	
}

function agregarProcedimientos(procsStr) {
	procsArr = procsStr.split(",");
	for (i = 0; i < procsArr.length; i++) {
		$("#"+procsArr[i]).addClass("row-selected");
	}
	suma=0;
	$(".row-selected").each(function () {suma = suma + parseInt($(this).attr('duracion'));})
	$("#sumaHoras").val(suma);
	semaforoHoras(suma);
}

function nuevoProc(metaId) {
	$("#urlProcMeta"+metaId).removeClass("is-invalid");
	$("#duracionProcMeta"+metaId).removeClass("is-invalid");
	error = false;
	nombre = $("#nombreProcMeta"+metaId).val();
	if (nombre == "") {
		error = true;
		$("#nombreProcMeta"+metaId).addClass("is-invalid");
	}
	else $("#nombreProcMeta"+metaId).remove("is-invalid");
	url = $("#urlProcMeta"+metaId).val();
	/*if (url == "") {
		error = true;
		$("#urlProcMeta"+metaId).addClass("is-invalid");
	}
	else $("#urlProcMeta"+metaId).remove("is-invalid");*/
	var duracion = $("#duracionProcMeta"+metaId).val();
	if (duracion < 1 || duracion > 250) {
		error = true;
		$("#duracionProcMeta"+metaId).addClass("is-invalid");
	}
	else $("#duracionProcMeta"+metaId).remove("is-invalid");
	if (! error) {
		$("#procModal"+metaId).modal("toggle");
		postStr = "action=crearProcedimiento&metaId="+metaId+"&nombre="+nombre+"&url="+url+"&duracion="+duracion;
		loadDiv('contenedor-principal','Servlet',postStr, 'Mis metas')
	}
}

function cerrarMeta(metaId) {
	var cantProcs = $("#cantProcsMeta"+metaId).val();
	idsProcs = "";
	caliProcs = "";
	error = false;
	for (i=1;i<=cantProcs;i++) {
		idsProcs = idsProcs + "&idProcs=" + $("#proc"+i+"Meta"+metaId).val();
		calProc = $("#calMeta"+metaId+"Proc"+i).val();
		if (calProc == "" || parseInt(calProc) < 1 || parseInt(calProc) > 10) {
			error = true;
			$("#calMeta"+metaId+"Proc"+i).addClass("is-invalid");
		}
		else $("#calMeta"+metaId+"Proc"+i).removeClass("is-invalid");
		caliProcs = caliProcs + "&caliProcs=" + $("#calMeta"+metaId+"Proc"+i).val();
	}
	if (error) $("#mensajeError"+metaId).removeClass("desaparecer");
	else {
		$("#metaModal"+metaId).modal("toggle");
		postStr = "action=cerrarMeta&metaId=" + metaId + idsProcs + caliProcs;
		loadDiv("contenedor-principal","Servlet",postStr,"Mis metas");
	}
}
