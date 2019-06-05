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

function existeUsuario() {
	var xmlhttp = new XMLHttpRequest();
	//TODO Hacer aparecer el loading
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			//TODO hacer desaparecer el loading y mostrar ok
			return true;
		}
		else if (this.readyState == 4 && this.status == 598) {
			//TODO hacer desaparecer el loading y mostrar error
			return false;
		}
		else if (this.readyState == 4) {
			//TODO hacer desaparecer el loading y mostrar error
			return false;
		}
		xmlhttp.open("POST", "Servlet?action=existeUsuario", true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
	    xmlhttp.send("usuario="+$("#inUsuario").val());
	}
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