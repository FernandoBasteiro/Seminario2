function passwordsMatch() {
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

var existeUsuario = function() {
	loading();
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			noExiste();
			return true;
		}
		else if (this.readyState == 4 && this.status == 598) {
			existe();
			return false;
		}
		else if (this.readyState == 4) {
			//TODO hacer desaparecer el loading y mostrar error
			return false;
		}};
	xmlhttp.open("POST", "Servlet?action=existeUsuario", true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
	xmlhttp.send("usuario="+$("#inUsuario").val());
}

function submitirFormulario() {
	var correcto = true;
	if (! passwordsMatch()) {
		correcto = false;
	}
	if (existeUsuario()) {
		correcto = false;
	}
}

function loading() {
	$("#usrDiv").addClass("mb-0");
	$("#loading").removeClass("desaparecer");
	$("#existe").addClass("desaparecer");
	$("#noexiste").addClass("desaparecer");
};
function existe() {
	$("#loading").addClass("desaparecer");
	$("#existe").removeClass("desaparecer");
	$("#noexiste").addClass("desaparecer");
}
function noExiste() {
	$("#loading").addClass("desaparecer");
	$("#existe").addClass("desaparecer");
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
	$('#inUsuario').keyup(debounce(existeUsuario, 1000));
});

