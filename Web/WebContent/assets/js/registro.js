function passwordsMatch() {
	if ($("#inPassword").val() === "" || $("#inPassword2").val() === "") {
			$("#inPassword2").removeClass("is-invalid");
			$("#inPassword2").removeClass("is-valid");
	}
	else{
		if ($("#inPassword").val() === $("#inPassword2").val()) {
			$("#inPassword2").removeClass("is-invalid");
			$("#inPassword2").addClass("is-valid");
			return true;
		}
		else {
			$("#inPassword2").addClass("is-invalid");
			$("#inPassword2").removeClass("is-valid");
			return false;
		}
	}
	
}

var existeUsuario = function() {
	if ($("#inUsuario").val() === "") {
		$("#loading").addClass("desaparecer");
		$("#existe").addClass("desaparecer");
		$("#inUsuario").addClass("is-invalid")
		$("#inUsuario").removeClass("is-valid")
		$("#noexiste").addClass("desaparecer");
		return false;
	}
	loading();
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText === "true") {
				existe();
				return false;
			}
			else {
				noExiste();
				return true;
			}
		}
		else if (this.readyState == 4) {
			//TODO hacer desaparecer el loading y mostrar error
			return false;
		}};
	xmlhttp.open("POST", "RespuestaCorta", true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
	xmlhttp.send("usuario="+$("#inUsuario").val()+"&action=existeUsuario");
}

function submitirFormulario() {
	var correcto = true;
	
	if ($("#inNombre").val() != "") {
		$("#inNombre").addClass("is-valid");
		$("#inNombre").removeClass("is-invalid");
	}
	else {
		$("#inNombre").removeClass("is-valid");
		$("#inNombre").addClass("is-invalid");
		correcto = false;
	}
	if ($("#inApellido").val() != "") {
		$("#inApellido").addClass("is-valid");
		$("#inApellido").removeClass("is-invalid");
	}
	else {
		$("#inApellido").removeClass("is-valid");
		$("#inApellido").addClass("is-invalid");
		correcto = false;
	}
	if ($("#inPassword").val() != "") {
		$("#inPassword").addClass("is-valid");
		$("#inPassword").removeClass("is-invalid");
	}
	else {
		$("#inPassword").removeClass("is-valid");
		$("#inPassword").addClass("is-invalid");
		correcto = false;
	}
	if (! passwordsMatch()) {
		correcto = false;
	}
	if (existeUsuario()) {
		correcto = false;
	}
	
	if (correcto) {
		usuario = [];
		usuario.push($("#inNombre").val());
		usuario.push($("#inApellido").val());
		usuario.push($("#inUsuario").val());
		usuario.push($("#inPassword").val());
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				window.location.href = "/Web/login.jsp";
			}
		};
		xmlhttp.open("POST", "Servlet?action=crearUsuario", true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
	    xmlhttp.send("usuario="+usuario);
	}
}

function loading() {
	$("#usrDiv").addClass("mb-0");
	$("#loading").removeClass("desaparecer");
	$("#existe").addClass("desaparecer");
	$("#inUsuario").removeClass("is-invalid")
	$("#inUsuario").removeClass("is-valid")
	$("#noexiste").addClass("desaparecer");
};
function existe() {
	$("#loading").addClass("desaparecer");
	$("#existe").removeClass("desaparecer");
	$("#inUsuario").addClass("is-invalid")
	$("#inUsuario").removeClass("is-valid")
	$("#noexiste").addClass("desaparecer");
}
function noExiste() {
	$("#loading").addClass("desaparecer");
	$("#existe").addClass("desaparecer");
	$("#inUsuario").removeClass("is-invalid")
	$("#inUsuario").addClass("is-valid")
	$("#noexiste").removeClass("desaparecer");
}

function debounce(func, wait, inmediate) {
	var timeout;
	return function() {
		var context = this, args = arguments;
		var later = function() {
			timeout = null;
			if (!inmediate) func.apply(context, args);
		};
		var callNow = inmediate && !timeout;
		clearTimeout(timeout);
		timeout = setTimeout(later, wait);
		if (callNow) func.apply(context, args);
	};
};

$(document).ready(function(){
	$('#inUsuario').keyup(debounce(existeUsuario, 750));
	$('#inNombre').change(function(){$('#inNombre').removeClass("is-invalid")});
	$('#inApellido').change(function(){$('#inApellido').removeClass("is-invalid")});
	$('#inApellido').change(function(){$('#inApellido').removeClass("is-invalid")});
	$('#inPassword').change(function(){$('#inPassword').removeClass("is-invalid")});
});

